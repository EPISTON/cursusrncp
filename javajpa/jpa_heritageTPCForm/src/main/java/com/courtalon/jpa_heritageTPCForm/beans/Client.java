package com.courtalon.jpa_heritageTPCForm.beans;

import javax.persistence.Entity;

@Entity
public class Client extends Personne {
	private String codeClient;
	private double soldeCompte;
	
	public String getCodeClient() {return codeClient;}
	public void setCodeClient(String codeClient) {this.codeClient = codeClient;}
	public double getSoldeCompte() {return soldeCompte;}
	public void setSoldeCompte(double soldeCompte) {this.soldeCompte = soldeCompte;}
	
	
	public Client() { this(0, "", "", "", 0.0);}
	public Client(int id, String nom, String prenom, String codeClient, double soldeCompte) {
		super(id, nom, prenom);
		this.codeClient = codeClient;
		this.soldeCompte = soldeCompte;
	}
	
	@Override
	public String toString() {
		return "Client [codeClient=" + codeClient + ", soldeCompte=" + soldeCompte + ", getId()=" + getId()
				+ ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + "]";
	}
	
	
		

}
