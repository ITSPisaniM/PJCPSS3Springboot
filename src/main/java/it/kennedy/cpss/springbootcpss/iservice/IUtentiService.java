package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.SOBase;
import it.kennedy.cpss.springbootcpss.dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.dto.input.SIUserInput;

public interface IUtentiService {
    public SOBase<UtentiDto> getUtente(SIUserInput userInput);
}
