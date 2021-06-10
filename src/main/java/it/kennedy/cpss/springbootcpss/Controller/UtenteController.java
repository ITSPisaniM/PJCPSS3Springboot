package it.kennedy.cpss.springbootcpss.Controller;

import it.kennedy.cpss.springbootcpss.Dto.Input.SIUserInput;
import it.kennedy.cpss.springbootcpss.Dto.SOBase;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.Service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/utente")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public SOBase<UtentiDto> login(@RequestBody SIUserInput user){
        SOBase<UtentiDto> res;

        res = utenteService.GetUtente(user);

        return res;
    }
}
