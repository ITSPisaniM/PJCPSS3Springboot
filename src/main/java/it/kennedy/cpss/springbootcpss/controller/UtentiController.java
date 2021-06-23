package it.kennedy.cpss.springbootcpss.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.kennedy.cpss.springbootcpss.config.JwtTokenUtil;
import it.kennedy.cpss.springbootcpss.dao.UtentiDao;
import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.dto.input.SIUserInput;
import it.kennedy.cpss.springbootcpss.iservice.IUtentiService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("api/utente")
@RequiredArgsConstructor
public class UtentiController {
    @Autowired
    IUtentiService utenteService;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public BaseResponse<UtentiDto> login(@RequestBody SIUserInput request, HttpServletResponse response) {
        BaseResponse<UtentiDto> res = new BaseResponse<>();
        res.data = new ArrayList<>();
        var authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username, request.password));

        UtentiDao dao = (UtentiDao) authenticate.getPrincipal();
        var dto = new UtentiDto();
        dto.Username = dao.getUsername();
        dto.newToken = jwtTokenUtil.generateAccessToken(dao);

        res.data.add(dto);
        response.setHeader("newToken", dto.getNewToken());
        return res;
    }
}
