package javaThread2Form;

public class CompteurPartage {
	
	private int compteur;
	public int getCompteur() {
		return compteur;
	}


	public CompteurPartage() {
		this.compteur = 0;
	}
	
	public void incrementeCompteur() {
		int valeur = getCompteur();
		valeur++;
		this.compteur = valeur;
	}

}
