package it.kennedy.cpss.springbootcpss.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalisiDto {

    public int quantitaTot; // quantità per settimana
    public int ricaviTot; // ricavi per settimana

}
