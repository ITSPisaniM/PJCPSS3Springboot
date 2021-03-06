package it.kennedy.cpss.springbootcpss.iservice;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.dto.input.OrdiniFilterDto;

public interface IOrdiniService {

	Page<OrdiniDto> getAllPagination(Pageable pageable);

	List<OrdiniDto> getAll();

	OrdiniDto findByAmazonOrderId(String id);

	BaseResponse<OrdiniDto> insertOrders() throws JsonProcessingException;

	Page<OrdiniDto> findByFilters(OrdiniFilterDto filter, Pageable pageable) throws ParseException;

	// ------------------------------------- FILTERS

}
