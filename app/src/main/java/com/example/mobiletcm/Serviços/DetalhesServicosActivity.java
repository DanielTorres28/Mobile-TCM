package com.example.mobiletcm.Serviços;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiletcm.R;
import com.example.mobiletcm.Socio.ConsultasActivity;

public class DetalhesServicosActivity extends AppCompatActivity {
    int id;
    EditText nomeProjeto2, descricao2;
    Button cadastrarservico2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servicos);
        getSupportActionBar().setTitle("Detalhes do Serviço");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

        nomeProjeto2 = findViewById(R.id.nomeProjeto2);
        descricao2 = findViewById(R.id.descricao2);
        cadastrarservico2 = findViewById(R.id.cadastrarservico2);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        nomeProjeto2.setText(bundle.getString("Nome do projeto"));
        descricao2 .setText(bundle.getString("Descrição"));


        cadastrarservico2.setOnClickListener(new View.OnClickListener() {
            @Override //alterar o socio lá do banco de dados
            public void onClick(View view) {
                ServicosDTO dtoservico= new ServicosDTO();
                dtoservico.setId(id);
                dtoservico.setNomeProjeto(nomeProjeto2.getText().toString());
                dtoservico.setDescricao(descricao2.getText().toString());

                ServicosDAO daoservico = new ServicosDAO(DetalhesServicosActivity.this);
                try{//vou tentar Inserir os dados para o banco de dados
                    long linhasInseridas = daoservico.alterarServico(dtoservico);
                    if (linhasInseridas>0){
                        Toast.makeText(DetalhesServicosActivity.this, "Alterado com Sucesso", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(DetalhesServicosActivity.this, ConsultasActivity.class);
                        startActivity(main);
                    }
                    else{
                        Toast.makeText(DetalhesServicosActivity.this, "Não foi possível Alterar", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(DetalhesServicosActivity.this, "Erro ao Alterar" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}