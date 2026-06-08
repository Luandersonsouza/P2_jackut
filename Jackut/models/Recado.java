package Jackut.models;

import java.io.Serializable;

/**
 * Mensagem curta enviada de um usuario para outro.
 */
public class Recado implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String remetente;
    private final String mensagem;

    public Recado(String remetente, String mensagem) {
        this.remetente = remetente;
        this.mensagem = mensagem;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getMensagem() {
        return mensagem;
    }
}
