package com.example.appcarro;

public class Produto {
    public String id;
    public String nome,categoria, estado, cor, idProduto;

    public Produto() {
    }

    public Produto(String id, String nome, String categoria, String estado, String cor) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.estado = estado;
        this.cor = cor;

    }

    @Override
    public String toString(){
        return "Nome: " + nome + " | " + "Marca: " + categoria + " | " + "Estado: " + estado + " | " + "Cor: " + cor;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
