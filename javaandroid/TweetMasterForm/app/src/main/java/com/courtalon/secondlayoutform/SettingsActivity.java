package com.courtalon.secondlayoutform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getIntent();
        // je recupere le message en provenance du Main mis dans l'intent
        String message = intent.getStringExtra("message_settings");
        TextView tv = (TextView)findViewById(R.id.message_settings);
        tv.setText(message);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        /*SharedPreferences.Editor peditor =  prefs.edit();
        peditor.putString("name", "bob");
        peditor.putInt("color", Color.YELLOW);
        peditor.commit();*/
        EditText editName = (EditText)findViewById(R.id.auteurname_edit);
        editName.setText(prefs.getString("name", "moi"));
        setChoixCouleur(prefs.getInt("color", Color.YELLOW));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor peditor =  prefs.edit();
        peditor.putString("name", ((EditText)findViewById(R.id.auteurname_edit))
                .getText().toString());
        peditor.putInt("color", getChoixCouleur());
        peditor.commit();
        Log.i("prefs", "passage dans onPause de settings");
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    // selectionner le bon radioButton
    private void setChoixCouleur(int couleur) {
        RadioGroup rg = (RadioGroup)findViewById(R.id.choixCouleur);
        switch(couleur) {
            case Color.YELLOW: rg.check(R.id.choixjaune);
            case Color.BLUE: rg.check(R.id.choixbleue);
            case Color.GREEN: rg.check(R.id.choixvert);
            default: rg.check(R.id.choixjaune);
        }
    }
    // recuperer ce qui a été choisi par le radioButton
    private int getChoixCouleur() {
        RadioGroup rg = (RadioGroup)findViewById(R.id.choixCouleur);
        switch(rg.getCheckedRadioButtonId()) {
            case R.id.choixjaune: return Color.YELLOW;
            case R.id.choixbleue: return Color.BLUE;
            case R.id.choixvert: return Color.GREEN;
            default: return Color.BLACK;
        }
    }
}
