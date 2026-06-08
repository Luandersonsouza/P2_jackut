package Jackut.services;

import Jackut.exceptions.JackutException;

public class Validador {
    private Validador() {
    }

    public static void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().length() == 0) {
            throw new JackutException(mensagem);
        }
    }
}
