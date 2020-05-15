package com.example.appunipac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appunipac.MODEL.DOMAIN.Aluno;
import com.example.appunipac.MODEL.REPOSITORY.AlunoRepository;
import com.example.appunipac.UI.ADAPTER.AlunoAdapter;
import com.example.appunipac.UI.ADAPTER.AlunoForm;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button formBtn;
    ListView listaDeAlunos;
    private AlunoAdapter alunoAdaper;
    private AlunoRepository alunoRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaDeAlunos = (ListView) findViewById(R.id.lista_aluno);
        formBtn = (Button) findViewById(R.id.formBtn);

        alunoRepository = new AlunoRepository(this);

        formBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AlunoForm.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Aluno> alunoList = alunoRepository.getAll();
        Log.i("ALUNOS_LIST", alunoList.toString());

        if (!alunoList.isEmpty()) {
            alunoAdaper = new AlunoAdapter(alunoList, this);
            listaDeAlunos.setAdapter(alunoAdaper);
        } else {
            Toast.makeText(this, "Não existe Alunos no Banco", Toast.LENGTH_LONG).show();
        }
    }
}
