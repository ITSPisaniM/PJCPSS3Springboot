package it.kennedy.cpss.springbootcpss.dto.input;

import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import lombok.Data;

import java.util.Map;

@Data
public class ProdottoInput {
    public Map<ProdottiDto, Integer> prodotti;
}
