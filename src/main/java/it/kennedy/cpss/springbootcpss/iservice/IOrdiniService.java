package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;

import java.util.List;

public interface IOrdiniService {

	List<OrdiniDto> getAllPagination(int pagina, int dimensionePagina);

	List<OrdiniDto> getAll();

	OrdiniDto findByAmazonOrderId(String id);

	Boolean insertOrders(Orders.OrdiniInternal[] orders);

}
