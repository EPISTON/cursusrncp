package com.courtalon.jpa_associationmanytomanyform.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	// la fonction utilitaire qui met en place une association
	// entre un livre et un auteur
	public void addAuteur(Auteur a) {
		if ( a == null)
			return;
		getAuteurs().add(a); // ici, provoquera une insertion dans la table de jointure
		a.getLivres().add(this); // purement utilitaire, n'affecte pas la base de données
	}
	
	// par defaut en lazy, EAGER forcerais hibernate a précharger les auteurs des qu'un livre
	// est requetté
	@ManyToMany(/*fetch=FetchType.EAGER*/)
	public Set<Auteur> getAuteurs() {
		// creer la collection si elle n'est pas déjà présente
		// la première fois qu'on y accede
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
