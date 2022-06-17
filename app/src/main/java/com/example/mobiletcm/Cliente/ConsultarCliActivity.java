package com.example.mobiletcm.Cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobiletcm.R;
import com.example.mobiletcm.Socio.CadastrarDTO;
import com.example.mobiletcm.Socio.CadastrarSocDAO;
import com.example.mobiletcm.Socio.CadastrarSocioActivity;
import com.example.mobiletcm.Socio.ConsultasActivity;
import com.example.mobiletcm.Socio.DetalhesActivity;

import java.util.ArrayList;

public class ConsultarCliActivity extends AppCompatActivity {
    ListView listViewcontato;
    Button cadastrarNV;
    EditText PesquisarCli;
    ArrayList<CadastrarClienteDTO> arrayListContato; //= new ArrayList<>();
    CadastrarClienteDAO daoCliente = new CadastrarClienteDAO(ConsultarCliActivity.this);
    CadastrarClienteDTO clienteDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cli);
        getSupportActionBar().setTitle("Consultar Cliente");//mudando o nome da actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //botão voltar

        listViewcontato = findViewById(R.id.listViewcontato);
        cadastrarNV = findViewById(R.id.cadastrarNV);
        PesquisarCli = findViewById(R.id.PesquisarCli);

        cadastrarNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inclusao = new Intent(ConsultarCliActivity.this , ClienteActivity.class);
                startActivity(inclusao);
            }
        });

        /*Parte da consulta da ListView*/
        CadastrarClienteDAO daoCliente = new CadastrarClienteDAO(ConsultarCliActivity.this);

        arrayListContato = daoCliente.consultarTodos();

        ArrayAdapter adaptar = new ArrayAdapter(ConsultarCliActivity.this, android.R.layout.simple_list_item_1, arrayListContato);

        listViewcontato.setAdapter(adaptar);
        /*Fim da consulta da ListView */


        //Evento do click na listView Tabela e dizendo em que momento ele vai aparecer
        listViewcontato.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                clienteDTO = arrayListContato.get(posicao);//está na posição que foi clicado
                registerForContextMenu(listViewcontato);
                return false;
            }
        });

        //fazendo a função de pesquisar o cadastrado
        PesquisarCli.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Toast.makeText(ConsultasActivity.this, "before: "+ charSequence, Toast.LENGTH_SHORT).show();
//                ele pega o que tinha no texto antes de ser alterado

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) { //resposavel pela pesquisa de um usuario
                atualizarListView(daoCliente.consultarNome(editable.toString()));
            }
        });

        //vai ser acessado com as infomarções do banco de dados
        atualizarListView(daoCliente.consultarTodos());
    }

    private void atualizarListView(ArrayList<CadastrarClienteDTO>clienteDTO) {
        arrayListContato = clienteDTO;
        ArrayAdapter adapter = new ArrayAdapter(ConsultarCliActivity.this, android.R.layout.simple_list_item_1, arrayListContato);
        listViewcontato.setAdapter(adapter);
    }

    //Menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo); //construtor da classe mãe

        menu.add(0,0,0, "Detalhes/alterar");//Os números representa o primeiro é hierarquia, segundo a ordem exibida, e terceiro o ID do selecionado
        menu.add(0,1,1, "Excluir");
    }


    //passando o ID do menu e chamando sua função
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        if (item.getItemId()==0) {
            Intent intent = new Intent(ConsultarCliActivity.this, DetalhesClienteActivity.class);
            intent.putExtra("id", clienteDTO.getId());
            intent.putExtra("Nome",clienteDTO.getNome());
            intent.putExtra("Sobrenome",clienteDTO.getSobrenome());
            intent.putExtra("Email",clienteDTO.getEmail());
            intent.putExtra("Telefone",clienteDTO.getTelefone());
            intent.putExtra("CPF",clienteDTO.getCpf());
            intent.putExtra("Senha",clienteDTO.getSenha());
            startActivity(intent);
        }
        else{
            AlertDialog.Builder msg = new AlertDialog.Builder(ConsultarCliActivity.this);
            msg.setMessage("Confirmar a exclusão");
            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int deletados = daoCliente.excluirCliente(clienteDTO);
                    if (deletados > 0) {
                        Toast.makeText(ConsultarCliActivity.this, "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                        arrayListContato = daoCliente.consultarTodos();
                        atualizarListView(arrayListContato);
                    } else {
                        Toast.makeText(ConsultarCliActivity.this, "Erro ao Excluír", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            msg.setNegativeButton("Não", null);
            msg.show();

        }
        return super.onContextItemSelected(item);//retornando o item selecionado
    }
}