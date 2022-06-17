package com.example.mobiletcm.Socio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiletcm.R;

public class CadastrarSocioActivity extends AppCompatActivity {
    EditText nomeSoc, emailSoc, SenhaSoc; //declarando
    Button CadastrarSoc, ConsultarSoc; //declarando

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_socio);
        getSupportActionBar().setTitle("Cadastro de Sócios");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

        nomeSoc = findViewById(R.id.nomeSoc);
        emailSoc = findViewById(R.id.emailSoc);
        SenhaSoc = findViewById(R.id.SenhaSoc);
        CadastrarSoc = findViewById(R.id.CadastrarSoc);
        ConsultarSoc = findViewById(R.id.ConsultarSoc);

        //Evento de click do botão
        CadastrarSoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarDTO dtoCadastrar= new CadastrarDTO();
                dtoCadastrar.setNome(nomeSoc.getText().toString());
                dtoCadastrar.setEmail(emailSoc.getText().toString());
                dtoCadastrar.setSenha(SenhaSoc.getText().toString());

                CadastrarSocDAO daoCadastrar = new CadastrarSocDAO(CadastrarSocioActivity.this);
                try{//vou tentar Inserir
                    long linhasInseridas = daoCadastrar.inserir(dtoCadastrar);
                    if (linhasInseridas>0){
                        Toast.makeText(CadastrarSocioActivity.this, "Inserido com Sucesso", Toast.LENGTH_SHORT).show();
                        //Intent main = new Intent(CadastrarSocioActivity.this, ConsultasActivity.class);
                        //startActivity(main);
                    }
                    else{
                        Toast.makeText(CadastrarSocioActivity.this, "Não foi possível inserir", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(CadastrarSocioActivity.this, "Erro ao Inserir" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        ConsultarSoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consultar = new Intent(getApplicationContext(), ConsultasActivity.class);
                startActivity(consultar);
            }
        });

    }
}