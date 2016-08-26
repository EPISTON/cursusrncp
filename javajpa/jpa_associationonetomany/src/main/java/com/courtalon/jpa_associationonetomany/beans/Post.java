package com.courtalon.jpa_associationonetomany.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	private int id;
	private String titre;
	private String corps;
	private int rating;
	private Auteur auteur;
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	public int getRating() {return rating;}
	public void setRating(int rating) {this.rating = rating;}
	
	/*
	* fetch permet de configurer si hibernate precharge ou pas l'association 
	* lazy -> chargement paresseux, le chargement ne se fera qu'au dernier moment si nécéssaire
	* eager -> chargement précoce, le chargement se fait tout de suite
	* 
	* cascade, propagation d'opérations via les associations
	* par exemple cascase remove -> propagation de l'effacement
	*/	
	@ManyToOne(/*fetch=FetchType.LAZY*/ cascade=CascadeType.PERSIST)
	public Auteur getAuteur() {return auteur;}
	public void setAuteur(Auteur auteur) {this.auteur = auteur;}
	
	
	public Post() { this(0, "", "", 0);}
	public Post(int id, String titre, String corps, int rating) {
		super();
		this.id = id;
		this.titre = titre;
		this.corps = corps;
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", titre=" + titre + ", corps=" + corps + ", rating=" + rating + "]";
	}
	
	

}
