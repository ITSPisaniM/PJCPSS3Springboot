package it.kennedy.cpss.springbootcpss.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private T data;
    private Date date;
    private List<Errors> errors;
    private int success;
    private Boolean result;

    public static BaseResponse generateResponse(boolean result, List<Errors> errors) {
        var res = new BaseResponse<>();

        res.result = result;
        res.date = new Date();
        res.errors = errors;

        return res;
    }

    public static BaseResponse generateResponse(boolean result, Errors errors) {
        var res = new BaseResponse<>();

        res.result = result;
        res.date = new Date();
        res.errors = new ArrayList<>();
        res.errors.add(errors);

        return res;
    }
}
