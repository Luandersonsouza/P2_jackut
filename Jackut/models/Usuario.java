package Jackut.models;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String login;
    private final String senha;
    private final Map<String, String> perfil;
    private final Set<String> amigos;
    private final Set<String> convitesRecebidos;
    private final Queue<Recado> recados;

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.perfil = new LinkedHashMap<String, String>();
        this.amigos = new LinkedHashSet<String>();
        this.convitesRecebidos = new LinkedHashSet<String>();
        this.recados = new LinkedList<Recado>();
        perfil.put("nome", nome);
    }

    public String getLogin() {
        return login;
    }

    public boolean senhaConfere(String senhaTentativa) {
        return senha.equals(senhaTentativa);
    }

    public void editarPerfil(String atributo, String valor) {
        perfil.put(atributo, valor);
    }

    public String getAtributo(String atributo) {
        return perfil.get(atributo);
    }

    public void receberConvite(String loginOrigem) {
        convitesRecebidos.add(loginOrigem);
    }

    public boolean temConviteDe(String loginOrigem) {
        return convitesRecebidos.contains(loginOrigem);
    }

    public void removerConviteDe(String loginOrigem) {
        convitesRecebidos.remove(loginOrigem);
    }

    public void adicionarAmigoConfirmado(String loginAmigo) {
        amigos.add(loginAmigo);
    }

    public boolean ehAmigoDe(String loginAmigo) {
        return amigos.contains(loginAmigo);
    }

    public Set<String> getAmigos() {
        return Collections.unmodifiableSet(amigos);
    }

    public void receberRecado(Recado recado) {
        recados.add(recado);
    }

    public Recado lerProximoRecado() {
        return recados.poll();
    }
}
