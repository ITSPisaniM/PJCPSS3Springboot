package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.config.JwtProvider;
import it.kennedy.cpss.springbootcpss.dao.UtentiDao;
import it.kennedy.cpss.springbootcpss.dto.Errors;
import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.dto.input.SIUserInput;
import it.kennedy.cpss.springbootcpss.iservice.IUtentiService;
import it.kennedy.cpss.springbootcpss.repository.IUtentiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class UtentiService implements IUtentiService {
    @Autowired
    IUtentiRepository utenteRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public BaseResponse<UtentiDto> GetUtente(SIUserInput userInput) {
        BaseResponse<UtentiDto> res = new BaseResponse<>();

        if (userInput.username == null || userInput.password == null) {
            res = errorHandler(res, "nome utente o password non valorizzati", "UtenteService:GetUtente");

            return res;
        }

        if (userInput.username == null || userInput.password == null) {
            res = errorHandler(res, "nome utente o password non valorizzati", "UtenteService:GetUtente");

            return res;
        }

        UtentiDao dao = utenteRepository.getByUsernameAndPassword(userInput.username, userInput.password);
        if (dao == null) {
            res = errorHandler(res, "utente non trovato", "UtenteService:GetUtente");

            return res;
        }

        UtentiDto dto = new UtentiDto();
        dto.Username = dao.getUsername();
        dto.UserID = dao.getUserId();
        dto.newToken = jwtProvider.createJwt(dao.getUsername());

        res.data = new ArrayList<>();
        res.data.add(dto);
        res.date = new Date();
        res.success = HttpStatus.OK.value();

        return res;
    }

    public BaseResponse errorHandler(BaseResponse res, String errorMessage, String classe) {
        res.success = HttpStatus.FAILED_DEPENDENCY.value();
        Errors e = new Errors();
        e.description = errorMessage;
        e.classe = classe;
        res.errors.add(e);

        return res;
    }
}
