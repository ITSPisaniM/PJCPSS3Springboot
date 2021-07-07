package it.kennedy.cpss.springbootcpss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquistiProdottiDto {

    private Integer purchasesItemsId;

    private int purchaseId;

    private String asin;

    private Integer purchasedAmount;

    private Double unitPrice;

}
