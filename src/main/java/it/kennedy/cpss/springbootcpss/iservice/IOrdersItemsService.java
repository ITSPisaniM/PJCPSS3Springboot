package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.input.AnalisiFilterDto;

import java.text.ParseException;
import java.util.List;

public interface IOrdersItemsService {

    List<AnalisiFilterDto> findByFilters(AnalisiFilterDto filter) throws ParseException;

    //QuantitaRicaviDao test(AnalisiFilterDto filter);

}
