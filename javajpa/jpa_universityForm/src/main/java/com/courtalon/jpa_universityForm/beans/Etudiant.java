package com.courtalon.jpa_universityForm.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Etudiant {
	private int id;
	private String nom;
	private String email;
	
	private Set<Cours> cours;

	@Id @GeneratedValue
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(mappedBy="etudiants")
	public Set<Cours> getCours() {
		if (cours == null)
			cours = new HashSet<>();
		return cours;
	}

	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}

	public Etudiant() { this(0, "", "");}
	public Etudiant(int id, String nom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", email=" + email + "]";
	}

	
	
	
}
