package it.kennedy.cpss.springbootcpss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdottiDto {

    private String asin;
    private String title;
    private String category;
    private Double price;
    private int stock; // giacenza
    private String brand;
    private int giacenzaMinima;

}
