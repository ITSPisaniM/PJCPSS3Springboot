package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.dto.input.OrdiniFilterDto;
import it.kennedy.cpss.springbootcpss.iservice.IOrdiniService;
import it.kennedy.cpss.springbootcpss.repository.IOrdersItemsRepository;
import it.kennedy.cpss.springbootcpss.repository.IOrdiniRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OrdiniService implements IOrdiniService {

	private static final Logger LOGGER = Logger.getLogger(OrdiniService.class.getName());

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
			OrdiniDao dao = ordiniRepository.findByAmazonOrderId(id);
			return daoToDto(dao);
		} catch (Exception e) {
			return null;
		}
	}

	// INSERT ORDINI API
	@Override
	public Boolean insertOrders(Orders.OrdiniInternal[] orders) {

		/*
		 * 1. Prendo l' ultimo ordine inserito da db (tramite data -> purchase date) 2.
		 * Prendo dall' API tutti gli ordini con purchase dopo quella data 3. Ci sarebbe
		 * da capire il LastUpdateDate che minchia fa
		 */

		Optional<OrdiniDao> lastDao = this.ordiniRepository.getLastOrder();
		ArrayList<OrdiniDao> defined = new ArrayList<>();
		if (!lastDao.isEmpty()) {
			var format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
			LocalDateTime lastInsertedDate = lastDao.get().getPurchaseDate();

			Arrays.stream(orders).forEach(k -> {
				var apiDate = LocalDateTime.parse(k.PurchaseDate, format);
				if (apiDate.isAfter(lastInsertedDate))
					defined.add(this.dtoInternalToDao(k));
			});

			for (OrdiniDao d : defined)
				this.ordiniRepository.save(d);

			return true;
		} else {
			var dao = new OrdiniDao();
			try {
				for (Orders.OrdiniInternal dtoInternal : orders) {
					dao = dtoInternalToDao(dtoInternal);
					ordiniRepository.save(dao);
				}
				return true;
			} catch (Exception exc) {
				LOGGER.warning(exc.getMessage());
				return false;
			}
		}
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
				var format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
				return LocalDateTime.parse(source, format);
			}
		};

		var mapper = new ModelMapper();

		mapper.typeMap(Orders.OrdiniInternal.class, OrdiniDao.class)
				.addMappings(mp -> mp.using(toStringDate).map(Orders.OrdiniInternal::getPurchaseDate,
						OrdiniDao::setPurchaseDate))
				.addMappings(mp -> mp.using(toStringDate).map(Orders.OrdiniInternal::getLastUpdateDate,
						OrdiniDao::setLastUpdatedDate))
				.addMappings(mp -> mp.using(toStringDate).map(Orders.OrdiniInternal::getEarliestShipDate,
						OrdiniDao::setEarliestsShipDate))
				.addMappings(mp -> mp.using(toStringDate).map(Orders.OrdiniInternal::getLatestShipDate,
						OrdiniDao::setLatestShipDate));

		return mapper.map(dto, OrdiniDao.class);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// FILTERS

	@Override
	public List<OrdiniDto> findByFilters(OrdiniFilterDto filters, Pageable pageable) throws ParseException {

		String amazonOrderId = filters.getAmazonOrderId();
		String buyerEmail = filters.getBuyerEmail();
		String purchaseDate1 = filters.getPurchaseDate();
		var date = "1970-01-01";

		LocalDate purchaseDate = LocalDate.parse(date);
		if (purchaseDate1 != null) {
			purchaseDate = LocalDate.parse(purchaseDate1);
		}
		LocalDateTime purchaseDateFormat = purchaseDate.atStartOfDay();

		Specification<OrdiniDao> specAmazonOrderId = ordiniRepository.amazonOrderId(amazonOrderId);
		Specification<OrdiniDao> specBuyerEmail = ordiniRepository.buyerEmail(buyerEmail);
		Specification<OrdiniDao> specPurchaseDate = ordiniRepository.purchaseDate(purchaseDateFormat);

		List<OrdiniDao> ordiniDao;

		if (amazonOrderId != null) {
			ordiniDao = ordiniRepository.findAll(specAmazonOrderId, pageable).getContent();
		} else if (buyerEmail != null) {
			specBuyerEmail.and(specPurchaseDate);
			ordiniDao = ordiniRepository.findAll(specBuyerEmail, pageable).getContent();
		} else {
			ordiniDao = ordiniRepository.findAll(specPurchaseDate, pageable).getContent();
		}

		List<OrdiniDto> ordiniDto = new ArrayList<>();
		for (OrdiniDao ordine : ordiniDao) {
			ordiniDto.add(daoToDto(ordine));
		}
		return ordiniDto; // List<OrdiniDto>
	}

}
