package it.kennedy.cpss.springbootcpss.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Errors {
    public final String description;
    public final String classe;

    @RequiredArgsConstructor
    public static class OrdiniError extends Error {
        public final String prodotto;
        public final int quantita;
        public final String ordine;
    }
}
