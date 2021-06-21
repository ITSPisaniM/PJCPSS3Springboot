package it.kennedy.cpss.springbootcpss.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public String save() {
		return "PROVA DI PING...AVVENUTA CON SUCCESSO --- ORDINI";
	}

	// --------------------------- GET ALL PAGINATION ORDINI
	// @SuppressWarnings("unused")
	@SuppressWarnings("unused")
	@GetMapping(produces = "application/json", path = "/page/{pagina}/{elPerPage}")
	public BaseResponse<OrdiniDto> getAllOrdini(@PathVariable int pagina, @PathVariable int elPerPage) {
		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		List<OrdiniDto> listDto = new ArrayList<>();

		String elPerPageS = null;
		elPerPageS = elPerPage + "";
		if (elPerPageS == null) {
			elPerPage = 10;
		}

		listDto = ordiniService.getAllPagination(pagina, elPerPage);

		response.setData(listDto);
		response.setDate(new Date());
		response.setErrors(new ArrayList<Errors>());
		response.setSuccess(HttpStatus.OK.value());
		// response.setToken();

		return response;
	}

	// --------------------------- GET ALL ORDINI
	@GetMapping(produces = "application/json", path = "/list")
	public BaseResponse<OrdiniDto> list() {

		List<OrdiniDto> listDto = ordiniService.getAll();

		BaseResponse<OrdiniDto> response = new BaseResponse<>();

		response.setData(listDto);
		response.setDate(new Date());
		//response.setErrors(new ArrayList<Errors>());
		response.setSuccess(HttpStatus.OK.value());

		return response;
	}

}
