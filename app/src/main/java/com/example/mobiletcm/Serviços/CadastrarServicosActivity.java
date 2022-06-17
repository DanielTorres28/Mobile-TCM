package com.example.mobiletcm.Serviços;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiletcm.R;
import com.example.mobiletcm.Socio.CadastrarDTO;
import com.example.mobiletcm.Socio.CadastrarSocDAO;
import com.example.mobiletcm.Socio.CadastrarSocioActivity;

public class CadastrarServicosActivity extends AppCompatActivity {
    EditText NomeProjeto,descricao;
    Button cadastrarservico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servicos);
        getSupportActionBar().setTitle("Cadastro de Serviços");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

        NomeProjeto = findViewById(R.id.NomeProjeto);
        descricao = findViewById(R.id.descricao);
        cadastrarservico = findViewById(R.id.cadastrarservico);


        //Evento de click do botão
        cadastrarservico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServicosDTO dtoservicos= new ServicosDTO();
                dtoservicos.setNomeProjeto(NomeProjeto.getText().toString());
                dtoservicos.setDescricao(descricao.getText().toString());


                ServicosDAO daoservicos = new ServicosDAO(CadastrarServicosActivity.this);
                try{//vou tentar Inserir
                    long linhasInseridas = daoservicos.inserirServico(dtoservicos);
                    if (linhasInseridas>0){
                        Toast.makeText(CadastrarServicosActivity.this, "Inserido com Sucesso", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(CadastrarServicosActivity.this, ConsultarServicosActivity.class);
                        startActivity(main);
                    }
                    else{
                        Toast.makeText(CadastrarServicosActivity.this, "Não foi possível inserir", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(CadastrarServicosActivity.this, "Erro ao Inserir" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}