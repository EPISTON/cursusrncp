package com.courtalon.secondlayoutform;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyTweetAdapter extends ArrayAdapter<Tweet>
{
    public MyTweetAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // creer le composant graphique s'il n'existe pas a partir du layout
        // c'est au demarrage, les view pour chaque ligne ne sont pas creer
        if (convertView == null) {
            // cela instancie tous les composant graphique de la vue
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.tweet_layout, parent, false);
        }

        // je recupere l'objet contenant les champs de mon tweet (sous la forme de composant
        // graphique) potentitiellement precedement creer (recyclage)

        // si le convertView view d'etre creer, je n'ai rien dans Tag (pas d'objet associé)
        // il faut alors que je creer l'objet "metier" associé a cette ligne et que je
        // l'associe via convertView.setTag

        TweetViewHolder viewHolder = (TweetViewHolder)convertView.getTag();
        // si je ne peu pas la recuperer
        if (viewHolder == null) {
            // on creer notre ViewHolder
            viewHolder = new TweetViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.texte = (TextView) convertView.findViewById(R.id.texte);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }
        return convertView;
    }

    // cette classe me permet de stocker ensemble les 3 composant visuel d'un tweet
    private class TweetViewHolder {
        public TextView pseudo;
        public TextView texte;
        public ImageView avatar;
    }
}
