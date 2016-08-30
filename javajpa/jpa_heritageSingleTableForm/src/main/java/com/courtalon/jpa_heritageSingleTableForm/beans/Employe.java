package com.courtalon.jpa_heritageSingleTableForm.beans;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="2")
public class Employe extends Personne {
	private String poste;
	private double salaire;
	
	public String getPoste() {return poste;}
	public void setPoste(String poste) {this.poste = poste;}
	
	//@Column(nullable=false)
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
