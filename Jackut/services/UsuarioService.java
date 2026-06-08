package Jackut.services;

import Jackut.exceptions.JackutException;
import Jackut.models.Usuario;

/**
 * Servico de usuarios, perfis e sessoes.
 */
public class UsuarioService {
    private Database db = Database.getInstance();

    public void criarUsuario(String login, String senha, String nome) {
        Validador.validarTexto(login, "Login inválido.");
        Validador.validarTexto(senha, "Senha inválida.");

        if (db.existeUsuario(login)) {
            throw new JackutException("Conta com esse nome já existe.");
        }

        db.adicionarUsuario(new Usuario(login, senha, nome));
    }

    public String abrirSessao(String login, String senha) {
        if (login == null || login.trim().length() == 0 || senha == null || senha.length() == 0 || !db.existeUsuario(login)) {
            throw new JackutException("Login ou senha inválidos.");
        }

        Usuario usuario = db.getUsuario(login);
        if (!usuario.senhaConfere(senha)) {
            throw new JackutException("Login ou senha inválidos.");
        }
        return db.abrirSessao(login);
    }

    public String getAtributoUsuario(String login, String atributo) {
        if (!db.existeUsuario(login)) {
            throw new JackutException("Usuário não cadastrado.");
        }

        Usuario usuario = db.getUsuario(login);
        String valor = usuario.getAtributo(atributo);
        if (valor == null) {
            throw new JackutException("Atributo não preenchido.");
        }
        return valor;
    }

    public void editarPerfil(String id, String atributo, String valor) {
        Usuario usuario = db.getUsuarioDaSessao(id);
        Validador.validarTexto(atributo, "Atributo inválido.");
        if (valor == null) {
            throw new JackutException("Valor inválido.");
        }
        usuario.editarPerfil(atributo, valor);
    }
}
