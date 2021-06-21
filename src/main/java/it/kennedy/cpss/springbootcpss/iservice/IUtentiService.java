package it.kennedy.cpss.springbootcpss.iservice;

import it.kennedy.cpss.springbootcpss.Dto.BaseResponse;
import it.kennedy.cpss.springbootcpss.Dto.UtentiDto;
import it.kennedy.cpss.springbootcpss.Dto.input.SIUserInput;

public interface IUtentiService {
    public BaseResponse<UtentiDto> GetUtente(SIUserInput userInput);
}
