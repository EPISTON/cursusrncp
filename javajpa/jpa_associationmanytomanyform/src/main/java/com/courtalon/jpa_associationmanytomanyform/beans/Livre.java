package com.courtalon.jpa_associationmanytomanyform.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livre {
	private int id;
	private String titre;
	private Date parution;
	private int nbPages;
	private double prix;
	
	private Set<Auteur> auteurs;

	@Id @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	// je veux uniquement la date, sans l'heure
	@Temporal(TemporalType.DATE)
	public Date getParution() {
		return parution;
	}

	public void setParution(Date parution) {
		this.parution = parution;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	
	@ManyToMany
	public Set<Auteur> getAuteurs() {
		if (auteurs == null)
			auteurs = new HashSet<>();
		return auteurs;
	}

	public void setAuteurs(Set<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	public Livre() { this (0, "", null, 0, 0);}
	public Livre(int id, String titre, Date parution, int nbPages, double prix) {
		super();
		this.id = id;
		this.titre = titre;
		this.parution = parution;
		this.nbPages = nbPages;
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", parution=" + parution + ", nbPages=" + nbPages + ", prix="
				+ prix + "]";
	}
	
	
	
	
}
