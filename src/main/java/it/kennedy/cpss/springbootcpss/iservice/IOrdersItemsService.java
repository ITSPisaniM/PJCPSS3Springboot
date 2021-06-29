package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.OrdersItemsDto;

import java.util.List;

public interface IOrdersItemsService {

    List<OrdersItemsDto> getAll();

}
