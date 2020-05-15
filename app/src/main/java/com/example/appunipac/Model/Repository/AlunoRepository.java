package com.example.appunipac.Model.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appunipac.INFRA.DbHelper;
import com.example.appunipac.Model.DOMAIN.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {
    private DbHelper dbHelper = null;

    public AlunoRepository(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean save(Aluno aluno) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long isSave = 0l;

        ContentValues values = new ContentValues();

        values.put(Aluno.FIELD_NAME, aluno.getName());
        values.put(Aluno.FIELD_REGISTER, aluno.getRegister());

        if (aluno.getId() != null) {
            values.put(Aluno.FIELD_ID, aluno.getId());

            isSave = database.update(Aluno.TABLE_ALUNO, values,
                    "id = ?",
                    new String[]{String.valueOf(aluno.getId())});
        } else {
            isSave = database.insert(Aluno.TABLE_ALUNO, null, values);
        }

        return isSave > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public List<Aluno> getAll(){
        List<Aluno> alunoList = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(Aluno.TABLE_ALUNO, Aluno.allColumms, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            int idxID = cursor.getColumnIndex(Aluno.FIELD_ID);
            int idxName = cursor.getColumnIndex(Aluno.FIELD_NAME);
            int idxRegister = cursor.getColumnIndex(Aluno.FIELD_REGISTER);

            do {

                Aluno aluno = new Aluno();
                aluno.setId(cursor.getLong(idxID));
                aluno.setName(cursor.getString(idxName));
                aluno.setRegister(cursor.getInt(idxRegister));

                alunoList.add(aluno);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return alunoList;
    }

    public Aluno findById(Long id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(Aluno.TABLE_ALUNO, Aluno.allColumms, Aluno.FIELD_ID + " = " + id, null, null, null, null);

        int idxID = cursor.getColumnIndex(Aluno.FIELD_ID);
        int idxName = cursor.getColumnIndex(Aluno.FIELD_NAME);
        int idxRegister = cursor.getColumnIndex(Aluno.FIELD_REGISTER);

        if (cursor.moveToFirst()) {

            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(idxID));
            aluno.setName(cursor.getString(idxName));
            aluno.setRegister(cursor.getInt(idxRegister));
        }

        return new Aluno();
    }
}
