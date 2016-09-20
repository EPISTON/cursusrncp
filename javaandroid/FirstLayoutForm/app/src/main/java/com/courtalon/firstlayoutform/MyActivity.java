package com.courtalon.firstlayoutform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my);
        setContentView(R.layout.my_linear);
        Log.i("info", "mon application est crée");
    }

    public void firstMethod(View v) {
        Toast.makeText(this, "first method called", Toast.LENGTH_SHORT).show();
        Log.i("info", "la méthode first est appelée");
    }

    public void secondMethod(View v) {
        Toast.makeText(this, "second method called", Toast.LENGTH_LONG).show();
        Log.i("info", "la méthode second est appelée");
    }

}
