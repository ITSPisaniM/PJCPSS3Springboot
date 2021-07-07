package it.kennedy.cpss.springbootcpss.controller;

import it.kennedy.cpss.springbootcpss.dto.AcquistiDto;
import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.input.AcquistiInsertDto;
import it.kennedy.cpss.springbootcpss.dto.input.ProdottoInput;
import it.kennedy.cpss.springbootcpss.serviceimpl.AcquistiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "api/acquisti")
public class AcquistiController {

    @Autowired
    AcquistiService acquistiService;

    // --------------------------- PING
    @GetMapping(path = "/ping")
    public String ping() {
        return "PROVA DI PING...AVVENUTA CON SUCCESSO --- ACQUISTI";
    }

    // --------------------------- GET ALL PAGINATION ACQUISTI
    @GetMapping(produces = "application/json", path = "/page")
    public BaseResponse<Page<AcquistiDto>> getAllOrdini(Pageable pageable) {

        BaseResponse<Page<AcquistiDto>> response = new BaseResponse<>();
        Page<AcquistiDto> listDto = acquistiService.getAllPagination(pageable);

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

    // --------------------------- GET ALL ACQUISTI
    @GetMapping(produces = "application/json", path = "/list")
    public BaseResponse<List<AcquistiDto>> list() {

        List<AcquistiDto> listDto = acquistiService.getAll();

        BaseResponse<List<AcquistiDto>> response = new BaseResponse<>();

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

    // --------------------------- GET BY ID ACQUISTI
    @GetMapping(produces = "application/json", path = "/{id}")
    public BaseResponse<List<AcquistiDto>> getById(@PathVariable int id) {

        BaseResponse<List<AcquistiDto>> response = new BaseResponse<>();

        List<AcquistiDto> listDto = new ArrayList<>();
        AcquistiDto ordineDto = acquistiService.findByPurchaseId(id);

        listDto.add(ordineDto);

        response.setData(listDto);
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());
        return response;
    }

    // --------------------------- INSERT ACQUISTI
    @PostMapping(consumes = "application/json", produces = "application/json", path = "/save")
    public BaseResponse<Boolean> inserisci(@RequestBody ProdottoInput[] piDto) {

        // creo aggoetto acquisto come (dto)
        AcquistiInsertDto dto = new AcquistiInsertDto();
        dto.setBillDate(new Date().toString());
        dto.setBillNumber(new Random().nextInt(100000));

        BaseResponse<Boolean> response = new BaseResponse<>();
        // inserito acquisto
        if (acquistiService.insertAcquisto(dto)) {
            int idAcquisto = acquistiService.getLastId();
            response.setResult(acquistiService.insertPurchasesItems(piDto));
        }

        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

    // --------------------------- UPDATE ACQUISTI
    @PatchMapping(consumes = "application/json", produces = "application/json", path = "/update/{id}")
    public BaseResponse<Boolean> updateById(@RequestBody AcquistiDto dto, @PathVariable int id) {

        BaseResponse<Boolean> response = new BaseResponse<>();

        response.setResult(acquistiService.modifyAcquisto(dto, id));
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

    // --------------------------- DELETE ACQUISTI
    @DeleteMapping(produces = "application/json", path = "/delete/{id}")
    public BaseResponse<Boolean> deleteById(@PathVariable int id) {

        BaseResponse<Boolean> response = new BaseResponse<>();

        response.setResult(acquistiService.deleteAcquisto(id));
        response.setDate(new Date());
        response.setErrors(new ArrayList<>());
        response.setSuccess(HttpStatus.OK.value());

        return response;
    }

}
