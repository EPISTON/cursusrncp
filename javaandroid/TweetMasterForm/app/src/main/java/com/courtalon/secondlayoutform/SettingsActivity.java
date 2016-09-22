package com.courtalon.secondlayoutform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        prefs = getSharedPreferences("tweetAuthor", MODE_PRIVATE);
        SharedPreferences.Editor peditor =  prefs.edit();
        peditor.putString("name", "bob");
        peditor.putInt("color", Color.YELLOW);
        peditor.commit();

    }
}
