package it.kennedy.cpss.springbootcpss.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdottiDto {

    public String asin;

    public String title;

    public String category;

    public Double price;

    public int stock; //giacenza

    public String brand;

}
