package Jackut.services;

import Jackut.exceptions.JackutException;
import Jackut.models.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class Database {
    private static Database instance;

    private final File arquivo;
    private Map<String, Usuario> usuarios;
    private Map<String, String> sessoes;
    private int proximoIdSessao;

    private Database() {
        this.arquivo = new File("jackut.dat");
        this.usuarios = carregarUsuarios();
        this.sessoes = new LinkedHashMap<String, String>();
        this.proximoIdSessao = 1;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void zerar() {
        usuarios.clear();
        sessoes.clear();
        proximoIdSessao = 1;
        if (arquivo.exists() && !arquivo.delete()) {
            throw new JackutException("Nao foi possivel apagar os dados.");
        }
    }

    public void salvar() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
            try {
                out.writeObject(usuarios);
            } finally {
                out.close();
            }
        } catch (Exception e) {
            throw new JackutException("Nao foi possivel salvar os dados.");
        }
    }

    public boolean existeUsuario(String login) {
        return usuarios.containsKey(login);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.getLogin(), usuario);
    }

    public Usuario getUsuario(String login) {
        Usuario usuario = usuarios.get(login);
        if (usuario == null) {
            throw new JackutException("Usuário não cadastrado.");
        }
        return usuario;
    }

    public String abrirSessao(String login) {
        String id = String.valueOf(proximoIdSessao);
        proximoIdSessao++;
        sessoes.put(id, login);
        return id;
    }

    public Usuario getUsuarioDaSessao(String id) {
        String login = sessoes.get(id);
        if (login == null) {
            throw new JackutException("Usuário não cadastrado.");
        }
        return getUsuario(login);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Usuario> carregarUsuarios() {
        if (!arquivo.exists()) {
            return new LinkedHashMap<String, Usuario>();
        }

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
            try {
                return (Map<String, Usuario>) in.readObject();
            } finally {
                in.close();
            }
        } catch (Exception e) {
            return new LinkedHashMap<String, Usuario>();
        }
    }
}
