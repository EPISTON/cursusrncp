package com.courtalon.springAcademyForm.beans;

import org.springframework.beans.factory.BeanNameAware;

public class Piano implements IInstrument, BeanNameAware
{
	private String nom;
	
	// cette méthode provient de l'interface Spring BeanNameAware
	// Spring l'appelera automatiquement pour m'injeceter l'identifiant du bean
	@Override
	public void setBeanName(String name) {
		this.nom = name;
	}
	
	@Override
	public void jouer() {
		System.out.println(this.nom + ": PLOUMPLIMPLOUM!");

	}

	

}
