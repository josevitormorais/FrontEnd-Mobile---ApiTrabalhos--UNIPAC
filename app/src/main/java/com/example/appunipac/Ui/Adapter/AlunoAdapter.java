package com.example.appunipac.Ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appunipac.MODEL.DOMAIN.Aluno;
import com.example.appunipac.R;

import java.util.List;

public class AlunoAdapter extends BaseAdapter {
    private List<Aluno> alunoList = null;
    private Context context = null;

    public AlunoAdapter(List<Aluno> alunoList, Context context) {
        this.alunoList = alunoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (!alunoList.isEmpty()) {
            return alunoList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (!alunoList.isEmpty()) {
            return alunoList.get(position);
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if (!alunoList.isEmpty()) {
            return alunoList.get(position).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunoList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.aluno_item, null);

        TextView alunoDesc = (TextView) view.findViewById(R.id.aluno_desc);

        StringBuffer sb = new StringBuffer(1200);
        sb.append(aluno.getNome());
        sb.append(" - ");
        sb.append(aluno.getTelefone());
        alunoDesc.setText(sb.toString());

        return view;
    }
}
