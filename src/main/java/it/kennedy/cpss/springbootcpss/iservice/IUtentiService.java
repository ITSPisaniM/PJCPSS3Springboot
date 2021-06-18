package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.dto.input.SIUserInput;

public interface IUtentiService {
    public BaseResponse<UtentiDto> GetUtente(SIUserInput userInput);
}
