package it.kennedy.cpss.springbootcpss.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BaseResponse<T> {
    public List<T> data;
    public Date date;
    public List<Errors> errors;
    public int success;
    public Boolean result;
}
