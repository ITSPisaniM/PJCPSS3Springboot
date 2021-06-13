package it.kennedy.cpss.springbootcpss.Service;

import it.kennedy.cpss.springbootcpss.Config.JwtProvider;
import it.kennedy.cpss.springbootcpss.Dao.UtentiDao;
import it.kennedy.cpss.springbootcpss.Dto.Errors;
import it.kennedy.cpss.springbootcpss.Dto.Input.SIUserInput;
import it.kennedy.cpss.springbootcpss.Dto.SOBase;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class UtenteService implements IUtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public SOBase<UtentiDto> GetUtente(SIUserInput userInput) {
        SOBase<UtentiDto> res = new SOBase<>();

        if(userInput.username == null || userInput.password == null) {
            res = errorHandler(res, "nome utente o password non valorizzati", "UtenteService:GetUtente");

            return res;
        }

        UtentiDao dao = utenteRepository.getByUsernameAndPassword(userInput.username, userInput.password);
        if(dao == null) {
            res = errorHandler(res, "utente non trovato", "UtenteService:GetUtente");

            return res;
        }

        UtentiDto dto = new UtentiDto();
        dto.Username = dao.getUsername();
        dto.UserID = dao.getUserID();
        dto.newToken = jwtProvider.createJwt(dao.getUsername());

        res.data = new ArrayList<>();
        res.data.add(dto);
        res.date = new Date();
        res.success = true;

        return res;
    }

    public SOBase errorHandler(SOBase res, String errorMessage, String classe){
        res.success = false;
        Errors e = new Errors();
        e.description = errorMessage;
        e.classe = classe;
        res.errors.add(e);

        return res;
    }
}
