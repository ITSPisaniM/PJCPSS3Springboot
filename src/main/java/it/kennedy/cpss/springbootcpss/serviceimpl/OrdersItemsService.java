package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.iservice.IOrdersItemsService;
import it.kennedy.cpss.springbootcpss.repository.IOrdersItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersItemsService implements IOrdersItemsService {

    @Autowired
    IOrdersItemsRepository orderItemsRepository;

    @Override
    public int totQuantita() {
        int quantity = orderItemsRepository.totQuantita();
        return quantity;
    }

    @Override
    public int totRicavi() {
        int ricavi = orderItemsRepository.totRicavi();
        return ricavi;
    }
}
