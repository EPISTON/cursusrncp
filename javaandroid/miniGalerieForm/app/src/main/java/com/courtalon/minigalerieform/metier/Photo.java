package com.courtalon.minigalerieform.metier;

import java.util.Date;

/**
 * Created by Stagiaire on 27/09/2016.
 */
public class Photo {
    private int id;
    private String nom;
    private String description;
    private Date dateCreation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Photo() {this(0, "", "", null);}
    public Photo(int id, String nom, String description, Date dateCreation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
