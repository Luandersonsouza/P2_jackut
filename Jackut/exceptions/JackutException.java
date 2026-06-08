package Jackut.exceptions;

/**
 * Excecao de negocio do Jackut.
 */
public class JackutException extends RuntimeException {
    public JackutException(String mensagem) {
        super(mensagem);
    }
}
