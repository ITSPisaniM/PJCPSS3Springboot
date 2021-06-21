package it.kennedy.cpss.springbootcpss.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.config.ServletTokenDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.serviceimpl.OrdiniService;

@RestController
@RequestMapping(value = "api/ordini")
public class OrdiniController {

	@Autowired
	OrdiniService ordiniService;

	// --------------------------- PING
	@GetMapping("/ping")
	public String ping() {
		return "PROVA DI PING...AVVENUTA CON SUCCESSO --- ORDINI";
	}


	// --------------------------- GET ALL PAGINATION ORDINI
	// @SuppressWarnings("unused")
	@SuppressWarnings("unused")
	@GetMapping(produces = "application/json", path = "/page/{pagina}/{elPerPage}")
	public BaseResponse<OrdiniDto> getAllOrdini(@PathVariable int pagina, @PathVariable int elPerPage) {

		ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		String token = details.token;

		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		String elPerPageS = elPerPage + "";
		//noinspection ConstantConditions
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

		ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
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

		ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
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
