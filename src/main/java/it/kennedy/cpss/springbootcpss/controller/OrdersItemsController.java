package it.kennedy.cpss.springbootcpss.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.input.AnalisiFilterDto;
import it.kennedy.cpss.springbootcpss.serviceimpl.OrdersItemsService;

@RestController
@RequestMapping(value = "api/ordiniProdotti")
public class OrdersItemsController {

    @Autowired
    OrdersItemsService ordersItemsService;

    // --------------------------- FILTERS API
    @GetMapping(produces = "application/json", path = "/analytics")
    public BaseResponse<List<AnalisiFilterDto>> getByFilters(
            @RequestParam(required = false, name = "StartDate") String startDate,
            @RequestParam(required = false, name = "ItemAsin") String itemAsin) {

        var filter = new AnalisiFilterDto();

        if (StringUtils.isNotBlank(startDate)) {
            filter.setStartDateS(startDate);
        } else {
            filter.setStartDateS(null);
        }
        if (StringUtils.isNotBlank(itemAsin)) {
            filter.setItemAsin(itemAsin);
        } else {
            filter.setItemAsin(null);
        }

        BaseResponse<List<AnalisiFilterDto>> response = new BaseResponse<>();

        try {
            this.ordersItemsService.findByFilters(filter);
            response.setData(ordersItemsService.findByFilters(filter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

}
