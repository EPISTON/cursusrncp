package com.courtalon.minigalerieform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.courtalon.minigalerieform.metier.Tag;

import java.util.Arrays;

public class TagActivity extends AppCompatActivity {
    private ListView tagList;
    private ArrayAdapter<String> tagAdapter;
    private RequestQueue fileAttente;
    private Tag[] tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        tagList = (ListView)findViewById(R.id.listViewTag);
        tagAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        GsonRequest<Tag[]> tagsrequest = new GsonRequest<>(
                "http://10.0.2.2:8080/gigaGallerie/tag/liste",
                Tag[].class,
                null,
                new Response.Listener<Tag[]>() {
                    @Override
                    public void onResponse(Tag[] response) {
                        tagAdapter.clear();
                        tags = response;
                        for (Tag t : response)
                            tagAdapter.add(t.getLibelle());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        tagList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("tagSelected", tags[position].getId());
                setResult(RESULT_OK, resultIntent);
                finish();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
