package com.courtalon.springAcademyForm.beans;

public class Jongleur implements IArtiste {
	private String nom;
	private int nbBalles;
	
	@Override
	public String getNom() {return nom;}

	public Jongleur(String nom, int nbBalles) {
		this.nbBalles = nbBalles;
		this.nom = nom;
	}
	
	@Override
	public void faireNumero() {
		System.out.println("moi, " + this.nom + " vais vous impressioner");
		System.out.println("JONGLE JONGLE avec " + this.nbBalles + " balles");
	}

}
