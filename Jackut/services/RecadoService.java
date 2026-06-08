package Jackut.services;

import Jackut.exceptions.JackutException;
import Jackut.models.Recado;
import Jackut.models.Usuario;

public class RecadoService {
    private Database db = Database.getInstance();

    public void enviarRecado(String id, String destinatario, String mensagem) {
        Usuario remetente = db.getUsuarioDaSessao(id);
        if (remetente.getLogin().equals(destinatario)) {
            throw new JackutException("Usuário não pode enviar recado para si mesmo.");
        }

        Usuario usuarioDestino = db.getUsuario(destinatario);
        if (mensagem == null) {
            throw new JackutException("Mensagem inválida.");
        }
        usuarioDestino.receberRecado(new Recado(remetente.getLogin(), mensagem));
    }

    public String lerRecado(String id) {
        Usuario usuario = db.getUsuarioDaSessao(id);
        Recado recado = usuario.lerProximoRecado();
        if (recado == null) {
            throw new JackutException("Não há recados.");
        }
        return recado.getMensagem();
    }
}
