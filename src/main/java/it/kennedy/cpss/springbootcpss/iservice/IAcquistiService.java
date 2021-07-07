package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.AcquistiDto;
import it.kennedy.cpss.springbootcpss.dto.AcquistiProdottiDto;
import it.kennedy.cpss.springbootcpss.dto.input.AcquistiInsertDto;
import it.kennedy.cpss.springbootcpss.dto.input.ProdottoInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IAcquistiService {

    Page<AcquistiDto> getAllPagination(Pageable papageable);

    List<AcquistiDto> getAll();

    AcquistiDto findByPurchaseId(int id);

    Boolean deleteAcquisto(int id);

    Boolean modifyAcquisto(AcquistiDto dto, int id);

    Boolean insertAcquisto(AcquistiInsertDto dto);

    int getLastId();

    Boolean insertPurchasesItems(List<ProdottoInput> piDto, int idAcquisto);
}
