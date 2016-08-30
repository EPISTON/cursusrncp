package com.courtalon.jpa_universityForm.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professeur {
	private int id;
	private String nom;
	private double salaire;
	
	
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

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	@OneToMany(mappedBy="professeur")
	public Set<Cours> getCours() {
		if (cours == null)
			cours = new HashSet<>();
		return cours;
	}

	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}

	public Professeur() { this(0, "", 0);}
	public Professeur(int id, String nom, double salaire) {
		super();
		this.id = id;
		this.nom = nom;
		this.salaire = salaire;
	}

	@Override
	public String toString() {
		return "Professeur [id=" + id + ", nom=" + nom + ", salaire=" + salaire + "]";
	}
	
	
	

}
