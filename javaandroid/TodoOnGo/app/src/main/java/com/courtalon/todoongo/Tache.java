package com.courtalon.todoongo;


import android.graphics.Color;

import java.util.Date;

public class Tache {
    public static final int PRIORITE_1 = Color.LTGRAY;
    public static final int PRIORITE_2 = Color.CYAN;
    public static final int PRIORITE_3 = Color.GREEN;
    public static final int PRIORITE_4 = Color.MAGENTA;
    public static final int PRIORITE_5 = Color.YELLOW;

    private String titre;
    private String contexte;
    private int priorite;
    private Date dateCreation;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContexte() {
        return contexte;
    }

    public void setContexte(String contexte) {
        this.contexte = contexte;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getPrioriteColor() {
        switch(getPriorite()) {
            case 1: return PRIORITE_1;
            case 2: return PRIORITE_2;
            case 3: return PRIORITE_3;
            case 4: return PRIORITE_4;
            case 5: return PRIORITE_5;
            default: return PRIORITE_1;
        }
    }

    public Tache(String titre, String contexte, int priorite, Date dateCreation) {
        this.titre = titre;
        this.contexte = contexte;
        this.priorite = priorite;
        this.dateCreation = dateCreation;
    }
}
