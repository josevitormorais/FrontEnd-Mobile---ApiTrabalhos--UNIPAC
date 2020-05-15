package com.example.appunipac.UI.ADAPTER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appunipac.DAO.AlunoDAO;
import com.example.appunipac.MODEL.DOMAIN.Aluno;
import com.example.appunipac.MODEL.REPOSITORY.AlunoRepository;
import com.example.appunipac.MainActivity;
import com.example.appunipac.R;

public class AlunoForm extends AppCompatActivity {

    private ViewHolder viewHolder = new ViewHolder();

    private AlunoRepository alunoRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_form);

        this.viewHolder.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.viewHolder.registerEdt = (EditText) findViewById(R.id.registerEdt);
        this.viewHolder.saveBtn = (Button) findViewById(R.id.saveBtn);

        alunoRepository = new AlunoRepository(this);


        this.viewHolder.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = AlunoForm.this.viewHolder.nameEdt.getText().toString();
                int register = Integer.parseInt(AlunoForm.this.viewHolder.registerEdt.getText().toString());
                Aluno aluno = getAluno(name, register);

                boolean save = alunoRepository.save(aluno);
                if (save) {
                    Toast.makeText(getApplicationContext(), "Dados Cadastrados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Dados Não Cadastrados", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Aluno getAluno(String name, int register) {
        Aluno aluno = new Aluno();
        aluno.setName(name);
        aluno.setRegister(register);
        return aluno;
    }

    public static class ViewHolder {
        EditText nameEdt, registerEdt;
        Button saveBtn;
    }

}
