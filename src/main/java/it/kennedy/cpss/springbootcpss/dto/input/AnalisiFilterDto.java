package it.kennedy.cpss.springbootcpss.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalisiFilterDto {

    public int quantitaTot; // quantità per settimana
    public int ricaviTot; // ricavi per settimana
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    public String startDateS;
    public String endDateS;
    public String analiticsType;

}
