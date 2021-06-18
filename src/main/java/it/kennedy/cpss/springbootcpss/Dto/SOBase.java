package it.kennedy.cpss.springbootcpss.Dto;

import java.util.Date;
import java.util.List;

public class SOBase <T>{
    public List<T> data;
    public Date date;
    public List<Errors> errors;
    public boolean success;
    public String token;
}
