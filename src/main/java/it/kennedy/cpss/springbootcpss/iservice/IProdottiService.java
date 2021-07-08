package it.kennedy.cpss.springbootcpss.iservice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;

public interface IProdottiService {

    // GET ALL PAGINATION PRODOTTI
    Page<ProdottiDto> getAllPagination(Pageable pageable);

    List<ProdottiDto> getAll();

    ProdottiDto getByIdProdotto(String asin);

    Boolean insertProdotto(ProdottiDto dto);

    Boolean updateProdotto(ProdottiDto dto, int id);

    Boolean deleteProdotto(int id);

    int count();
}
