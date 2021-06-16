package it.kennedy.cpss.springbootcpss.IService;

import it.kennedy.cpss.springbootcpss.Dto.Input.SIUserInput;
import it.kennedy.cpss.springbootcpss.Dto.SOBase;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;

public interface IUtentiService {
    public SOBase<UtentiDto> GetUtente(SIUserInput userInput);
}
