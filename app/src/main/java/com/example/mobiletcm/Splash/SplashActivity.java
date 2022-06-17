package com.example.mobiletcm.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.mobiletcm.R;

import java.util.Timer;
import java.util.TimerTask;

import com.example.mobiletcm.Login.TelaLogin;

public class SplashActivity extends AppCompatActivity {

private static  final int TEMPO = 3500; //em mileSegundos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // Esconder a action bar
        setContentView(R.layout.activity_splash);
        inicializarTelaLogin();//estou chamando o metodo
    }

    private void inicializarTelaLogin(){
       new Timer().schedule(new TimerTask() {
           @Override
           public void run() {
               finish();//fechando o que está aberto
               Intent telaLogin = new Intent(getApplicationContext(), TelaLogin.class);
               startActivity(telaLogin);
           }
       },TEMPO);//a minha Task (tarefa) estou usando ela aqui fora
    }
}