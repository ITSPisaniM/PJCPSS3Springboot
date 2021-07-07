package it.kennedy.cpss.springbootcpss.dto;

import java.util.Date;
import java.util.List;

import it.kennedy.cpss.springbootcpss.dao.AcquistiProdottiDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
