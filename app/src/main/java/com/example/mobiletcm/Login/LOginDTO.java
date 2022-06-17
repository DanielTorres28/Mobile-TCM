package com.example.mobiletcm.Login;

public class LOginDTO {
    //Atributos
    private int id;
    private String Nome;
    private String Email;
    private String senha;

    public LOginDTO() {
        //construtores
    }
    public LOginDTO(int id,String Nome,  String Email, String senha) {
        this.id = id;
        this.Email = Nome;
        this.Email = Email;
        this.senha = senha;
    }


    //Getters e Setters
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNome() { return Nome; }

    public void setNome(String Nome) { this.Nome = Nome; }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //metodo de autenticação vai verificar se os campos são autênticos (se existe ou não)
    public boolean autenticar(String usuario, String senha){ //o que eu quero autenticar (no caso é usuario e senha)
        if(this.getEmail().equals(usuario)&& this.getSenha().equals(senha)){ //se o GetUsuario for igual usuario ele está certo e comparar o GetSenha com a senha se for igual está certo
            return true; //se der tudo certo ele vai me retornar True(Verdade)
        }
        return false; //se não der certo ele me retorna false(falso)
    }

}


