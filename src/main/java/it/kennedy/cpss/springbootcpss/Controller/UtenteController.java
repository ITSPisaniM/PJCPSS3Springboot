package it.kennedy.cpss.springbootcpss.Controller;

import it.kennedy.cpss.springbootcpss.Dto.SOBase;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/utente")
public class UtenteController {

    @GetMapping()
    public SOBase<UtentiDto> login(){
        SOBase<UtentiDto> res = new SOBase<>();

        res.success = true;

        return res;
    }
}
