package com.example.mobiletcm.Sobre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobiletcm.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        getSupportActionBar().setTitle("Sobre");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar
    }
}