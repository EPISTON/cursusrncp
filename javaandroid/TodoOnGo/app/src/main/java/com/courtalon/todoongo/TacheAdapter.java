package com.courtalon.todoongo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class TacheAdapter extends ArrayAdapter<Tache>{
    private static final SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");

    public TacheAdapter(Context context, List<Tache> taches) {
        super(context,0, taches);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tache t = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.tache_layout,
                            parent,
                            false);
        }

        TacheViewHolder tv = (TacheViewHolder)convertView.getTag();
        if (tv == null) {
            tv = new TacheViewHolder();
            tv.titreTexte = (TextView)convertView.findViewById(R.id.textTitre);
            tv.contexteTexte = (TextView)convertView.findViewById(R.id.textContexte);
            tv.dateTexte = (TextView)convertView.findViewById(R.id.textDate);
            tv.boutonTerminer = (Button) convertView.findViewById(R.id.buttonTerminer);
            convertView.setTag(tv);
        }

        tv.titreTexte.setText(t.getTitre());
        tv.contexteTexte.setText(t.getContexte());
        tv.dateTexte.setText(df.format(t.getDateCreation()));
        tv.boutonTerminer.setTag(t);
        convertView.setBackgroundColor(t.getPrioriteColor());
        return  convertView;
    }

    private class TacheViewHolder
    {
        public TextView titreTexte;
        public TextView contexteTexte;
        public TextView dateTexte;
        public Button boutonTerminer;
    }
}
