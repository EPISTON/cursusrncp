package com.courtalon.minigalerieform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.courtalon.minigalerieform.metier.Photo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    private ListView itemsView;
    private RequestQueue fileAttenteRequetes;
    private MyPhotoAdapter itemsAdapter;
    private ArrayList<Integer> tagsID;
    private Integer tagToAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        tagsID = new ArrayList<>();

        itemsView = (ListView)findViewById(R.id.photoView);
        fileAttenteRequetes = Volley.newRequestQueue(getApplicationContext());
        itemsAdapter = new MyPhotoAdapter(this, new ArrayList<Photo>(), fileAttenteRequetes);
        itemsView.setAdapter(itemsAdapter);

    }

    public void addTag(View v) {
        startActivityForResult(new Intent(this, TagActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && resultCode == 1){
            tagToAdd =  data.getIntExtra("tagSelected", 0);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        // sauvegarde des tags selectionnés
        getIntent().putIntegerArrayListExtra("tags", tagsID);

    }

    private void refreshList() {
        String url = "";
        if (tagsID.isEmpty()) {
            url = "http://10.0.2.2:8080/gigaGallerie/photo/liste";
        }
        else {
            url = "http://10.0.2.2:8080/gigaGallerie/photo/filter/";
            int position = 0;
            for (Integer tid : tagsID) {
                if (position > 0)
                    url += "_";
                url += ""+ tid;
                position++;
            }
        }
        GsonRequest<Photo[]> request = new GsonRequest<>(
                url,
                Photo[].class,
                null,
                new Response.Listener<Photo[]>() {
                    @Override
                    public void onResponse(Photo[] response) {
                        itemsAdapter.clear();
                        for (Photo p : response) {
                            itemsAdapter.add(p);
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("erreur requette", error.getMessage());
                    }
                });
        fileAttenteRequetes.add(request);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // recupere ma liste de tag filtrants
        tagsID = getIntent().getIntegerArrayListExtra("tags");
        if (tagsID == null) {
            tagsID = new ArrayList<>();
        }
        if (tagToAdd != null) {
            tagsID.add(tagToAdd);
            tagToAdd = null;
        }
        refreshList();
     }
}
