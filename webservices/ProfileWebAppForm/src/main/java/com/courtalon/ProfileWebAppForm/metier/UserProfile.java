package com.courtalon.ProfileWebAppForm.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserProfile {
	private int id;
	private String nom;
	private String email;
	private String ville;
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getVille() {return ville;}
	public void setVille(String ville) {this.ville = ville;}

	public UserProfile() { this(0, "", "", "");}
	public UserProfile(int id, String nom, String email, String ville) {
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.ville = ville;
	}
	
	
	
}
