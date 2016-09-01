package com.courtalon.springAcademyForm.beans;

import java.util.Random;

public class Publique {
	
	public void applaudirAvant(IArtiste artiste) {
		System.out.println("bravo! " + artiste.getNom() + " clap clap clap clap");
	}

	public void applaudirApres(IArtiste artiste) {
		Random rd = new Random();
		if (rd.nextDouble() > 0.4) {
			System.out.println("CLAP CLAP hourra, bis, " + artiste.getNom());
		}
		else
			System.out.println("Booouuuh, rembourser, [une tomate vole vers "
													+ artiste.getNom() + " ]");
		
	}

}
