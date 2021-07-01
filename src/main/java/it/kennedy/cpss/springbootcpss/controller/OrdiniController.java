package it.kennedy.cpss.springbootcpss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.dto.input.OrdiniFilterDto;
import it.kennedy.cpss.springbootcpss.serviceimpl.OrdiniService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/ordini")
public class OrdiniController {
	@Value("${utils.ordiniUri}")
	private String uri;

	@Autowired
	OrdiniService ordiniService;

	// --------------------------- PING
	@GetMapping(path = "/ping")
	public String ping() {
		return "PROVA DI PING...AVVENUTA CON SUCCESSO --- ORDINI";
	}

	// --------------------------- INSERT FROM API ORDINI
	@GetMapping(path = "/insertAPI")
	private String insert() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);

			ObjectMapper mapper = new ObjectMapper();

			Orders orders = mapper.readValue(result, Orders.class);

			System.err.println(orders.Orders);

			boolean success = ordiniService.insertOrders(orders.Orders); // da dichiarare il metodo
			return "GETTING DATA: " + success;
		} catch (Exception exc) {
			return "Exception raised: " + exc;
		}
	}

	// --------------------------- GET ALL PAGINATION ORDINI
	@GetMapping(produces = "application/json", path = "/page")
	public BaseResponse<OrdiniDto> getAllOrdini(Pageable pageable) {

		BaseResponse<OrdiniDto> response = new BaseResponse<>();


		List<OrdiniDto> listDto = ordiniService.getAllPagination(pageable);

		response.setData(listDto);
		response.setDate(new Date());
		response.setErrors(new ArrayList<>());
		response.setSuccess(HttpStatus.OK.value());

		return response;
	}

	// --------------------------- GET ALL ORDINI
	@GetMapping(produces = "application/json", path = "/list")
	public BaseResponse<OrdiniDto> list() {

		List<OrdiniDto> listDto = ordiniService.getAll();

		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		response.setData(listDto);
		response.setDate(new Date());
		response.setErrors(new ArrayList<>());
		response.setSuccess(HttpStatus.OK.value());

		return response;
	}

	// --------------------------- GET BY ID ORDINI
	@GetMapping(produces = "application/json", path = "/{id}")
	public BaseResponse<OrdiniDto> getById(@PathVariable String id) {

		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		List<OrdiniDto> listDto = new ArrayList<>();
		OrdiniDto ordineDto = ordiniService.findByAmazonOrderId(id);

		listDto.add(ordineDto);

		response.setData(listDto);
		response.setDate(new Date());
		response.setErrors(new ArrayList<>());
		response.setSuccess(HttpStatus.OK.value());
		return response;
	}

	// --------------------------- FILTERS API
	@GetMapping(produces = "application/json")
	public BaseResponse<OrdiniDto> getByFilters(
			Pageable pageable,
			// localhost:8090/api/ordini?page=0&size=5
			//required = false --> non è obbligatorio l'inserimento
			//name = "name" --> quello che viene fuori nell'URL nel browser
			@RequestParam(required = false, name="amazonOrderId") String amazonOrderId,
			@RequestParam(required = false, name ="buyerEmail") String buyerEmail,
			@RequestParam(required = false, name = "purchaseDate") String purchaseDate
	) throws ParseException {

		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		//creo una val di tipo OrdiniFilteDto per passarla come oggetto al Service
		OrdiniFilterDto filters = new OrdiniFilterDto();
		//StringUtils --> dependency per controllare che una stringa non sia: null, "", " ";
		if (StringUtils.isNotBlank(amazonOrderId)) {
			filters.setAmazonOrderId(amazonOrderId);
		}
		if (StringUtils.isNotBlank(buyerEmail)) {
			filters.setBuyerEmail(buyerEmail);
		}
		if (StringUtils.isNotBlank(purchaseDate)) {
			filters.setPurchaseDate(purchaseDate);
		}

		response.setData(ordiniService.findByFilters(filters, pageable));
		response.setDate(new Date());
		response.setErrors(new ArrayList<>());
		response.setSuccess(HttpStatus.OK.value());

		return response;
	}



}
