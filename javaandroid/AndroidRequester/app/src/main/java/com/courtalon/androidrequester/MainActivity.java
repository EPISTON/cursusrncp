package com.courtalon.androidrequester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView statusView;
    private ListView itemsView;
    private RequestQueue fileAttenteRequetes;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusView = (TextView)findViewById(R.id.statusText);
        itemsView = (ListView)findViewById(R.id.listViewItems);
        // cette file d'attente est un des composants de Volley (la librairie HTTP)
        // c'est cette file d'attente qui gerera les requete que l'on envoie
        fileAttenteRequetes = Volley.newRequestQueue(this);

        // mise en place de la ListView
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        itemsView.setAdapter(itemsAdapter);

    }

    public void getServerJson(View v) {
        String url = "http://jsonplaceholder.typicode.com/users";
        statusView.setText("requetage en cours....");
        /*
        [
            {
            "id": 1,
            "name": "Leanne Graham",
            "username": "Bret",
            "email": "Sincere@april.biz",
            "address": {
              "street": "Kulas Light",
              "suite": "Apt. 556",
              "city": "Gwenborough",
              "zipcode": "92998-3874",
              "geo": {
                "lat": "-37.3159",
                "lng": "81.1496"
              }
            },
        ...
        ]
         */
        // creation de la requete
        JsonArrayRequest request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        statusView.setText("nombre d'objets = " + response.length());
                        itemsAdapter.clear();
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject item = response.getJSONObject(i);
                                String ligne = "name: " + item.getString("name")
                                                + ", email = " + item.getString("email");
                                itemsAdapter.add(ligne);
                            }
                        } catch (JSONException e) {e.printStackTrace();}
                    }
                },
                new Response.ErrorListener(){
            @Override
                    public void onErrorResponse(VolleyError error) {
                        statusView.setText("erreur au requetage: " +  error.getMessage());
                    }
                }
        );
        // placement de la requete dans la file d'attente pour execution
        fileAttenteRequetes.add(request);
    }


}
