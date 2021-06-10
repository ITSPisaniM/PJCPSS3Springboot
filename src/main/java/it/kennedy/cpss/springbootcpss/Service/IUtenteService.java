package it.kennedy.cpss.springbootcpss.Service;

import it.kennedy.cpss.springbootcpss.Dto.Input.SIUserInput;
import it.kennedy.cpss.springbootcpss.Dto.SOBase;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;

public interface IUtenteService {
    public SOBase<UtentiDto> GetUtente(SIUserInput userInput);
}
