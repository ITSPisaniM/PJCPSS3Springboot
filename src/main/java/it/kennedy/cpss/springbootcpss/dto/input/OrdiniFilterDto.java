package it.kennedy.cpss.springbootcpss.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdiniFilterDto {

    private String amazonOrderId;
    private String buyerEmail;
    private String purchaseDate;

}
