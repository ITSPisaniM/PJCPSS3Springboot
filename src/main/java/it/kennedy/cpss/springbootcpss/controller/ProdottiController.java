package it.kennedy.cpss.springbootcpss.controller;

import it.kennedy.cpss.springbootcpss.config.ServletTokenDetails;
import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import it.kennedy.cpss.springbootcpss.serviceimpl.ProdottiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping(produces = "application/json", path = "/page/{pagina}/{elPerPage}")
    public BaseResponse<ProdottiDto> getAllProdotti(@PathVariable int pagina, @PathVariable int elPerPage) {

        ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String token = details.token;

        BaseResponse<ProdottiDto> response = new BaseResponse<>();

        String elPerPageS = elPerPage + "";
        //noinspection ConstantConditions
        if (elPerPageS == null) {
            elPerPage = 10;
        }

        List<ProdottiDto> listDto = prodottiService.getAllPagination(pagina, elPerPage);

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());
        response.token = token;

        return response;
    }

    // --------------------------- GET ALL PRODOTTI
    @GetMapping(produces = "application/json", path = "/list")
    public BaseResponse<ProdottiDto> list() {

        ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication()
                .getDetails();
        String token = details.token;

        List<ProdottiDto> listDto = prodottiService.getAll();

        BaseResponse<ProdottiDto> response = new BaseResponse<>();

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());
        response.token = token;

        return response;
    }

    // --------------------------- GET BY ID PRODOTTI
    @GetMapping(produces = "application/json", path = "/{id}")
    public BaseResponse<ProdottiDto> getById(@PathVariable String id) {

        ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication()
                .getDetails();
        String token = details.token;

        BaseResponse<ProdottiDto> response = new BaseResponse<>();

        List<ProdottiDto> listDto = new ArrayList<>();
        ProdottiDto prodottoDto = prodottiService.getByIdProdotto(id);

        listDto.add(prodottoDto);

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());
        response.token = token;

        return response;
    }

    // --------------------------- INSERT PRODOTTI
    @PostMapping(consumes = "application/json", produces = "application/json", path = "/save")
    public BaseResponse<Boolean> inserisci(@RequestBody ProdottiDto dto) {
        ServletTokenDetails details = (ServletTokenDetails) SecurityContextHolder.getContext().getAuthentication()
                .getDetails();
        String token = details.token;

        BaseResponse<Boolean> response = new BaseResponse<>();

        List<Boolean> success = new ArrayList<Boolean>();
        success.add(prodottiService.insertProdotto(dto));

        response.setData(success);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());
        response.token = token;

        return response;
    }



}
