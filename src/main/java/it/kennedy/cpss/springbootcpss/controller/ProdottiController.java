package it.kennedy.cpss.springbootcpss.controller;

import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import it.kennedy.cpss.springbootcpss.serviceimpl.ProdottiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/prodotti")
public class ProdottiController {

    @Autowired
    ProdottiService prodottiService;

    // --------------------------- PING
    @GetMapping(path = "/ping")
    public String ping() {
        return "PROVA DI PING...AVVENUTA CON SUCCESSO --- PRODOTTI";
    }

    // --------------------------- GET ALL PAGINATION PRODOTTI
    @GetMapping(produces = "application/json", path = "/page")
    public BaseResponse<List<ProdottiDto>> getAllProdotti(Pageable pageable) {

        BaseResponse<List<ProdottiDto>> response = new BaseResponse<>();

        List<ProdottiDto> listDto = prodottiService.getAllPagination(pageable);

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

    // --------------------------- GET ALL PRODOTTI
    @GetMapping(produces = "application/json", path = "/list")
    public BaseResponse<List<ProdottiDto>> list() {

        List<ProdottiDto> listDto = prodottiService.getAll();

        BaseResponse<List<ProdottiDto>> response = new BaseResponse<>();

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

    // --------------------------- GET BY ID PRODOTTI
    @GetMapping(produces = "application/json", path = "/{id}")
    public BaseResponse<List<ProdottiDto>> getById(@PathVariable String id) {

        BaseResponse<List<ProdottiDto>> response = new BaseResponse<>();

        List<ProdottiDto> listDto = new ArrayList<>();
        ProdottiDto prodottoDto = prodottiService.getByIdProdotto(id);

        listDto.add(prodottoDto);

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

    // --------------------------- INSERT PRODOTTI
    @PostMapping(consumes = "application/json", produces = "application/json", path = "/save")
    public BaseResponse<List<Boolean>> inserisci(@RequestBody ProdottiDto dto) {

        BaseResponse<List<Boolean>> response = new BaseResponse<>();

        List<Boolean> success = new ArrayList<Boolean>();
        success.add(prodottiService.insertProdotto(dto));

        response.setData(success);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());
        return response;
    }



    // --------------------------------------------------------------- FILTERS

    @GetMapping(produces = "application/json", path = "/count")
    public int count(){
        return prodottiService.count();
    }

}
