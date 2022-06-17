package com.example.mobiletcm.Cliente;

import androidx.annotation.NonNull;

public class CadastrarClienteDTO{
    private int id;
    private String nome, sobrenome, email, telefone,cpf, senha;

    //construtor para poder instanciar as informações de uma vez
    public CadastrarClienteDTO(String nome, String sobrenome, String email, String telefone, String cpf, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
    }



    //construtor vazio
    public CadastrarClienteDTO() {


    }

    //ele pertence a classe mãe de todas as classes do java que é a classe object
    //vou retornar o que eu quero
    @NonNull
    @Override
    public String toString(){
        return "Nome: " + nome + " - Sobrenome: " + sobrenome + " - Email: " + email + " - Telefone: " + telefone + " - CPF: " + cpf + " - Senha: " + senha;
    }



    //Getters and Setters
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
