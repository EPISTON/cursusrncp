package com.courtalon.jpa_heritagejoinform.beans;

import javax.persistence.Entity;

@Entity
public class Employe extends Personne {
	private String poste;
	private double salaire;
	
	public String getPoste() {return poste;}
	public void setPoste(String poste) {this.poste = poste;}
	public double getSalaire() {return salaire;}
	public void setSalaire(double salaire) {this.salaire = salaire;}
	
	public Employe() { this(0, "", "", "", 0.0);}
	public Employe(int id, String nom, String prenom, String poste, double salaire) {
		super(id, nom, prenom);
		this.poste = poste;
		this.salaire = salaire;
	}
	@Override
	public String toString() {
		return "Employe [poste=" + poste + ", salaire=" + salaire + ", getId()=" + getId() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + "]";
	}
	
}
