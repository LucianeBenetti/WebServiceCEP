package com.example.webservicecep.model;

public class Endereco {
    private String Cep;
    private String rua;
    private String cidade;
    private String uf;

    public Endereco() {
    }

    public Endereco(String cep, String rua, String cidade, String uf) {
        Cep = cep;
        this.rua = rua;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
