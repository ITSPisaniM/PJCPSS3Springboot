package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;

import java.util.List;

public interface IProdottiService {

    public List<ProdottiDto> getAllPagination(int pagina, int dimensionePagina);

    public ProdottiDto getByIdProdotto(String asin);

    public Boolean insertProdotto(ProdottiDto dto);

    public Boolean updateProdotto(ProdottiDto dto, int id);

    public Boolean deleteProdotto(int id);




}
