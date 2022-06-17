package com.example.mobiletcm.Cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mobiletcm.Socio.CadastrarDTO;

import java.util.ArrayList;

public class CadastrarClienteDAO extends SQLiteOpenHelper {
    private final String CLIENTE = "TBl_CLIENTE";//Constante

    public CadastrarClienteDAO(@Nullable Context context) { super(context, "DB_Ayoike1", null, 1); }



    //metodos essenciais para funcionar o metodo
    @Override
    public void onCreate(SQLiteDatabase db) {

        //criando a tabela do banco
        String Cliente = "CREATE TABLE " + CLIENTE + "(" +
                "ID INTEGER PRIMARY KEY," +
                "NOME VARCHAR(50) not null," +
                "SOBRENOME VARCHAR(50) not null," +
                "EMAIL VARCHAR(100) not null," +
                "TELEFONE VARCHAR(12) not null," +
                "CPF VARCHAR(15) not null," +
                "SENHA VARCHAR(10)not null)";

        //Esse metodo onCreate vai ser chamado automaticamente e vai criar a tabela Cliente
        db.execSQL(Cliente);

    }


    //vai ser chamado quando for fazer uma alteração na tabela no banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }



    //long= para saber se o código incluir um novo cliente
    public long inserirCliente(CadastrarClienteDTO contato){
        //comando de inserir os valores
        ContentValues valor = new ContentValues();//vai associar os nomes  das colunas com os valores da coluna
        valor.put("Nome", contato.getNome());//está associando os get com o banco de dados //metodo put=espera receber qualquer tipo de dados
        valor.put("Sobrenome", contato.getSobrenome());
        valor.put("Email", contato.getEmail());
        valor.put("Telefone", contato.getTelefone());
        valor.put("CPF", contato.getCpf());
        valor.put("Senha", contato.getSenha());

        long nlinhas = getWritableDatabase().insert(CLIENTE, null, valor);
        return nlinhas;

    }



    //consultando todos os clientes
    public ArrayList<CadastrarClienteDTO> consultarTodos() {
        String Cliente = "SELECT * FROM " + CLIENTE;
        Cursor cursor = getReadableDatabase().rawQuery(Cliente, null); //ReadableDatabase serve para leitura //WritableDatabase serve para escrever
        ArrayList<CadastrarClienteDTO> arrayListContato = new ArrayList<>();

        while (cursor.moveToNext()) { //cursor está recebendo o resultado da tabela //moveToNext está apontando para a primeira linha da tabela
            CadastrarClienteDTO clienteDTO = new CadastrarClienteDTO();
            clienteDTO.setId(cursor.getInt(0));//informando coluna que estou utilizando o tipo de dado usado
            clienteDTO.setNome(cursor.getString(1));
            clienteDTO.setSobrenome(cursor.getString(2));
            clienteDTO.setEmail(cursor.getString(3));
            clienteDTO.setTelefone(cursor.getString(4));
            clienteDTO.setCpf(cursor.getString(5));
            clienteDTO.setSenha(cursor.getString(6));

            arrayListContato.add(clienteDTO);
        }
        return arrayListContato;
    }

    //consultando por Nome
    public ArrayList<CadastrarClienteDTO> consultarNome(String nome){
        String Cliente = "SELECT * FROM " + CLIENTE + " WHERE Nome LIKE  + ?"; //QUALQUER TEXTO QUE CONTENHA A DIGITADA NO CODIGO ELE VAI PESQUISAR
        String[] args = {"%"+nome+"%"};
        Cursor cursor = getReadableDatabase().rawQuery(Cliente, args);

        ArrayList<CadastrarClienteDTO> arrayListContato = new ArrayList<>();

        while(cursor.moveToNext()){ //cursor está recebendo o resultado da tabela //moveToNext está apontando para a primeira linha da tabela
            CadastrarClienteDTO clienteDTO = new CadastrarClienteDTO();
            clienteDTO.setId(cursor.getInt(0));//informando coluna que estou utilizando utilizando o tipo de dado usado
            clienteDTO.setNome(cursor.getString(1));
            clienteDTO.setSobrenome(cursor.getString(2));
            clienteDTO.setEmail(cursor.getString(3));
            clienteDTO.setTelefone(cursor.getString(4));
            clienteDTO.setCpf(cursor.getString(5));
            clienteDTO.setSenha(cursor.getString(6));

            arrayListContato.add(clienteDTO);
        }
        return arrayListContato;
    }

    //alterando o usuario
    public long alterarCliente(CadastrarClienteDTO clienteDTO) {
        ContentValues values = new ContentValues();
        values.put("Nome", clienteDTO.getNome()); //está associando os get com o banco de dados
        values.put("Sobrenome", clienteDTO.getSobrenome());
        values.put("Email", clienteDTO.getEmail());
        values.put("Telefone", clienteDTO.getTelefone());
        values.put("CPF", clienteDTO.getCpf());
        values.put("senha", clienteDTO.getSenha());
        String id = "id=?";
        String[] args = {clienteDTO.getId()+""};
        return getWritableDatabase().update(CLIENTE, values, id, args);
    }

    //Excluindo da tabela
    public int excluirCliente(CadastrarClienteDTO clienteDTO) {
        String id = "id=?";
        String[] args = {clienteDTO.getId()+""};
        return getWritableDatabase().delete(CLIENTE,id,args);
    }
}
