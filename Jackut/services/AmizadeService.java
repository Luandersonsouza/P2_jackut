package Jackut.services;

import Jackut.exceptions.JackutException;
import Jackut.models.Usuario;

import java.util.Iterator;

/**
 * Servico responsavel pelos relacionamentos de amizade.
 */
public class AmizadeService {
    private Database db = Database.getInstance();

    public void adicionarAmigo(String id, String amigo) {
        Usuario usuario = db.getUsuarioDaSessao(id);

        if (usuario.getLogin().equals(amigo)) {
            throw new JackutException("Usuário não pode adicionar a si mesmo como amigo.");
        }

        Usuario usuarioAmigo = db.getUsuario(amigo);

        if (usuario.ehAmigoDe(amigo)) {
            throw new JackutException("Usuário já está adicionado como amigo.");
        }
        if (usuarioAmigo.temConviteDe(usuario.getLogin())) {
            throw new JackutException("Usuário já está adicionado como amigo, esperando aceitação do convite.");
        }

        if (usuario.temConviteDe(amigo)) {
            usuario.removerConviteDe(amigo);
            usuario.adicionarAmigoConfirmado(amigo);
            usuarioAmigo.adicionarAmigoConfirmado(usuario.getLogin());
        } else {
            usuarioAmigo.receberConvite(usuario.getLogin());
        }
    }

    public String ehAmigo(String login, String amigo) {
        Usuario usuario = db.getUsuario(login);
        db.getUsuario(amigo);
        return String.valueOf(usuario.ehAmigoDe(amigo));
    }

    public String getAmigos(String login) {
        Usuario usuario = db.getUsuario(login);
        StringBuilder builder = new StringBuilder("{");
        Iterator<String> iterator = usuario.getAmigos().iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
