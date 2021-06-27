package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.iservice.IOrdiniService;
import it.kennedy.cpss.springbootcpss.repository.IOrdersItemsRepository;
import it.kennedy.cpss.springbootcpss.repository.IOrdiniRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

		/*
		* 1. Prendo l' ultimo ordine inserito da db (tramite data -> purchase date)
		* 2. Prendo dall' API tutti gli ordini con purchase dopo quella data
		* 3. Ci sarebbe da capire il LastUpdateDate che minchia fa
		* */

		Optional<OrdiniDao> lastDao = this.ordiniRepository.getLastOrder();
		ArrayList<OrdiniDao> defined = new ArrayList<>();
		if(!lastDao.isEmpty()){
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

			Date lastInsertedDate = lastDao.get().getPurchaseDate();
			Arrays.stream(orders)
					.forEach(k -> {
						DateTime arrayDate = DateTime.parse(lastInsertedDate.toString(), formatter);
						DateTime daoDate = DateTime.parse(k.getPurchaseDate(), formatter);

						int compared = daoDate.compareTo(arrayDate);
						if(compared == -1) defined.add(dtoInternalToDao(k, new OrdiniDao()));
					});
		}

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
}
