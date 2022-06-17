package com.example.mobiletcm.Serviços;

import androidx.annotation.NonNull;

public class ServicosDTO {
        private int id;
        private String nomeProjeto, descricao;


        //construtor Vazio
        public ServicosDTO() {

        }


        @NonNull
        @Override
        public String toString(){
            return "Nome do Projeto: " +  nomeProjeto
                    + " - Descrição: " + descricao;

        }

        //construtor
        public ServicosDTO(String nomeProjeto, String descricao) {
            this.nomeProjeto = nomeProjeto;
            this.descricao = descricao;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
