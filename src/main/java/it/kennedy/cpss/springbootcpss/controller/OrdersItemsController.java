package it.kennedy.cpss.springbootcpss.controller;

import it.kennedy.cpss.springbootcpss.dto.input.AnalisiDto;
import it.kennedy.cpss.springbootcpss.serviceimpl.OrdersItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/ordiniProdotti")
public class OrdersItemsController {

    @Autowired
    OrdersItemsService ordersItemsService;

    @GetMapping(produces = "application/json", path = "/analisi1")
    public AnalisiDto analisi1(){
        AnalisiDto analisiDto = new AnalisiDto();

        int quantita = ordersItemsService.totQuantita();
        int ricavi = ordersItemsService.totRicavi();

        analisiDto.setQuantitaTot(quantita);
        analisiDto.setRicaviTot(ricavi);

        return analisiDto;
    }

}
