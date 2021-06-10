package it.kennedy.cpss.springbootcpss.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("api/utente")
public class UtenteController {

    @GetMapping(path = "login")
    public void test(){

    }
}
