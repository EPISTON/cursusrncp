package com.courtalon.springCamelotForm.beans;

import java.util.Random;

public class QueteEpique implements IQuete {

	private String description;
	private double difficulte;
	
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public double getDifficulte() {return difficulte;}
	public void setDifficulte(double difficulte) {this.difficulte = difficulte;}
	
	// les quetes epiques sont plus difficiles
	@Override
	public boolean realiserQuete(double reussite) {
		Random rd = new Random();
		if ((reussite * rd.nextDouble() > difficulte) && (reussite * rd.nextDouble() > difficulte))
			return true;
		else
			return false;
	}
	@Override
	public String toString() {
		return "QueteEpique [description=" + description + "]";
	}

}
