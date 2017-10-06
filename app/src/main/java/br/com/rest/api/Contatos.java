package br.com.rest.api;

import java.util.ArrayList;
import java.util.List;

public class Contatos {

    private List<Contato> contatos = new ArrayList<>();

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
