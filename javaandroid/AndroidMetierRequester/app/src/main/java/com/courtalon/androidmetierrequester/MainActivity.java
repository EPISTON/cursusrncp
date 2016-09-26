package com.courtalon.androidmetierrequester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.courtalon.androidmetierrequester.metier.Produit;

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
        fileAttenteRequetes = Volley.newRequestQueue(this);
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        itemsView.setAdapter(itemsAdapter);
    }

    public void getJsonProduits(View v) {
        // petit piege, si l'emulateur est un genymotion, l'adresse est 10.0.3.2
        // cela correspond au localhost de la machine hote
        // en effet, le localhost sur l'emulateur est le terminal mobile lui-même!
        String url = "http://10.0.2.2:8080/springStrutsJpaExo4Form/produit/indexjson/";
        GsonRequest<Produit[]> request = new GsonRequest<>(
                url,
                Produit[].class,
                null,
                new Response.Listener<Produit[]>() {
                    @Override
                    public void onResponse(Produit[] response) {
                        itemsAdapter.clear();
                        for (Produit p : response) {
                            itemsAdapter.add(p.toString());
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        statusView.setText("erreur de la requete: " + error.getMessage());
                    }
                });
        fileAttenteRequetes.add(request);
    }
}
