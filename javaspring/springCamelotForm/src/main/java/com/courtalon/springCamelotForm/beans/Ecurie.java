package com.courtalon.springCamelotForm.beans;

import java.util.List;
import java.util.Random;

public class Ecurie {
	
	private List<String> nomChevaux;

	public List<String> getNomChevaux() {
		return nomChevaux;
	}
	public void setNomChevaux(List<String> nomChevaux) {
		this.nomChevaux = nomChevaux;
	}

	public Cheval fournirCheval() {
		Random rd = new Random();
		Cheval c = new Cheval();
		c.setNom(nomChevaux.get(rd.nextInt(nomChevaux.size())));
		System.out.println("l'ecurie cree le cheval " + c.getNom());
		return c;
	}

}
