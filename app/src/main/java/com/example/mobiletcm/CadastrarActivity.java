package com.example.mobiletcm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiletcm.Login.LOginDTO;
import com.example.mobiletcm.Socio.CadastrarDTO;
import com.example.mobiletcm.Socio.CadastrarSocDAO;
import com.example.mobiletcm.Socio.CadastrarSocioActivity;

public class CadastrarActivity extends AppCompatActivity {
    EditText nomeSoc2, emailSoc2, senhaSoc2; //declarando
    Button cadastrarSoc2; //declarando

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        getSupportActionBar().setTitle("Cadastro de Sócios");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

        nomeSoc2= findViewById(R.id.nomeSoc2);
        emailSoc2 = findViewById(R.id.emailSoc2);
        senhaSoc2 = findViewById(R.id.senhaSoc2);
        cadastrarSoc2 = findViewById(R.id.cadastrarSoc2);

        //Evento de click do botão
        cadastrarSoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarDTO dtoCadastrar= new CadastrarDTO();
                dtoCadastrar.setNome(nomeSoc2.getText().toString());
                dtoCadastrar.setEmail(emailSoc2.getText().toString());
                dtoCadastrar.setSenha(senhaSoc2.getText().toString());

                CadastrarSocDAO daoCadastrar = new CadastrarSocDAO(CadastrarActivity.this);
                try{//vou tentar Inserir
                    long linhasInseridas = daoCadastrar.inserir(dtoCadastrar);
                    if (linhasInseridas>0){
                        Toast.makeText(CadastrarActivity.this, "Inserido com Sucesso", Toast.LENGTH_SHORT).show();
                        //Intent main = new Intent(CadastrarSocioActivity.this, ConsultasActivity.class);
                        //startActivity(main);
                    }
                    else{
                        Toast.makeText(CadastrarActivity.this, "Não foi possível inserir", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(CadastrarActivity.this, "Erro ao Inserir" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}