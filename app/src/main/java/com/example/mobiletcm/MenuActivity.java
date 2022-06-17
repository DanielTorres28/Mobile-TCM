package com.example.mobiletcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mobiletcm.Cliente.ClienteActivity;
import com.example.mobiletcm.Mapa.MapaActivity;
import com.example.mobiletcm.Serviços.ServicosActivity;
import com.example.mobiletcm.Sobre.SobreActivity;
import com.example.mobiletcm.Socio.CadastrarSocioActivity;
import com.example.mobiletcm.Socio.ConsultasActivity;

public class MenuActivity extends AppCompatActivity {
    ImageView botao_servicos;
    ImageView botao_sobre;
    ImageView botao_mapa;
    ImageView botao_consultas;
    ImageView botao_cliente;
    ImageView botao_socios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Esconde a actionBar
        setContentView(R.layout.activity_menu);

        botao_servicos = findViewById(R.id.botao_servicos);
        botao_sobre = findViewById(R.id.botao_sobre);
        botao_mapa = findViewById(R.id.botao_mapa);
        botao_consultas = findViewById(R.id.botao_consultas);
        botao_cliente = findViewById(R.id.botao_cliente);
        botao_socios = findViewById(R.id.botao_socios);

        //funçao do click para a tela serviços
        botao_servicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServicosActivity.class);
                startActivity(intent);
            }
        });


        //funçao do click para a tela sobre
        botao_sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SobreActivity.class);
                startActivity(intent);
            }
        });

        //funçao do click para a tela Mapa
        botao_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapaActivity.class);
                startActivity(intent);
            }
        });


        //funçao do click para a tela consultas
        botao_consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultasActivity.class);
                startActivity(intent);
            }
        });

        //funçao do click para a tela socios
        botao_socios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastrarSocioActivity.class);
                startActivity(intent);
            }
        });


        //funçao do click para a tela cliente
        botao_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClienteActivity.class);
                startActivity(intent);
            }
        });
    }
}