package com.example.mobiletcm.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiletcm.CadastrarActivity;
import com.example.mobiletcm.MenuActivity;
import com.example.mobiletcm.R;

import com.example.mobiletcm.Socio.CadastrarDTO;
import com.example.mobiletcm.Socio.CadastrarSocDAO;
import com.example.mobiletcm.Socio.CadastrarSocioActivity;
import com.example.mobiletcm.Socio.ConsultasActivity;

public class TelaLogin extends AppCompatActivity {
    EditText username , password; //declarando
    Button buttonLoginEnviar;
    LOginDTO lOginDTO; //declarei
    //CadastrarDTO cadastrarDTO; //declarei
    TextView cadastroSoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Esconde a actionBar
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        buttonLoginEnviar = findViewById(R.id.login);

        lOginDTO = new LOginDTO(1, "Daniel", "daniel@hotmail.com", "123456"); //passei os valores lá da LoginDTO

        buttonLoginEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lOginDTO.autenticar(username.getText().toString(), password.getText().toString())) { //preciso passar os valores, vai converter para string e passa e vai comparar com os valores que declarei logo ali em cima
                    Intent telaMenu = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(telaMenu);
                    Toast.makeText(getApplicationContext(), "Logado com Sucesso", Toast.LENGTH_SHORT).show(); //vai trazer a mensagem

                } else {
                    Toast.makeText(getApplicationContext(), "Insira um email ou senha", Toast.LENGTH_SHORT).show(); //vai trazer a mensagem
                }
            }

        });

        cadastroSoc = findViewById(R.id.cadastroSoc);
        //funçao do click e chamando a tela de cadastrar
        cadastroSoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastrarActivity.class);
                startActivity(intent);
            }
        });
    }
}