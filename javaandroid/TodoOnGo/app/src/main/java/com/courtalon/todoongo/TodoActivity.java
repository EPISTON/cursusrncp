package com.courtalon.todoongo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class TodoActivity extends AppCompatActivity {

    private ListView lv;
    private TacheAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        adapter= new TacheAdapter(TodoActivity.this, new ArrayList<Tache>());
        lv = (ListView)findViewById(R.id.todoView);
        lv.setAdapter(adapter);

    }
     public void ajouterTache(View v) {
         Tache t = new Tache(
                 ((EditText)findViewById(R.id.editTitre)).getText().toString(),
                 ((EditText)findViewById(R.id.editContexte)).getText().toString(),
                 Integer.parseInt(((EditText)findViewById(R.id.editPriorite)).getText().toString()),
                 new Date());
         adapter.add(t);
     }

    public void  viderTaches(View v) {
        adapter.clear();
    }

    public void terminerTache(View v) {
        Tache t = (Tache)v.getTag();
        adapter.remove(t);
    }


}
