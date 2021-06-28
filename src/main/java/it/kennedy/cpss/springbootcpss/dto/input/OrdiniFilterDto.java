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

    public String amazonOrderId;
    public String buyerEmail;
    public String purchaseDate;

}
