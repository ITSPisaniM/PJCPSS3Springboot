package it.kennedy.cpss.springbootcpss.dto;

import it.kennedy.cpss.springbootcpss.dao.AcquistiProdottiDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquistiDto {

    private int purchaseId;

    private int supplierId;

    private Date billDate;

    private int billNumber;

    private List<AcquistiProdottiDao> acquistiProdotti;
}
