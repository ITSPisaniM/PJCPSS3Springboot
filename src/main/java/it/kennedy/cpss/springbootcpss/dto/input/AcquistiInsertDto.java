package it.kennedy.cpss.springbootcpss.dto.input;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class AcquistiInsertDto {
    public int supplierId;
    public String billDate;
    public int billNumber;
    public Map<String, Integer> items;
}
