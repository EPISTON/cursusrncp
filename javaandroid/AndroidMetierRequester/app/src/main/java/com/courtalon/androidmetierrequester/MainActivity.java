package com.courtalon.androidmetierrequester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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
}
