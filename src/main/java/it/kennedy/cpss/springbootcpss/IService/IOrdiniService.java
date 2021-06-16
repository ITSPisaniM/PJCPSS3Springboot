package it.kennedy.cpss.springbootcpss.IService;

import java.util.List;

import it.kennedy.cpss.springbootcpss.Dto.OrdiniDto;;
public interface IOrdiniService {

	public List<OrdiniDto> getAll(int pagina, int dimensionePagina);
	public OrdiniDto getById(int id);
	
}
