package com.courtalon.jpa_universityForm.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Matiere {
	private int id;
	private String libelle;
	
	private Set<Cours> cours;

	@Id @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(mappedBy="matiere")
	public Set<Cours> getCours() {
		if (cours == null)
			cours = new HashSet<>();
		return cours;
	}

	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}

	public Matiere() { this(0, "");}
	public Matiere(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Matiere [id=" + id + ", libelle=" + libelle + "]";
	}
	
	
}
