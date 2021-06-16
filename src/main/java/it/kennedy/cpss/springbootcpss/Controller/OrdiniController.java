package it.kennedy.cpss.springbootcpss.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.kennedy.cpss.springbootcpss.Dto.Errors;
import it.kennedy.cpss.springbootcpss.Dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.Dto.SOBase;
import it.kennedy.cpss.springbootcpss.ServiceImpl.OrdiniService;

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
	//@SuppressWarnings("unused")
	@SuppressWarnings("unused")
	@GetMapping(produces = "application/json", path = "/page/{pagina}/{elPerPage}")
	public SOBase<List<OrdiniDto>> getAllOrdini(@PathVariable int pagina, @PathVariable int elPerPage) {
		SOBase<List<OrdiniDto>> response = new SOBase<>();

		List<OrdiniDto> listDto = new ArrayList<OrdiniDto>();

		String elPerPageS = null;
		elPerPageS = elPerPage + "";
		if (elPerPageS == null) {
			elPerPage = 10;
		}

		listDto = ordiniService.getAllPagination(pagina, elPerPage);

		response.setData(listDto);
		response.setDate(new Date());
		response.setErrors(new Errors());
		response.setSuccess(HttpStatus.OK.value());
		//response.setToken();

		return response;
	}
		
	
	// --------------------------- GET ALL ORDINI
		@GetMapping(produces = "application/json", path = "/list")
		public SOBase<List<OrdiniDto>> list() {
			
			List<OrdiniDto> listDto = ordiniService.getAll();
			SOBase<List<OrdiniDto>> response = new SOBase<List<OrdiniDto>>();

			listDto = ordiniService.getAll();

			response.setData(listDto);
			response.setDate(new Date());
			response.setErrors(new Errors());
			response.setSuccess(HttpStatus.OK.value());

			return response;
		}
	
	
			
	
}
