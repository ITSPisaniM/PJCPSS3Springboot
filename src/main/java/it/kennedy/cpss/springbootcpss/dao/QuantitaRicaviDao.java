package it.kennedy.cpss.springbootcpss.dao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class QuantitaRicaviDao {
    private LocalDate giorno;
    private int quantita;
    private double ricavi;
}
