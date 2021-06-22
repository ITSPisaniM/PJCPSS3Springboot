package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;

import java.util.List;

public interface IOrdiniService {

	public List<OrdiniDto> getAllPagination(int pagina, int dimensionePagina);

	public List<OrdiniDto> getAll();

	public OrdiniDto findByAmazonOrderId(String id);

}
