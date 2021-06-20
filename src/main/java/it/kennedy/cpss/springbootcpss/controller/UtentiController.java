package it.kennedy.cpss.springbootcpss.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.kennedy.cpss.springbootcpss.Dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.Dto.input.SIUserInput;
import it.kennedy.cpss.springbootcpss.config.JwtTokenUtil;
import it.kennedy.cpss.springbootcpss.dao.UtentiDao;
import it.kennedy.cpss.springbootcpss.iservice.IUtentiService;
import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("api/utente")
@RequiredArgsConstructor
public class UtentiController {
    @Autowired
    IUtentiService utenteService;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public BaseResponse<UtentiDto> login(@RequestBody SIUserInput request) {
        BaseResponse<UtentiDto> res = new BaseResponse<>();
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
}
