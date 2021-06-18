package it.kennedy.cpss.springbootcpss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.kennedy.cpss.springbootcpss.IService.IUtentiService;
import it.kennedy.cpss.springbootcpss.dto.SOBase;
import it.kennedy.cpss.springbootcpss.dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.dto.input.SIUserInput;

@RestController()
@RequestMapping("api/utente")
public class UtentiController {
    @Autowired
    IUtentiService utenteService;

    @PostMapping("/login")
    public SOBase<UtentiDto> login(@RequestBody SIUserInput user) {
        SOBase<UtentiDto> res;

        res = utenteService.GetUtente(user);

        return res;
    }
}
