package it.kennedy.cpss.springbootcpss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquistiDto {

    public int purchaseId;
    public int supplierId;
    public Date billDate;
    public int billNumber;

}
