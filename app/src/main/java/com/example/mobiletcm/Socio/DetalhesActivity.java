package com.example.mobiletcm.Socio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiletcm.R;

public class DetalhesActivity extends AppCompatActivity {
     EditText AltnomeSoc, AltemailSoc, AltSenhaSoc;
     Button AlterarSoc;
     int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        getSupportActionBar().setTitle("Detalhes do Sócio");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

        AltnomeSoc = findViewById(R.id.AltnomeSoc);
        AltemailSoc = findViewById(R.id.AltemailSoc);
        AltSenhaSoc = findViewById(R.id.AltSenhaSoc);
        AlterarSoc  = findViewById(R.id.AlterarSoc);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        AltnomeSoc.setText(bundle.getString("nome"));
        AltemailSoc.setText(bundle.getString("Email"));
        AltSenhaSoc.setText(bundle.getString("Senha"));

        AlterarSoc.setOnClickListener(new View.OnClickListener() {
            @Override //alterar o socio lá do banco de dados
            public void onClick(View view) {
                CadastrarDTO dtoCadastrar= new CadastrarDTO();
                dtoCadastrar.setId(id);
                dtoCadastrar.setNome(AltnomeSoc.getText().toString());
                dtoCadastrar.setEmail(AltemailSoc.getText().toString());
                dtoCadastrar.setSenha(AltSenhaSoc.getText().toString());

                CadastrarSocDAO daoCadastrar = new CadastrarSocDAO(DetalhesActivity.this);
                try{//vou tentar Inserir os dados para o banco de dados
                    long linhasInseridas = daoCadastrar.alterar(dtoCadastrar);
                    if (linhasInseridas>0){
                        Toast.makeText(DetalhesActivity.this, "Alterado com Sucesso", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(DetalhesActivity.this, ConsultasActivity.class);
                        startActivity(main);
                    }
                    else{
                        Toast.makeText(DetalhesActivity.this, "Não foi possível Alterar", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(DetalhesActivity.this, "Erro ao Alterar" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}