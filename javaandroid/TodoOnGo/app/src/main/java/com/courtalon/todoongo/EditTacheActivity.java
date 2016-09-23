package com.courtalon.todoongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditTacheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tache);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("test", "ma tache");
        setResult(RESULT_CANCELED, resultIntent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
     }

    public void saveTache(View v) {
        Intent resultIntent = new Intent();
        String titre = ((EditText)findViewById(R.id.editTitre)).getText().toString();
        String contexte = ((EditText)findViewById(R.id.editContexte)).getText().toString();
        int priorite = Integer.parseInt(((EditText)findViewById(R.id.editPriorite)).getText().toString());

        resultIntent.putExtra("tache_titre", titre);
        resultIntent.putExtra("tache_contexte", contexte);
        resultIntent.putExtra("tache_priorite", priorite);

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
