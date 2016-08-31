package com.courtalon.springCamelotForm.beans;

import java.util.Random;

public class QueteBuilder {
	
	private final static String[] ACTIONS = {"sauver", "casser la figure", "ramener", "consulter"};
	private final static String[] OBJETS = {"graal", "dame du lac", "dragon", "oracle"};
	
	// fabrique de quete epique
	public IQuete obtenirQuete() {
		QueteEpique quete = new QueteEpique();
		Random rd =new Random();
		quete.setDescription(ACTIONS[rd.nextInt(ACTIONS.length)] +
							" le/la/au/du " +
							OBJETS[rd.nextInt(OBJETS.length)]);
		quete.setDifficulte(rd.nextDouble());
		return quete;
	}

}
