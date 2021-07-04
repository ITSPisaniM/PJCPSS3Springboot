package it.kennedy.cpss.springbootcpss.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    public static BaseResponse generateResponse(boolean result, List<Errors> errors){
        BaseResponse res = new BaseResponse();

        res.result = result;
        res.date = new Date();
        res.errors = errors;

        return res;
    }

    public static BaseResponse generateResponse(boolean result, Errors errors){
        BaseResponse res = new BaseResponse();

        res.result = result;
        res.date = new Date();
        res.errors = new ArrayList();
        res.errors.add(errors);

        return res;
    }
}
