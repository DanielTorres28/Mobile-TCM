package com.example.mobiletcm.Serviços;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobiletcm.R;

public class Servicos2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos2);
        getSupportActionBar().setTitle("Serviços");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar
    }
}