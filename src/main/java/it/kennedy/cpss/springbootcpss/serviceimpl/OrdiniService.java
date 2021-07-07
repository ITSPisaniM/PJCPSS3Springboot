package it.kennedy.cpss.springbootcpss.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.dao.ProdottiDao;
import it.kennedy.cpss.springbootcpss.dto.*;
import it.kennedy.cpss.springbootcpss.dto.input.OrdiniFilterDto;
import it.kennedy.cpss.springbootcpss.repository.IOrdersItemsRepository;
import it.kennedy.cpss.springbootcpss.repository.IOrdiniRepository;
import it.kennedy.cpss.springbootcpss.repository.IProdottiRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import it.kennedy.cpss.springbootcpss.iservice.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
	public Page<OrdiniDto> getAllPagination(Pageable pageable) {
		Page<OrdiniDao> pageListDao = ordiniRepository.findAll(pageable);
		return pageListDao.map(this::daoToDto);
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
	public BaseResponse<OrdiniDto> insertOrders(Orders.OrdiniInternal[] orders) throws JsonProcessingException {

		BaseResponse<OrdiniDto> res = new BaseResponse<>();

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
						if(apiDate.isAfter(lastInsertedDate)) defined.add(dtoInternalToDao(k));
					});
		} else {
			for(Orders.OrdiniInternal d : orders)
				defined.add(dtoInternalToDao(d));
		}

		/*
		 * Prendo gli ordini:
		 * 1. Per ogni ordine chiamo il servizio API per prendere gli items
		 * 2. Controllo che ci siano abbastanza items in magazzino per completare l' ordine
		 * 3. Se
		 * 4. salvo l'ordine a db
		 **/

		try{
			List<OrdiniDao> error = new ArrayList<>();
			Map<OrdiniDao, List<OrdersItemsDao>> ok = new HashMap<>();

			for(OrdiniDao d : defined){

				this.ordiniRepository.save(d);

				// 1. Per ogni ordine chiamo il servizio API per prendere gli items
				RestTemplate restTemplate = new RestTemplate();
				String result = restTemplate.getForObject(uri + d.getAmazonOrderId(), String.class);

				ObjectMapper mapper = new ObjectMapper();

				OrderItems ordersItems = mapper.readValue(result, OrderItems.class);

				List<OrdersItemsDao> temp = new ArrayList<>();
				// 2. Controllo che ci siano abbastanza items in magazzino per completare l' ordine
				for(OrderItems.OrdiniItemsInternal o : ordersItems.OrderItems) {
					ProdottiDao prodotto = this.prodottiRepository.findByAsin(o.getASIN());

					prodotto.setStock(prodotto.getStock() - o.getQuantityOrdered());

					this.prodottiRepository.save(prodotto);
					this.ordersItemsRepository.save(orderItemsInternalToDao(o));
				}
			}
		}catch (Exception ex){
 			res = BaseResponse.generateResponse(false, new Errors(ex.getMessage(), "OrdiniService.insertOrders"));
 			return res;
		}

		return res;
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

	private OrdersItemsDao orderItemsInternalToDao(OrderItems.OrdiniItemsInternal dto){
		var mapper = new ModelMapper();

		return mapper.map(dto, OrdersItemsDao.class);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	// FILTERS

	@Override
	public Page<OrdiniDto> findByFilters(OrdiniFilterDto filters, Pageable pageable) throws ParseException {

		String amazonOrderId = filters.getAmazonOrderId();
		String buyerEmail = filters.getBuyerEmail();
		String purchaseDate1 = filters.getPurchaseDate();
		var date = "1970-01-01";

		var purchaseDate = LocalDate.parse(date);
		if (purchaseDate1 != null) {
			purchaseDate = LocalDate.parse(purchaseDate1);
		}
		LocalDateTime purchaseDateFormat = purchaseDate.atStartOfDay();

		Specification<OrdiniDao> specAmazonOrderId = ordiniRepository.amazonOrderId(amazonOrderId);
		Specification<OrdiniDao> specBuyerEmail = ordiniRepository.buyerEmail(buyerEmail);
		Specification<OrdiniDao> specPurchaseDate = ordiniRepository.purchaseDate(purchaseDateFormat);

		Page<OrdiniDao> ordiniDao;

		if (amazonOrderId != null) {
			ordiniDao = ordiniRepository.findAll(specAmazonOrderId, pageable);
		} else if (buyerEmail != null) {
			specBuyerEmail.and(specPurchaseDate);
			ordiniDao = ordiniRepository.findAll(specBuyerEmail, pageable);
		} else {
			ordiniDao = ordiniRepository.findAll(specPurchaseDate, pageable);
		}

		return ordiniDao.map(this::daoToDto); // List<OrdiniDto>
	}


}
