package it.kennedy.cpss.springbootcpss.dto.input;

import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import lombok.Data;

import java.util.List;

@Data
public class ProdottoInput {
    List<ProdottiItems> prodotto;

    public static class ProdottiItems {
        public ProdottiDto prodotto;
        public int quantita;
    }
}


