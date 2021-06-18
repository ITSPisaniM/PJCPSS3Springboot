package it.kennedy.cpss.springbootcpss.Controller;

import it.kennedy.cpss.springbootcpss.Config.JwtTokenUtil;
import it.kennedy.cpss.springbootcpss.Dao.UtentiDao;
import it.kennedy.cpss.springbootcpss.Dto.Input.SIUserInput;
import it.kennedy.cpss.springbootcpss.Dto.SOBase;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.Service.IUtenteService;
import it.kennedy.cpss.springbootcpss.Service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("api/utente")
@RequiredArgsConstructor
public class UtenteController {
    @Autowired
    IUtenteService utenteService;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public SOBase<UtentiDto> login(@RequestBody SIUserInput request){
        SOBase<UtentiDto> res = new SOBase<>();
        res.data = new ArrayList<>();
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username, request.password));

        UtentiDao dao = (UtentiDao) authenticate.getPrincipal();
        UtentiDto dto = new UtentiDto();
        dto.Username = dao.getUsername();
        dto.newToken = jwtTokenUtil.generateAccessToken(dao);

        res.data.add(dto);

        return res;
    }

    @GetMapping("/test")
    public String test(){
        return "ok";
    }
}
