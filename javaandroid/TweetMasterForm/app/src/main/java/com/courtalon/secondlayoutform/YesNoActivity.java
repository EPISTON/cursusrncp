package com.courtalon.secondlayoutform;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

public class YesNoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_no);
    }



    // WARNING, ne pas faire le finish dans onStop, c'est alors trop tard
    // et le résultat n'est pas renvoyé
    @Override
    public void onPause() {
        super.onPause();
        Log.i("cyclevie", "passage onStop de yesNo");
        Intent returnIntent = new Intent();
        returnIntent.putExtra("choix",
                ((Switch)findViewById(R.id.switchChoix)).isChecked());
        setResult(Activity.RESULT_OK, returnIntent);
        // important, sinon, le resulat n'est pas renvoyé
        finish();
    }
}
