package com.courtalon.jpa_universityForm.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cours {
	private int id;
	private Date date;
	private int capaciteMax;
	
	private Set<Etudiant> etudiants;
	private Matiere matiere;
	private Professeur professeur;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCapaciteMax() {
		return capaciteMax;
	}
	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}
	
	@ManyToMany
	public Set<Etudiant> getEtudiants() {
		if (etudiants == null)
			etudiants = new HashSet<>();
		return etudiants;
	}
	public void setEtudiants(Set<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
	@ManyToOne
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
	@ManyToOne
	public Professeur getProfesseur() {
		return professeur;
	}
	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}
	
	public Cours() { this(0, null, 0);}
	public Cours(int id, Date date, int capaciteMax) {
		super();
		this.id = id;
		this.date = date;
		this.capaciteMax = capaciteMax;
	}
	
	@Override
	public String toString() {
		return "Cours [id=" + id + ", date=" + date + ", capaciteMax=" + capaciteMax + "]";
	}
	
	
	
	
}
