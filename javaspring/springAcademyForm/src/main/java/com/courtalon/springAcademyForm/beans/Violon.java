package com.courtalon.springAcademyForm.beans;

import org.springframework.beans.factory.BeanNameAware;

public class Violon implements IInstrument, BeanNameAware
{
	private String nom;
	private String sonorite;
	
	
	public String getSonorite() {return sonorite;}
	public void setSonorite(String sonorite) {this.sonorite = sonorite;}

	public void accorder(){
		System.out.println("j'accorde le violon " + this.nom);
	}
	
	public Violon() {
		setSonorite("wiooouuunnnnggwiaaiiiing");
	}
	// cette méthode provient de l'interface Spring BeanNameAware
	// Spring l'appelera automatiquement pour m'injeceter l'identifiant du bean
	@Override
	public void setBeanName(String name) {
		this.nom = name;
	}
	
	@Override
	public void jouer() {
		System.out.println(this.nom + ": " + getSonorite());

	}
}
