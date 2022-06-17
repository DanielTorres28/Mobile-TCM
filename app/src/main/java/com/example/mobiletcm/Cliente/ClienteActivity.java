package com.example.mobiletcm.Cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiletcm.MenuActivity;
import com.example.mobiletcm.R;

public class ClienteActivity extends AppCompatActivity {
    Button btnConsultarCliente;
    Button btnCadastrarCliente;
    EditText NomeCliente;
    EditText SobrenomeCliente;
    EditText emailCliente;
    EditText telefoneCliente;
    EditText cpfCliente;
    EditText senhaCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        getSupportActionBar().setTitle("Cadastro de Cliente");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

                NomeCliente = findViewById(R.id.NomeCliente);
                SobrenomeCliente = findViewById(R.id.SobrenomeCliente);
                emailCliente = findViewById(R.id.emailCliente);
                telefoneCliente = findViewById(R.id.telefoneCliente);
                cpfCliente = findViewById(R.id.cpfCliente);
                senhaCliente = findViewById(R.id.senhaCliente);

        //Ação do botão Consultar Cliente
        btnConsultarCliente = findViewById(R.id.btnConsultarCliente);
        btnConsultarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View view) {
                Intent consultar = new Intent(getApplicationContext(), ConsultarCliActivity.class);
                startActivity(consultar);
            }
        });


        //Ação do botão Cadastrar
        btnCadastrarCliente = findViewById(R.id.btnCadastrarCliente);
        btnCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CadastrarClienteDTO clienteDTO = new CadastrarClienteDTO(); //Transferencia de dados
                clienteDTO.setNome(NomeCliente.getText().toString());
                clienteDTO.setSobrenome(SobrenomeCliente.getText().toString());
                clienteDTO.setEmail(emailCliente.getText().toString());
                clienteDTO.setTelefone(telefoneCliente.getText().toString());
                clienteDTO.setCpf(cpfCliente.getText().toString());
                clienteDTO.setSenha(senhaCliente.getText().toString());


                CadastrarClienteDAO daoCliente = new CadastrarClienteDAO(ClienteActivity.this);
                try{//tentando Inserir
                    long linhasInseridas = daoCliente.inserirCliente(clienteDTO);
                    if(linhasInseridas>0){
                        Toast.makeText(ClienteActivity.this,"Inserido com Sucesso", Toast.LENGTH_SHORT).show();//Mensagem de Inserido Com sucesso
                        //Intent inserir = new Intent(ClienteActivity.this, ConsultarCliActivity.class);
                        //startActivity(inserir);
                    }
                    else{
                        Toast.makeText(ClienteActivity.this, "Não foi possível inserir", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(ClienteActivity.this ,"Erro ao Inserir" + ex.toString(), Toast.LENGTH_SHORT).show();//Mensagem erro ao Inserir
                }
            }
        });

    }
}