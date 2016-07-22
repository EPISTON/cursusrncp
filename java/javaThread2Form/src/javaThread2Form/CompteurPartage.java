package javaThread2Form;

import java.util.concurrent.atomic.AtomicInteger;

public class CompteurPartage {
	
	private AtomicInteger compteur;
	public int getCompteur() {
		return compteur.get();
	}

	private Object verrou1;

	public CompteurPartage() {
		this.compteur = new AtomicInteger(0);
		verrou1 = new Object();
	}
	// synchronized interdit d'etre plusieurs a executer cette fonction en même temps
	// (*) il y a des subtilité
	public /*synchronized*/ void incrementeCompteur() {
		compteur.incrementAndGet();
		
		/*synchronized(verrou1) {
			int valeur = getCompteur();
			valeur++;
			this.compteur = valeur;
		}*/
	}

}
