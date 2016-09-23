package com.courtalon.todoongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class TodoActivity extends AppCompatActivity {
    public static final int AJOUT_TACHE_REQUEST = 1;

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

    @Override
    protected void onResume() {
        super.onResume();
        Gson json = new Gson();
        try {
            // ouverture du fichier de sauvegarde des taches en lecture
            FileInputStream fi = getApplicationContext().openFileInput("taches");
            Scanner reader = new Scanner(fi);
            // lecture du json depuis le fichier
            String jsontaches = reader.nextLine();
            reader.close();
            // de-serialisation des taches depuis le json
            Tache[] taches = json.fromJson(jsontaches, Tache[].class);
            // ajout des taches deserialisées dans l'adpater de la liste
            adapter.clear();
            if (tacheToAdd != null)
                adapter.add(tacheToAdd);
            tacheToAdd = null;
            adapter.addAll(Arrays.asList(taches));
        } catch (FileNotFoundException e) {
            Log.i("taches", "pas de taches sauvegardées a charger");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Gson json = new Gson();
        try {
            // ouverture du fichier de sauvegarde en ecriture
            FileOutputStream fo = getApplicationContext().openFileOutput("taches", MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fo);
            // recuperation des taches depuis l'adapter
            ArrayList<Tache> taches = new ArrayList<>();
            for ( int i = 0; i < adapter.getCount(); i++) {
                taches.add(adapter.getItem(i));
            }
            // serialisation en json des taches
            String jsontaches = json.toJson(taches);
            // ecriture dans le fichier du json des taches
            pw.print(jsontaches);
            pw.close();
        } catch (FileNotFoundException e) {
            Log.e("taches", e.getMessage());
        }
    }

    public void ajouterTache(View v) {
         Intent intent = new Intent(this, EditTacheActivity.class);
         startActivityForResult(intent, AJOUT_TACHE_REQUEST);

         /*Tache t = new Tache(
                 ((EditText)findViewById(R.id.editTitre)).getText().toString(),
                 ((EditText)findViewById(R.id.editContexte)).getText().toString(),
                 Integer.parseInt(((EditText)findViewById(R.id.editPriorite)).getText().toString()),
                 new Date());
         adapter.add(t);*/
     }
    private Tache tacheToAdd = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("taches", "appel de onActivityResult");
        if (requestCode == AJOUT_TACHE_REQUEST) {
            if (resultCode == RESULT_CANCELED) {
                Log.i("taches", "pas de tache ajoutée");
            }
            else if (resultCode == RESULT_OK) {
                Log.i("taches", "tache ajoutée");
                tacheToAdd = new Tache(data.getStringExtra("tache_titre"),
                                    data.getStringExtra("tache_contexte"),
                                    data.getIntExtra("tache_priorite", 1),
                                    new Date());
            }
        }
        else {
            Log.i("taches", "code request inconnu");
        }

    }

    public void  viderTaches(View v) {
        adapter.clear();
    }

    public void terminerTache(View v) {
        Tache t = (Tache)v.getTag();
        adapter.remove(t);
    }


}
