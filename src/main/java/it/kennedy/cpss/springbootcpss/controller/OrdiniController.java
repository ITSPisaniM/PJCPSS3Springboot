package it.kennedy.cpss.springbootcpss.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.kennedy.cpss.springbootcpss.config.ServletTokenDetails;
import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.Orders;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.serviceimpl.OrdiniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/ordini")
public class OrdiniController {

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
			final String uri = "https://projectwork.gomulgame.com/WebServiceOrders.asmx/orders?refresh_token=Atzr|IwEBIPGGbogA4gJ86OciHsp16r6gXmV&CreatedAfter=2021-06-01T16:09:52.000&CreatedBefore=2021-07-31T16:09:52.000";

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
	// @SuppressWarnings("unused")
	@SuppressWarnings("unused")
	@GetMapping(produces = "application/json", path = "/page/{pagina}/{elPerPage}")
	public BaseResponse<OrdiniDto> getAllOrdini(@PathVariable int pagina, @PathVariable int elPerPage) {

		ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		String token = details.token;

		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		String elPerPageS = elPerPage + "";
		// noinspection ConstantConditions
		if (elPerPageS == null) {
			elPerPage = 10;
		}

		List<OrdiniDto> listDto = ordiniService.getAllPagination(pagina, elPerPage);

		response.setData(listDto);
		response.setDate(new Date());
		response.setErrors(new ArrayList<>());
		response.setSuccess(HttpStatus.OK.value());
		response.token = token;

		return response;
	}

	// --------------------------- GET ALL ORDINI
	@GetMapping(produces = "application/json", path = "/list")
	public BaseResponse<OrdiniDto> list() {

		ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		String token = details.token;

		List<OrdiniDto> listDto = ordiniService.getAll();

		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		response.setData(listDto);
		response.setDate(new Date());
		response.setErrors(new ArrayList<>());
		response.setSuccess(HttpStatus.OK.value());
		response.token = token;

		return response;
	}

	// --------------------------- GET BY ID ORDINI
	@GetMapping(produces = "application/json", path = "/{id}")
	public BaseResponse<OrdiniDto> getById(@PathVariable String id) {

		ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		String token = details.token;

		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		List<OrdiniDto> listDto = new ArrayList<>();
		OrdiniDto ordineDto = ordiniService.findByAmazonOrderId(id);

		listDto.add(ordineDto);

		response.setData(listDto);
		response.setDate(new Date());
		response.setErrors(new ArrayList<>());
		response.setSuccess(HttpStatus.OK.value());
		response.token = token;

		return response;
	}

}
