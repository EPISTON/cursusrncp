package com.courtalon.secondlayoutform;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// mon ArrayAdapter est aussi une collection (comme un ArrayList)
// j'herite de ArrayAdapter<Tweet>, j'herite de tous ce qu'il faut
// pour gerer une collection de tweet
// par contre, il ne sait pas comment "personnaliser" l'affichage
// de mes ligne dans la liste, c'est ce que nous allons fournir
public class MyTweetAdapter extends ArrayAdapter<Tweet>
{
    public MyTweetAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    // la methode getView est appelée par le composant graphique ListView
    // elle est appelé quand une nouvelle ligne doit apparaitre a l'affichage
    // cependant, pour des raisons de performance, la listeView veux, si possible
    // réutiliser des ligne(composant graphique) existante si elle ne s'en sert plus
    // pour eviter de réallouer ces composants graphiques
    // quand la ListView a besoin d'afficher une nouvelle ligne, 2 cas ce presentent
    //          1 -> il n'a pas de vue "recyclée" a disposition, a ce moment la
    // la parametre convertView sera null, c.a.d, creer une nouvelle vue pour cette ligne
    //          2 -> il a une vue de ligne "prete à etre recyclée", il me la passe
    // dans le parametre convertView, pour que je la reutilise avec les donnée de la nouvelle
    // ligne aparaissant a l'affichage
    // autre parametres:
    // position -> l'index du tweet a afficher dans la collection
    // parent -> ici, c'est la ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // je recupere le tweet correspondant
        Tweet t = getItem(position);

        Log.i("info", "besoin d'une ligne pour " + t.getTexte());
        // creer la vue d'une ligne si elle n'existe pas a partir du layout
        // c'est au demarrage, au premier affichage, que ca cas se présentera
        // cf, cas no 1
        if (convertView == null) {
            // cela instancie tous les composant graphique de la vue
            // c'est couteux en execution, on ne veut pas le faire souvent
            // c'est pour ca que si on a des composants deja disponnible (vue recyclée)
            // on ne passera pas ici
            Log.i("info", "*** creation de la vue pour cette ligne ***");
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.tweet_layout, parent, false);
        }
        else {
            Log.i("info", "recuperation vue existante pour cette ligne");
        }


        // la propriété tag d'une vue est une sorte de fourre-tout, ou vous pouvez
        // stocker un objet associé de votre choix
        // si le convertView view d'etre creer, je n'ai rien dans Tag (pas d'objet associé)
        // je vais alors creer un TwwetViewHolder, qui contiendra les composants champs
        // associés a cette vue/ligne
        // sinon, se tweetViewHolde existe déjà
        // POURQUOI?
        // ce n'est pas nécéssaire /indispensable, c'est une optimisation
        // j'ai une vue avec les composants d'une ligne
        // pour récupérer chaque composant, je doit appeler findViewById
        // c'est relativement couteux aussi
        // le TweetViewHolder, stockera ces composant, pour eviter de les
        // rechercher quand on recyclera
        TweetViewHolder viewHolder = (TweetViewHolder)convertView.getTag();
        // si je ne peu pas la recuperer
        if (viewHolder == null) {
            Log.i("info", "creation du ViewHolder associé a cette vue de ligne");
            // on creer notre ViewHolder
            viewHolder = new TweetViewHolder();
            // on recupere les champs via le couteux findViewById
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.texte = (TextView) convertView.findViewById(R.id.texte);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            // et on l'associe a notre vue, pour pouvoir directement les retrouver
            // plus tard (sans find)
            convertView.setTag(viewHolder);
        }
        // dans mon viewHolder, j'ai les 3 composants graphiques a mettre a jour
        // pour la logne en question

        Log.i("info", "copie du contenu du tweet" + t.getTexte() + " dans la vue");
        // je copie dans chaque composant graphique/champ la valeur correspondante du
        // tweet
        viewHolder.pseudo.setText(t.getPseudo());
        viewHolder.texte.setText(t.getTexte());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(t.getColor()));
        return convertView;
    }

    // cette classe me permet de stocker ensemble les 3 composant visuel d'un tweet
    private class TweetViewHolder {
        public TextView pseudo;
        public TextView texte;
        public ImageView avatar;
    }
}
