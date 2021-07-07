package it.kennedy.cpss.springbootcpss.dto.input;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalisiFilterDto {

    private int quantitaTot; // quantità per settimana
    private double ricaviTot; // ricavi per settimana
    private LocalDateTime startDate;
    private String startDateS;
    private String itemAsin;

}
