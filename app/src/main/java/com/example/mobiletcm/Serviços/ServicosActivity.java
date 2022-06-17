package com.example.mobiletcm.Serviços;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobiletcm.R;

public class ServicosActivity extends AppCompatActivity {
        TextView VerMais;
        Button CadastrarServicos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        getSupportActionBar().setTitle("Serviços");//mudando o nome da actionbar
        setContentView(R.layout.activity_servicos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

        VerMais  = findViewById(R.id.VerMais);
        VerMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mais = new Intent(getApplicationContext(), Servicos2Activity.class);
                startActivity(mais);
            }
        });

        CadastrarServicos = findViewById(R.id.CadastrarServicos);
        CadastrarServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent servicos = new Intent(getApplicationContext(), CadastrarServicosActivity.class);
                startActivity(servicos);
            }
        });
    }
}