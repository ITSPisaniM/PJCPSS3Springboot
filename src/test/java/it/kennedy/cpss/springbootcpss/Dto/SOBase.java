package it.kennedy.cpss.springbootcpss.Dto;

import java.util.Date;

public class SOBase<T> {
    public T[] data;
    public Error[] e;
    public Date date;
    public boolean success;
    public Object token;
}
