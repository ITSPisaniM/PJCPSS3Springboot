package it.kennedy.cpss.springbootcpss.ServiceImpl;

import it.kennedy.cpss.springbootcpss.Dao.UtentiDao;
import it.kennedy.cpss.springbootcpss.Dto.Errors;
import it.kennedy.cpss.springbootcpss.Dto.Input.SIUserInput;
import it.kennedy.cpss.springbootcpss.IService.IUtentiService;
import it.kennedy.cpss.springbootcpss.Dto.SOBase;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.Repository.IUtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public SOBase<UtentiDto> GetUtente(SIUserInput userInput) {
        SOBase<UtentiDto> res = new SOBase<>();

        if(userInput.username == null || userInput.password == null) {
            res.success = false;
            Errors e = new Errors();
            e.description = "password o utente nullo";
            e.classe = "UtenteService";
            res.errors.add(e);

            return res;
        }

        UtentiDao dao = utenteRepository.getByUsernameAndPassword(userInput.username, userInput.password);

        UtentiDto dto = new UtentiDto();
        dto.Username = dao.getUsername();
        dto.UserID = dao.getUserID();

        res.data = new ArrayList<>();
        res.data.add(dto);
        res.date = new Date();
        res.success = true;

        return res;
    }
}
