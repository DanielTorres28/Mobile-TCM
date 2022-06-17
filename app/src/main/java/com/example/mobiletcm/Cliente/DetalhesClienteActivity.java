package com.example.mobiletcm.Cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiletcm.R;

public class DetalhesClienteActivity extends AppCompatActivity {

    EditText nomeCliente2, sobrenomeCliente2 , emailCliente2, telefoneCliente2, cpfCliente2, senhaCliente2;
    Button AlterarCli;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_cliente);
        getSupportActionBar().setTitle("Detalhes do Cliente");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar


        nomeCliente2 = findViewById(R.id.nomeCliente2);
        sobrenomeCliente2 = findViewById(R.id.sobrenomeCliente2);
        emailCliente2 = findViewById(R.id.emailCliente2);
        telefoneCliente2 = findViewById(R.id.telefoneCliente2);
        cpfCliente2 = findViewById(R.id.cpfCliente2);
        senhaCliente2 = findViewById(R.id.senhaCliente2);
        AlterarCli = findViewById(R.id.AlterarCli);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        nomeCliente2.setText(bundle.getString("Nome"));
        sobrenomeCliente2.setText(bundle.getString("Sobrenome"));
        emailCliente2.setText(bundle.getString("Email"));
        telefoneCliente2.setText(bundle.getString("Telefone"));
        cpfCliente2.setText(bundle.getString("CPF"));
        senhaCliente2.setText(bundle.getString("Senha"));



        AlterarCli.setOnClickListener(new View.OnClickListener() {
            @Override //alterar o socio lá do banco de dados
            public void onClick(View view) {
                CadastrarClienteDTO clienteDTO = new CadastrarClienteDTO();
                clienteDTO.setId(id);
                clienteDTO.setNome(nomeCliente2.getText().toString());
                clienteDTO.setSobrenome(sobrenomeCliente2.getText().toString());
                clienteDTO.setEmail(emailCliente2.getText().toString());
                clienteDTO.setTelefone(telefoneCliente2.getText().toString());
                clienteDTO.setCpf(cpfCliente2.getText().toString());
                clienteDTO.setSenha(senhaCliente2.getText().toString());

                CadastrarClienteDAO daoCliente = new CadastrarClienteDAO(DetalhesClienteActivity.this);
                try{//vou tentar Inserir os dados para o banco de dados
                    long linhasInseridas = daoCliente.alterarCliente(clienteDTO);
                    if (linhasInseridas>0){
                        Toast.makeText(DetalhesClienteActivity.this, "Alterado com Sucesso", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(DetalhesClienteActivity.this, ConsultarCliActivity.class);
                        startActivity(main);
                    }
                    else{
                        Toast.makeText(DetalhesClienteActivity.this, "Não foi possível Alterar", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(DetalhesClienteActivity.this, "Erro ao Alterar" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}