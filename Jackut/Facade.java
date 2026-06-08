package Jackut;

import Jackut.services.AmizadeService;
import Jackut.services.Database;
import Jackut.services.RecadoService;
import Jackut.services.UsuarioService;

/**
 * Fachada publica usada pelos testes de aceitacao.
 *
 * Esta classe apenas encaminha chamadas para os servicos do projeto.
 */
public class Facade {
    private Database db = Database.getInstance();
    private UsuarioService usuarioService = new UsuarioService();
    private AmizadeService amizadeService = new AmizadeService();
    private RecadoService recadoService = new RecadoService();

    public void zerarSistema() { db.zerar(); }
    public void encerrarSistema() { db.salvar(); }

    public void criarUsuario(String login, String senha, String nome) {
        usuarioService.criarUsuario(login, senha, nome);
    }

    public String abrirSessao(String login, String senha) {
        return usuarioService.abrirSessao(login, senha);
    }

    public String getAtributoUsuario(String login, String atributo) {
        return usuarioService.getAtributoUsuario(login, atributo);
    }

    public void editarPerfil(String id, String atributo, String valor) {
        usuarioService.editarPerfil(id, atributo, valor);
    }

    public void adicionarAmigo(String id, String amigo) {
        amizadeService.adicionarAmigo(id, amigo);
    }

    public String ehAmigo(String login, String amigo) {
        return amizadeService.ehAmigo(login, amigo);
    }

    public String getAmigos(String login) {
        return amizadeService.getAmigos(login);
    }

    public void enviarRecado(String id, String destinatario, String mensagem) {
        recadoService.enviarRecado(id, destinatario, mensagem);
    }

    public String lerRecado(String id) {
        return recadoService.lerRecado(id);
    }
}
