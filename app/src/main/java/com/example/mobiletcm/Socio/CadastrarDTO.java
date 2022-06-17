package com.example.mobiletcm.Socio;

import androidx.annotation.NonNull;

public class CadastrarDTO{
    private int id;
    private String nome, email, senha;


    //construtor Vazio
    public CadastrarDTO() {

    }


    @NonNull
    @Override
    public String toString(){
        return "Nome: " + nome + " - Email: " + email
                + " - Senha: " + senha;

    }

    //construtor
    public CadastrarDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

