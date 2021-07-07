package it.kennedy.cpss.springbootcpss.dao;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantitaRicaviDao {
    private LocalDate giorno;
    private int quantita;
    private double ricavi;
}
