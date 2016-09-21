package com.courtalon.secondlayoutform;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

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



        // c'est en quelque sorte un menu contextuel flottant
        // ca correspond a l'icone de la petite enveloppe
        // on recuper la floating barre
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // dans le click listener
        // on affiche une certain nombre de choix
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ma premiere Action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Snackbar.make(view, "Ma deuxieme Action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });*/
        myTweetListView = (ListView) findViewById(R.id.listView);
        adapter = new MyTweetAdapter(WidgetActivity.this, new ArrayList<Tweet>());
        myTweetListView.setAdapter(adapter);
    }

    private String[] choix = null;

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("cycleVie", "appel de OnStart de notre application");

        List<Tweet> tweets = new ArrayList<Tweet>();
        /*tweets.add(new Tweet(Color.BLACK, "steve jobs", "j'ai mange une pomme"));
        tweets.add(new Tweet(Color.RED, "Bill gates", "vive la fondation gates"));
        tweets.add(new Tweet(Color.BLUE, "Patrick Etoile", "peu de meduses aujourd'hui"));
        tweets.add(new Tweet(Color.YELLOW, "Bob Eponge", "vive le crusty crab"));
        tweets.add(new Tweet(Color.BLUE, "Patrick Etoile", "a la peche au meduse"));
        tweets.add(new Tweet(Color.BLACK, "steve jobs", "j'aime android"));
        tweets.add(new Tweet(Color.RED, "Bill gates", "encore un blue screen"));
        tweets.add(new Tweet(Color.YELLOW, "Bob Eponge", "shopping de nouveau pantalon"));
        tweets.add(new Tweet(Color.RED, "Bill gates", "nouvelle demo usb"));
        tweets.add(new Tweet(Color.RED, "Bill gates", "visual studio code sous linux :("));*/

        //adapter.notifyDataSetChanged();


        //ListView lv = (ListView)findViewById(R.id.listView);
       /* choix = new String[] {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche",
                "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche",
                "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                WidgetActivity.this,
                android.R.layout.simple_list_item_1, choix );
        lv.setAdapter(adapter);*/
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
        adapter.add(new Tweet(Color.MAGENTA, "moi", champ.getText().toString()));
    }
    public void vidertweet(View v) {
        adapter.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("tweets");
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
        }
        Log.i("cycleVie", "appel de onResume de notre application");



    }

    @Override
    public void onPause() {
        super.onPause();
        Intent intent = getIntent();

        Bundle b = new Bundle();
        ArrayList<CharSequence> pseudos = new ArrayList<>();
        ArrayList<CharSequence> textes = new ArrayList<>();
        ArrayList<Integer> couleurs = new ArrayList<>();
        for (int i = 0; i < adapter.getCount(); i++) {
            Tweet t = adapter.getItem(i);
            pseudos.add(t.getPseudo());
            textes.add(t.getTexte());
            couleurs.add(t.getColor());
        }
        b.putCharSequenceArrayList("pseudos", pseudos);
        b.putCharSequenceArrayList("textes", textes);
        b.putIntegerArrayList("couleurs", couleurs);
        intent.putExtra("tweets", b);


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
