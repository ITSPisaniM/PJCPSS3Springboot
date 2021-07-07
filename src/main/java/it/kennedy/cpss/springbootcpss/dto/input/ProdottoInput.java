package it.kennedy.cpss.springbootcpss.dto.input;

import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import lombok.Data;

@Data
public class ProdottoInput {
    public ProdottiDto prodotti;
    public Integer quantita;
}
