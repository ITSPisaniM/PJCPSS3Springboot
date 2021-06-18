package it.kennedy.cpss.springbootcpss.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SOBase <T> {
    public Object data;
    public Date date;
    public Errors errors;
    public int success;
    public Object token;
}
