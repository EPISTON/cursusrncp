package com.courtalon.minigalerieform;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.courtalon.minigalerieform.metier.Photo;

import java.util.List;


public class MyPhotoAdapter extends ArrayAdapter<Photo>
{   private RequestQueue fileAttente;
    public MyPhotoAdapter(Context context, List<Photo> photos, RequestQueue fileAttente) {
        super(context, 0, photos);
        this.fileAttente = fileAttente;
    }

    private void loadImageFor(Photo p, final ImageView v) {
        ImageRequest imgrequest = new ImageRequest(
                "http://10.0.2.2:8080/gigaGallerie/photo/affiche/" + p.getId(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        v.setImageBitmap(response);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        v.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
                    }
                }
        );
        fileAttente.add(imgrequest);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Photo p = getItem(position);

        if (convertView == null) {
//            Log.i("info", "*** creation de la vue pour cette ligne ***");
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.photoitem_layout, parent, false);
        }
        else {
  //          Log.i("info", "recuperation vue existante pour cette ligne");
        }


        PhotoViewHolder viewHolder = (PhotoViewHolder)convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new PhotoViewHolder();
            // on recupere les champs via le couteux findViewById
            viewHolder.nom = (TextView) convertView.findViewById(R.id.textViewNom);
            viewHolder.description = (TextView) convertView.findViewById(R.id.textViewDescription);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.imageView);
            // et on l'associe a notre vue, pour pouvoir directement les retrouver
            // plus tard (sans find)
            convertView.setTag(viewHolder);
        }

        viewHolder.nom.setText(p.getNom());
        viewHolder.description.setText(p.getDescription());
        viewHolder.photo.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        loadImageFor(p, viewHolder.photo);
        return convertView;
    }

    // cette classe me permet de stocker ensemble les 3 composant visuel d'un tweet
    private class PhotoViewHolder {
        public TextView nom;
        public TextView description;
        public ImageView photo;
    }
}
