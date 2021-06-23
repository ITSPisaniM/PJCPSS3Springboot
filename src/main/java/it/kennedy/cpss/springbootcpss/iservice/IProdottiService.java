package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;

import java.util.List;

public interface IProdottiService {

    List<ProdottiDto> getAllPagination(int pagina, int dimensionePagina);

    List<ProdottiDto> getAll();

    ProdottiDto getByIdProdotto(String asin);

    Boolean insertProdotto(ProdottiDto dto);

    Boolean updateProdotto(ProdottiDto dto, int id);

    Boolean deleteProdotto(int id);




}
