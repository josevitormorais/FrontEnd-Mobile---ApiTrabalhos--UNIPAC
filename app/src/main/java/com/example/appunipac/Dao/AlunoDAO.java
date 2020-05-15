package com.example.appunipac.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appunipac.INFRA.DbHelper;
import com.example.appunipac.MODEL.DOMAIN.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private DbHelper dbHelper = null;

    private String[] allColumns = { Aluno.ID, Aluno.NOME, Aluno.EMAIL,
            Aluno.TELEFONE};

    public AlunoDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean save(Aluno aluno) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Aluno.NOME, aluno.getNome());
        values.put(Aluno.EMAIL, aluno.getEmail());
        values.put(Aluno.TELEFONE, aluno.getTelefone());

        long insertId = database.insert(Aluno.TABLE_NAME, null, values);

        return insertId > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public Aluno buscarPorId(long id){
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(Aluno.TABLE_NAME, allColumns, Aluno.ID + " = " +
                id, null,null, null, null);
        cursor.moveToFirst();

        Aluno aluno = new Aluno();

        aluno.setId(cursor.getLong(0));
        aluno.setNome(cursor.getString(1));
        aluno.setEmail(cursor.getString(2));
        aluno.setTelefone(cursor.getString(3));

        return aluno;
    }

    public List<Aluno> getAll(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(Aluno.TABLE_NAME, allColumns, null, null,null, null, null);

        List<Aluno> alunos = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int idxId = cursor.getColumnIndex(Aluno.ID);
            int idxNome = cursor.getColumnIndex(Aluno.NOME);
            int idxEmail = cursor.getColumnIndex(Aluno.EMAIL);
            int idxTelefone = cursor.getColumnIndex(Aluno.TELEFONE);

            do {
                Aluno aluno = new Aluno();
                alunos.add(aluno);

                if (idxId != 0)
                    aluno.setId(cursor.getLong(idxId));
                if (idxNome != 0)
                    aluno.setNome(cursor.getString(idxNome));
                if (idxEmail != 0)
                    aluno.setEmail(cursor.getString(idxEmail));
                if (idxTelefone != 0)
                    aluno.setTelefone(cursor.getString(idxTelefone));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return alunos;
    }
}
