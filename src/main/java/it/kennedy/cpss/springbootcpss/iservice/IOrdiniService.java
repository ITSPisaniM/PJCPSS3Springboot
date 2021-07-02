package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.dto.input.OrdiniFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface IOrdiniService {

	Page<OrdiniDto> getAllPagination(Pageable pageable);

	List<OrdiniDto> getAll();

	OrdiniDto findByAmazonOrderId(String id);

	Boolean insertOrders(Orders.OrdiniInternal[] orders);

	List<OrdiniDto> findByFilters(OrdiniFilterDto filter, Pageable pageable) throws ParseException;

	//------------------------------------- FILTERS



}
