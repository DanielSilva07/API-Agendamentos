package com.daniel.silva.exceptions;

public class AgendamentoJaExiste extends RuntimeException {

    public AgendamentoJaExiste(String message) {
        super(message);
    }
}
