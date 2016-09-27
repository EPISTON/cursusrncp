package com.courtalon.minigalerieform.metier;

/**
 * Created by Stagiaire on 27/09/2016.
 */
public class Tag {
    private int id;
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tag() { this(0, "");}
    public Tag(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
