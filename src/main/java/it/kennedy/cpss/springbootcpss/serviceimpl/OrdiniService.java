package it.kennedy.cpss.springbootcpss.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.dao.ProdottiDao;
import it.kennedy.cpss.springbootcpss.dto.OrderItems;
import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.dto.input.OrdiniFilterDto;
import it.kennedy.cpss.springbootcpss.iservice.IOrdiniService;
import it.kennedy.cpss.springbootcpss.repository.IOrdersItemsRepository;
import it.kennedy.cpss.springbootcpss.repository.IOrdiniRepository;
import it.kennedy.cpss.springbootcpss.repository.IProdottiRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrdiniService implements IOrdiniService {

	@Value("${utils.ordiniItems}")
	private String uri;

	@Autowired
	IProdottiRepository prodottiRepository;

	@Autowired
	IOrdiniRepository ordiniRepository;

	@Autowired
	IOrdersItemsRepository ordersItemsRepository;

	// GET ALL PAGINATION ORDINI
	@Override
	public List<OrdiniDto> getAllPagination(Pageable pageable) {
		List<OrdiniDto> listDto = new ArrayList<>();
		for (OrdiniDao dao : ordiniRepository.findAll(pageable)) {
			var dto = daoToDto(dao);
			listDto.add(dto);
		}
		return listDto;
	}

	// GET ALL ORDINI
	@Override
	public List<OrdiniDto> getAll() {
		List<OrdiniDao> listaDao = ordiniRepository.findAll();
		List<OrdiniDto> listaDto = new ArrayList<>();
		for (OrdiniDao dao : listaDao) {
			var dto = daoToDto(dao);
			listaDto.add(dto);
		}
		return listaDto;
	}

	// GET BY ID ORDINE
	@Override
	public OrdiniDto findByAmazonOrderId(String id) {
		try {
			OrdiniDto dto = new OrdiniDto();
			OrdiniDao dao = ordiniRepository.findByAmazonOrderId(id);
			dto = daoToDto(dao);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	// INSERT ORDINI API
	@Override
	public Boolean insertOrders(Orders.OrdiniInternal[] orders) throws JsonProcessingException {

		/*
		* 1. Prendo l' ultimo ordine inserito da db (tramite data -> purchase date)
		* 2. Prendo dall' API tutti gli ordini con purchase dopo quella data
		* */

		Optional<OrdiniDao> lastDao = this.ordiniRepository.getLastOrder();
		ArrayList<OrdiniDao> defined = new ArrayList<>();

		if(!lastDao.isEmpty()){
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
			LocalDateTime lastInsertedDate = lastDao.get().getPurchaseDate();

			Arrays.stream(orders)
					.forEach(k -> {
						LocalDateTime apiDate = LocalDateTime.parse(k.PurchaseDate, format);
						if(apiDate.isAfter(lastInsertedDate)) defined.add(this.dtoInternalToDao(k));
					});

			//for(OrdiniDao d : defined) this.ordiniRepository.save(d);

			//return true;
		} else {
			for(Orders.OrdiniInternal d : orders)
				defined.add(dtoInternalToDao(d));
		}

		/*
		 * Prendo gli ordini:
		 * 1. Per ogni ordine chiamo il servizio API per prendere gli items
		 * 2. Controllo che ci siano abbastanza items in magazzino per completare l' ordine
		 * 3. Creo l'oggetto orderItems e lo salvo a db
		 * 4. salvo l'ordine a db
		 **/

		try{
			ArrayList<ProdottiDao> prodottiList = new ArrayList<>();

			for(OrdiniDao d : defined){
				RestTemplate restTemplate = new RestTemplate();
				String result = restTemplate.getForObject(uri + d.getAmazonOrderId(), String.class);

				ObjectMapper mapper = new ObjectMapper();

				OrderItems ordersItems = mapper.readValue(result, OrderItems.class);

				for(OrderItems.OrdiniItemsInternal o : ordersItems.OrderItems){
					ProdottiDao prodotto = this.prodottiRepository.findByAsin(o.getASIN());

					if(!(prodotto.getStock() > o.getQuantityOrdered()))
						return false;

					prodotto.setStock(prodotto.getStock() - o.getQuantityOrdered());

					prodottiList.add(prodotto);
				}
			}
		}catch (Exception ex){
			return false;
		}

		return false;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	// METHODS

	// DAO TO DTO METHOD
	private OrdiniDto daoToDto(OrdiniDao dao) {
		var mapper = new ModelMapper();
		OrdiniDto dto = mapper.map(dao, OrdiniDto.class);

		String amazonOrderId = dao.getAmazonOrderId();

		List<OrdersItemsDao> orderItemsDao = ordersItemsRepository.findByAmazonOrderId(amazonOrderId);

		dto.setOrdersItems(orderItemsDao);
		return dto;
	}

	// DTO TO DAO METHOD
	private OrdiniDao dtoToDao(OrdiniDto dto) {
		var mapper = new ModelMapper();
		return mapper.map(dto, OrdiniDao.class);
	}

	// DTO INTERNAL TO DAO METHOD
	private OrdiniDao dtoInternalToDao(Orders.OrdiniInternal dto) {

		Converter<String, LocalDateTime> toStringDate = new AbstractConverter<String, LocalDateTime>() {
			@Override
			protected LocalDateTime convert(String source) {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
				LocalDateTime localDate = LocalDateTime.parse(source, format);
				return localDate;
			}
		};


		var mapper = new ModelMapper();

		mapper.typeMap(Orders.OrdiniInternal.class, OrdiniDao.class)
				.addMappings(mp -> mp.using(toStringDate).map(Orders.OrdiniInternal :: getPurchaseDate, OrdiniDao :: setPurchaseDate))
				.addMappings(mp -> mp.using(toStringDate).map(Orders.OrdiniInternal :: getLastUpdateDate, OrdiniDao :: setLastUpdatedDate))
				.addMappings(mp -> mp.using(toStringDate).map(Orders.OrdiniInternal :: getEarliestShipDate, OrdiniDao :: setEarliestsShipDate))
				.addMappings(mp -> mp.using(toStringDate).map(Orders.OrdiniInternal :: getLatestShipDate, OrdiniDao :: setLatestShipDate));

		return mapper.map(dto, OrdiniDao.class);
	}


	//--------------------------------------------------------------------------------------------------------------------------------
	// FILTERS

	@Override
	public List<OrdiniDto> findByFilters(OrdiniFilterDto filters, Pageable pageable) throws ParseException {

			String amazonOrderId = filters.getAmazonOrderId();
			String buyerEmail = filters.getBuyerEmail();
			String purchaseDate1 = filters.getPurchaseDate();
			String date = "1970-01-01";

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);
		Date purchaseDate = format.parse(date);
		if (purchaseDate1 != null) {
			purchaseDate = format.parse(purchaseDate1);
		}

		Specification<OrdiniDao> specAmazonOrderId = ordiniRepository.amazonOrderId(amazonOrderId);
		Specification<OrdiniDao> specBuyerEmail = ordiniRepository.buyerEmail(buyerEmail);
		Specification<OrdiniDao> specPurchaseDate = ordiniRepository.purchaseDate(purchaseDate);

			List<OrdiniDao> ordiniDao = new ArrayList<>();

			if (amazonOrderId != null) {
				ordiniDao = ordiniRepository.findAll(specAmazonOrderId, pageable).getContent();
			} else if (amazonOrderId == null && buyerEmail != null) {
				specBuyerEmail.and(specPurchaseDate);
				ordiniDao = ordiniRepository.findAll(specBuyerEmail, pageable).getContent();
			} else {
				ordiniDao = ordiniRepository.findAll(specPurchaseDate, pageable).getContent();
			}

		List<OrdiniDto> ordiniDto = new ArrayList<>();
		for (OrdiniDao ordine : ordiniDao) {
			ordiniDto.add(daoToDto(ordine));
		}
		return ordiniDto; //List<OrdiniDto>
	}


}
