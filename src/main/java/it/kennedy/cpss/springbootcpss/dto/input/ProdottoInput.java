package it.kennedy.cpss.springbootcpss.dto.input;

import it.kennedy.cpss.springbootcpss.dto.AcquistiProdottiDto;
import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProdottoInput {
    List<test> prodotto;

    private static class test {
        public ProdottiDto prodotto;
        public int quantita;
    }
}
