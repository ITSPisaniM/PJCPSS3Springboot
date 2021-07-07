package it.kennedy.cpss.springbootcpss.dto.input;

import java.util.Map;

import lombok.Data;

@Data
public class AcquistiInsertDto {
    private int supplierId;
    private String billDate;
    private int billNumber;
    private Map<String, Integer> items;
}
