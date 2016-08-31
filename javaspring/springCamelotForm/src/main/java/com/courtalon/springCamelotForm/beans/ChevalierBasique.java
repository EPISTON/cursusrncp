package com.courtalon.springCamelotForm.beans;

public class ChevalierBasique implements IChevalier {

	private String nom;
	private IQuete quete;
	private double reussite;
	
	public double getReussite() {return reussite;}
	public void setReussite(double reussite) {this.reussite = reussite;}
	public void setNom(String nom) {this.nom = nom;}
	@Override
	public String getNom() {return nom;}
	@Override
	public void setQuete(IQuete quete) {this.quete = quete;}
	@Override
	public void partirEnQuete() {
		System.out.println("Moi, " + getNom() + " part joyeusement en quete de " + this.quete);
		if (this.quete.realiserQuete(getReussite())) {
			System.out.println("Moi, " + getNom() + " reviens victorieusement de quete");
		}
		else
			System.out.println("Moi, " + getNom() + " a echoué dans ma quete");
	}

}
