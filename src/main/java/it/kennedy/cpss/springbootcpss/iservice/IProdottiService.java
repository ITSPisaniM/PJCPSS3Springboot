package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProdottiService {

    // GET ALL PAGINATION PRODOTTI
    List<ProdottiDto> getAllPagination(Pageable pageable);

    List<ProdottiDto> getAll();

    ProdottiDto getByIdProdotto(String asin);

    Boolean insertProdotto(ProdottiDto dto);

    Boolean updateProdotto(ProdottiDto dto, int id);

    Boolean deleteProdotto(int id);

    int count();
}
