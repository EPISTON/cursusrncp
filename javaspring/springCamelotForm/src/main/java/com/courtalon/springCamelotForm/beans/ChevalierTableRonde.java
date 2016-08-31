package com.courtalon.springCamelotForm.beans;

import java.util.Random;

public class ChevalierTableRonde implements IChevalier {

	private String nom;
	private IQuete quete;
	private Cheval monture;
	private double reussite;
		
	public double getReussite() {return reussite;}
	public void setReussite(double reussite) {this.reussite = reussite;}
	public Cheval getMonture() {return monture;}
	public void setMonture(Cheval monture) {this.monture = monture;}
	public IQuete getQuete() {return quete;}
	public void setNom(String nom) {this.nom = nom;}
	@Override
	public String getNom() {return this.nom;}
	@Override
	public void setQuete(IQuete quete) { this.quete = quete;}
	@Override
	public void partirEnQuete() {
		System.out.println("Moi, Sire "+ getNom() + " part glorieusement en quete de " + getQuete()
				+ " sur mon fidele destrier " + getMonture().getNom() );
		if (getQuete().realiserQuete(getReussite() * 2.0)) {
			System.out.println("Moi, Sire " + getNom() + " revient couvert de gloire");
		}
		else {
			System.out.println("Moi, Sire " + getNom() + " a eu un contretemps");
		}
	}

}
