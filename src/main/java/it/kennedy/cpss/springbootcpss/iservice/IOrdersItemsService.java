package it.kennedy.cpss.springbootcpss.iservice;

import java.text.ParseException;
import java.util.List;

import it.kennedy.cpss.springbootcpss.dto.input.AnalisiFilterDto;

public interface IOrdersItemsService {

    List<AnalisiFilterDto> findByFilters(AnalisiFilterDto filter) throws ParseException;

    // QuantitaRicaviDao test(AnalisiFilterDto filter);

}
