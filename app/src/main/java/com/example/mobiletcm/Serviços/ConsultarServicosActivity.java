package com.example.mobiletcm.Serviços;

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

import com.example.mobiletcm.Cliente.CadastrarClienteDAO;
import com.example.mobiletcm.Cliente.ConsultarCliActivity;
import com.example.mobiletcm.R;
import com.example.mobiletcm.Socio.DetalhesActivity;

import java.util.ArrayList;

public class ConsultarServicosActivity extends AppCompatActivity {
    Button CadastrarNovo;
    EditText pesquisar;
    ListView ListViewServicos;
    ArrayList<ServicosDTO> arrayListservico = new ArrayList<>();
    ServicosDAO daoservicos = new ServicosDAO(ConsultarServicosActivity.this);
    ServicosDTO dtoservicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_servicos);

        CadastrarNovo = findViewById(R.id.CadastrarNovo);
        pesquisar = findViewById(R.id.pesquisar);
        ListViewServicos = findViewById(R.id.ListViewServicos);


        //ServicosDAO daoservicos = new ServicosDAO(ConsultarServicosActivity.this);

        /*Parte da consulta da ListView*/
        ServicosDAO daoservicos = new ServicosDAO (ConsultarServicosActivity.this);

        arrayListservico = daoservicos.consultarCadastro();

        ArrayAdapter adaptar = new ArrayAdapter(ConsultarServicosActivity.this, android.R.layout.simple_list_item_1,  arrayListservico);

        ListViewServicos.setAdapter(adaptar);
        /*Fim da consulta da ListView */


        //Evento do click na listView Tabela e dizendo em que momento ele vai aparecer
        ListViewServicos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                dtoservicos = arrayListservico.get(posicao);//está na posição que foi clicado
                registerForContextMenu(ListViewServicos);
                return false;
            }
        });

        //fazendo a função de pesquisar o cadastrado
        pesquisar.addTextChangedListener(new TextWatcher() {
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
                atualizarListView(daoservicos.consultarPorNome(editable.toString()));
            }
        });

        CadastrarNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inclusao = new Intent(ConsultarServicosActivity.this, CadastrarServicosActivity.class);
                startActivity(inclusao); //iniciando a tela
            }
        });

        //vai ser acessado com as infomarções do banco de dados
        atualizarListView(daoservicos.consultarCadastro());
    }

    private void atualizarListView(ArrayList<ServicosDTO>dtoservicos) {
        arrayListservico = dtoservicos;
        ArrayAdapter adapter = new ArrayAdapter(ConsultarServicosActivity.this, android.R.layout.simple_list_item_1, arrayListservico);
        ListViewServicos.setAdapter(adapter);
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
            Intent intent = new Intent(ConsultarServicosActivity.this, DetalhesActivity.class);
            intent.putExtra("id", dtoservicos.getId());
            intent.putExtra("Nome do Projeto",dtoservicos.getNomeProjeto());
            intent.putExtra("Descrição",dtoservicos.getDescricao());

            startActivity(intent);
        }
        else{
            AlertDialog.Builder msg = new AlertDialog.Builder(ConsultarServicosActivity.this);
            msg.setMessage("Confirmar a exclusão");
            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int deletados = daoservicos.excluirServico(dtoservicos);
                    if (deletados > 0) {
                        Toast.makeText(ConsultarServicosActivity.this, "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                        arrayListservico = daoservicos.consultarCadastro();
                        atualizarListView(arrayListservico);
                    } else {
                        Toast.makeText(ConsultarServicosActivity.this, "Erro ao Excluír", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            msg.setNegativeButton("Não", null);
            msg.show();

        }
        return super.onContextItemSelected(item);//retornando o item selecionado
    }
}
