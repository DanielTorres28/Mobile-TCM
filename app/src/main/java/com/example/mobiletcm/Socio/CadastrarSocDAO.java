package com.example.mobiletcm.Socio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.example.mobiletcm.Cliente.CadastrarClienteDTO;

import java.util.ArrayList;

public class CadastrarSocDAO extends SQLiteOpenHelper{
    private final String TABELA = "TBl_CADASTRARSOC";//Constante


    public CadastrarSocDAO(@Nullable Context context) {
        super(context, "DB_Ayoike", null, 1);
    }



    //metodos essenciais para funcionar o metodo
    @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String comando = "CREATE TABLE " + TABELA + "(" +
                    "ID INTEGER PRIMARY KEY," +
                    "NOME VARCHAR(100) not null," +
                    "EMAIL VARCHAR(100) not null," +
                    "SENHA VARCHAR(10)not null)";

        //Esse metodo onCreate vai ser chamado automaticamente e vai criar a tabela cadastrarSoc
        sqLiteDatabase.execSQL(comando);



    }


    //ele vai ser chamado quando fizer uma alteração no banco de dados seria basicamente um alter table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //metodo de Inserir
    public long inserir(CadastrarDTO cadastrar){ //long pode inserir um monte linhas
        ContentValues values = new ContentValues(); //vai associar os nomes  das colunas com os valores da coluna
        values.put("Nome", cadastrar.getNome()); //está associando os get com o banco de dados //metodo put=espera receber qualquer tipo de dados
        values.put("Email", cadastrar.getEmail());
        values.put("senha", cadastrar.getSenha());


      long nlinhas = getWritableDatabase().insert(TABELA, null, values);
      return nlinhas; //long retornando long ou então
        // return getWritableDatabase().insert(TABELA, null, values);
    }

    //consultando todos
    public ArrayList<CadastrarDTO> consultarCadastro(){
        String comando = "SELECT * FROM " + TABELA;
        Cursor cursor = getReadableDatabase().rawQuery(comando, null);
        ArrayList<CadastrarDTO> arrayListCadastrar = new ArrayList<>();

        while(cursor.moveToNext()){ //cursor está recebendo o resultado da tabela //moveToNext está apontando para a primeira linha da tabela
            CadastrarDTO cadastrarDTO = new CadastrarDTO();
            cadastrarDTO.setId(cursor.getInt(0));//informando coluna que estou utilizando utilizando o tipo de dado usado
            cadastrarDTO.setNome(cursor.getString(1));
            cadastrarDTO.setEmail(cursor.getString(2));
            cadastrarDTO.setSenha(cursor.getString(3));

            arrayListCadastrar.add(cadastrarDTO);
        }
        return arrayListCadastrar;
    }

    //consultando por Nome
    public ArrayList<CadastrarDTO> consultarPorNome(String nome){
        String comando = "SELECT * FROM " + TABELA + " WHERE Nome LIKE  + ?"; //QUALQUER TEXTO QUE CONTENHA A DIGITADA NO CODIGO ELE VAI PESQUISAR
        String[] args = {"%"+nome+"%"};
        Cursor cursor = getReadableDatabase().rawQuery(comando, args);

        ArrayList<CadastrarDTO> arrayListCadastrar = new ArrayList<>();

        while(cursor.moveToNext()){ //cursor está recebendo o resultado da tabela //moveToNext está apontando para a primeira linha da tabela
            CadastrarDTO cadastrarDTO = new CadastrarDTO();
            cadastrarDTO.setId(cursor.getInt(0));//informando coluna que estou utilizando utilizando o tipo de dado usado
            cadastrarDTO.setNome(cursor.getString(1));
            cadastrarDTO.setEmail(cursor.getString(2));
            cadastrarDTO.setSenha(cursor.getString(3));

            arrayListCadastrar.add(cadastrarDTO);
        }
        return arrayListCadastrar;
    }

    //alterando o usuario
    public long alterar(CadastrarDTO cadastrar) {
        ContentValues values = new ContentValues();
        values.put("Nome", cadastrar.getNome()); //está associando os get com o banco de dados
        values.put("Email", cadastrar.getEmail());
        values.put("senha", cadastrar.getSenha());
        String id = "id=?";
        String[] args = {cadastrar.getId()+""};
        return getWritableDatabase().update(TABELA, values, id, args);
    }

    //Excluindo da tabela
    public int excluir(CadastrarDTO cadastrarDTO) {
        String id = "id=?";
        String[] args = {cadastrarDTO.getId()+""};
       return getWritableDatabase().delete(TABELA,id,args);
    }
}

//WritableDataBase = para qualquer operação que vai modificar, inserir ou alterar o banco de dados
//ReadableDataBase = para consultas que são as querys
