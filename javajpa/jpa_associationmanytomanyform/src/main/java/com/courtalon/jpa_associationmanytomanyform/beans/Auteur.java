package com.courtalon.jpa_associationmanytomanyform.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Auteur {
	private int id;
	private String nom;
	private String email;
	private String langue;
	
	private Set<Livre> livres;

	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getLangue() {return langue;}
	public void setLangue(String langue) {this.langue = langue;}

	@ManyToMany(mappedBy="auteurs")
	public Set<Livre> getLivres() {
		return livres;
	}
	public void setLivres(Set<Livre> livres) {this.livres = livres;}
	
	public Auteur() { this(0, "", "", "");}
	public Auteur(int id, String nom, String email, String langue) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.langue = langue;
	}
	
	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", email=" + email + ", langue=" + langue + "]";
	}
	
	
	

}
