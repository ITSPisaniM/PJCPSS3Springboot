package it.kennedy.cpss.springbootcpss.IService;

import java.util.List;

import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;;
public interface IOrdiniService {

	public List<OrdiniDto> getAllPagination(int pagina, int dimensionePagina);
	public List<OrdiniDto> getAll();
	public OrdiniDto getById(int id);
	
}
