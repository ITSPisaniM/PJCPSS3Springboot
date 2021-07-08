package it.kennedy.cpss.springbootcpss.dto.input;

import lombok.Data;

import java.util.Date;

import lombok.Data;

@Data
public class AcquistiInsertDto {

    public int supplierId;
    public Date billDate;
    public int billNumber;
    //public Map<String, Integer> items;

}
