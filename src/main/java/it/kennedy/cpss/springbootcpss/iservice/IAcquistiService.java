package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.AcquistiDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAcquistiService {

    Page<AcquistiDto> getAllPagination(Pageable papageable);

    List<AcquistiDto> getAll();

    AcquistiDto findByPurchaseId(int id);

    Boolean deleteAcquisto(int id);

    Boolean modifyAcquisto(AcquistiDto dto, int id);

    Boolean insertAcquisto(AcquistiDto dto);
}
