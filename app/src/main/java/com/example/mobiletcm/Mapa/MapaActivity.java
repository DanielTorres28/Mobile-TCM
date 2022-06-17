package com.example.mobiletcm.Mapa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobiletcm.R;

public class MapaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        getSupportActionBar();
        getSupportActionBar().setTitle("Mapa");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

    }
}