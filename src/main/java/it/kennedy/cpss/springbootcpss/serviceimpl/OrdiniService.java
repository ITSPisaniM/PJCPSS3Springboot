package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.dto.input.OrdiniFilterDto;
import it.kennedy.cpss.springbootcpss.iservice.IOrdiniService;
import it.kennedy.cpss.springbootcpss.repository.IOrdersItemsRepository;
import it.kennedy.cpss.springbootcpss.repository.IOrdiniRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class OrdiniService implements IOrdiniService {

	@Autowired
	IOrdiniRepository ordiniRepository;

	@Autowired
	IOrdersItemsRepository ordersItemsRepository;

	// GET ALL PAGINATION ORDINI
	@Override
	public List<OrdiniDto> getAllPagination(int pagina, int elPerPage) {
		List<OrdiniDto> listDto = new ArrayList<>();
		var pageable = PageRequest.of(pagina, elPerPage);
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
			String idString = id + "";
			OrdiniDao dao = ordiniRepository.findByAmazonOrderId(idString);
			dto = daoToDto(dao);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	// INSERT ORDINI API
	@Override
	public Boolean insertOrders(Orders.OrdiniInternal[] orders) {
		OrdiniDao dao = new OrdiniDao();
		try {
			for (Orders.OrdiniInternal dtoInternal:orders) {
				dtoInternalToDao(dtoInternal, dao);
				ordiniRepository.save(dao);
			}
			return true;
		} catch (Exception exc) {
			System.err.println(exc);
			return false;
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
	private OrdiniDao dtoInternalToDao(Orders.OrdiniInternal dto, OrdiniDao dao) {
		var mapper = new ModelMapper();
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
