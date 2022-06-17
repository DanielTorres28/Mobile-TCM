package com.example.mobiletcm.Serviços;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ServicosDAO extends SQLiteOpenHelper {
    private final String SERVICOS = "TBl_SERVICOS";//Constante

    public ServicosDAO(@Nullable Context context) {
        super(context, "BD_SERVICO", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        String Servicos = "CREATE TABLE " + SERVICOS + "(" +
                "ID INTEGER PRIMARY KEY," +
                "PROJETO VARCHAR(100) not null," +
                "DESCRICAO VARCHAR(100) not null)";


        //Esse metodo onCreate vai ser chamado automaticamente e vai criar a tabela cadastrarSoc
        banco.execSQL(Servicos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int i, int i1) {

    }

    //metodo de Inserir
    public long inserirServico(ServicosDTO sv){ //long pode inserir um monte linhas
        ContentValues values = new ContentValues(); //vai associar os nomes  das colunas com os valores da coluna
        values.put("Nome do Projeto", sv.getNomeProjeto()); //está associando os get com o banco de dados //metodo put=espera receber qualquer tipo de dados
        values.put("Descriçao", sv.getDescricao());

        long nlinhas = getWritableDatabase().insert(SERVICOS, null, values);
        return nlinhas; //long retornando long ou então
    }


    //consultando todos
    public ArrayList<ServicosDTO> consultarCadastro(){
        String servicos = "SELECT * FROM " + SERVICOS;
        Cursor cursor = getReadableDatabase().rawQuery(servicos, null);
        ArrayList<ServicosDTO> arrayListservico = new ArrayList<>();

        while(cursor.moveToNext()){ //cursor está recebendo o resultado da tabela //moveToNext está apontando para a primeira linha da tabela
            ServicosDTO dtoservicos = new ServicosDTO();
            dtoservicos.setId(cursor.getInt(0));//informando coluna que estou utilizando utilizando o tipo de dado usado
            dtoservicos.setNomeProjeto(cursor.getString(1));
            dtoservicos.setDescricao(cursor.getString(2));

            arrayListservico.add(dtoservicos);
        }
        return arrayListservico;
    }

    //consultando por Nome
    public ArrayList<ServicosDTO> consultarPorNome(String nome){
        String servicos = "SELECT * FROM " + SERVICOS + " WHERE Nome LIKE  + ?"; //QUALQUER TEXTO QUE CONTENHA A DIGITADA NO CODIGO ELE VAI PESQUISAR
        String[] args = {"%"+nome+"%"};
        Cursor cursor = getReadableDatabase().rawQuery(servicos, args);

        ArrayList<ServicosDTO> arrayListservico = new ArrayList<>();

        while(cursor.moveToNext()){ //cursor está recebendo o resultado da tabela //moveToNext está apontando para a primeira linha da tabela
            ServicosDTO dtoservicos = new ServicosDTO();
            dtoservicos.setId(cursor.getInt(0));//informando coluna que estou utilizando utilizando o tipo de dado usado
            dtoservicos.setNomeProjeto(cursor.getString(1));
            dtoservicos.setDescricao(cursor.getString(2));

            arrayListservico.add(dtoservicos);
        }
        return arrayListservico;
    }

    //alterando o usuario
    public long alterarServico(ServicosDTO sv) {
        ContentValues values = new ContentValues();
        values.put("Nome do Projeto", sv.getNomeProjeto()); //está associando os get com o banco de dados
        values.put("Descrição", sv.getDescricao());

        String id = "id=?";
        String[] args = {sv.getId()+""};
        return getWritableDatabase().update(SERVICOS, values, id, args);
    }

    //Excluindo da tabela
    public int excluirServico(ServicosDTO dtoservicos) {
        String id = "id=?";
        String[] args = {dtoservicos.getId()+""};
        return getWritableDatabase().delete(SERVICOS,id,args);
    }
}
