package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.AcquistiDto;

import java.util.List;

public interface IAcquistiService {

    List<AcquistiDto> getAllPagination(int pagina, int dimensionePagina);

    List<AcquistiDto> getAll();

    AcquistiDto findByPurchaseId(String id);

}
