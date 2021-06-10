package it.kennedy.cpss.springbootcpss.Dto;

import java.util.Date;

public class SOBase <T>{
    public T[] data;
    public Date date;
    public Error[] errors;
    public boolean success;
    public Object token;
}
