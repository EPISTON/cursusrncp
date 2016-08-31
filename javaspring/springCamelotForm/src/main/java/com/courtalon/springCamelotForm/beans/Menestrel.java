package com.courtalon.springCamelotForm.beans;

public class Menestrel {

	private String nom;

	public Menestrel(String nom) {
		this.nom = nom;
	}
	
	public void chanterAvant(IChevalier chevalier) {
		System.out.println(this.nom + ": TRALALALA , sire " + chevalier.getNom() 
								+ " part en riant pour sa quete" );
	}
	
	public void chanterApres(IChevalier chevalier) {
		System.out.println(this.nom + ": PLOUPLOUMPLOUM, sire " + chevalier.getNom()
								+ " revient au grand plaisir du royaume!");
	}
	
	
}
