package com.courtalon.secondlayoutform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

public class WidgetActivity extends AppCompatActivity {


    private ListView myTweetListView;
    private MyTweetAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("cycleVie", "appel de onCreate su mon activite");
        setContentView(R.layout.activity_widget);

        // la toolbar correspond au menu avec settings (en haut)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myTweetListView = (ListView) findViewById(R.id.listView);
        adapter = new MyTweetAdapter(WidgetActivity.this, new ArrayList<Tweet>());
        myTweetListView.setAdapter(adapter);
    }

    private String[] choix = null;

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("cycleVie", "appel de OnStart de notre application");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_widget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                Log.i("info", "clicked settings");
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.putExtra("message_settings", "bonjour depuis main");
                startActivity(intent);

                //Toast.makeText(this, "clicked settings", Toast.LENGTH_LONG);
                return true;
            case R.id.action_open:
                Log.i("info", "clicked open");
                //Toast.makeText(this, "clicked open", Toast.LENGTH_LONG);
                return true;
            case R.id.action_close:
                Log.i("info", "clicked close");
                //Toast.makeText(this, "clicked close", Toast.LENGTH_LONG);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ajoutertweet(View v) {
        //if (v.getId() == R.id.buttonAdd)
        TextView champ = (TextView)findViewById(R.id.texteTweet);
        adapter.add(new Tweet(avatarColor, authorName, champ.getText().toString()));
    }
    public void vidertweet(View v) {
        adapter.clear();
    }

    private SharedPreferences prefs;
    private String authorName;
    private int avatarColor;

    @Override
    public void onResume() {
        super.onResume();
        //Intent intent = getIntent();
        String jsonTweets = "[]";
        try {
            // j'ecris un fichier dans l'espace privé de cette application
            FileInputStream f = getApplicationContext().openFileInput("tweets");
            Scanner reader = new Scanner(f);
            jsonTweets = reader.nextLine();
            reader.close();
        } catch (FileNotFoundException e) {
            Log.i("info", "pas de tweet sauvegardés");
        }
        if (jsonTweets != null && !jsonTweets.isEmpty()) {

            Gson gson = new Gson();
            //Type listType = new TypeToken<ArrayList<Tweet>>() {}.getType();

            // je deserialise le tableau de tweet
            Tweet[] tweetArray = gson.fromJson(jsonTweets, Tweet[].class);
            // je reconvertit mon tableau en ArrayList
            ArrayList<Tweet> tweets = new ArrayList<>(Arrays.asList(tweetArray));

            adapter.addAll(tweets);
        }
        prefs = getSharedPreferences("tweetAuthor", MODE_PRIVATE);
        authorName = prefs.getString("name", "moi");
        avatarColor = prefs.getInt("color", Color.RED);



        /*Bundle b = intent.getBundleExtra("tweets");
        if ( b != null) {
            ArrayList<CharSequence> pseudos = b.getCharSequenceArrayList("pseudos");
            ArrayList<CharSequence> textes = b.getCharSequenceArrayList("textes");
            ArrayList<Integer> couleurs = b.getIntegerArrayList("couleurs");
            for (int i = 0; i < pseudos.size(); i++) {
                Tweet t = new Tweet(couleurs.get(i),
                        pseudos.get(i).toString(),
                        textes.get(i).toString()
                );
                adapter.add(t);
            }
        }*/
        Log.i("cycleVie", "appel de onResume de notre application");



    }

    @Override
    public void onPause() {
        super.onPause();
        //Intent intent = getIntent();

        Gson gson = new Gson();
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < adapter.getCount(); i++) {
            tweets.add(adapter.getItem(i));
        }
        // transformation de la collection en chaine json
        String jsontweets = gson.toJson(tweets);
        Log.i("json", jsontweets);
        // sauvegarde de celle ci dans notre intent
        //intent.putExtra("tweets", jsontweets);
        try {
            // j'ecris un fichier dans l'espace privé de cette application
            FileOutputStream f = getApplicationContext().openFileOutput("tweets", MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(f);
            pw.print(jsontweets);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        Log.i("cycleVie", "appel de onPause de notre application");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("cycleVie", "appel de onStop de notre application");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("cycleVie", "appel de onDestroy de notre application");
    }
    @Override
    public void onRestart() {
        super.onRestart();
        Log.i("cycleVie", "appel de onRestart de notre application");
    }

}
