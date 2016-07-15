package javaExercice2_3bisForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner lecteur = new Scanner(new File("sample.txt"));
		
		StringBuilder sb = new StringBuilder();
		while (lecteur.hasNextLine()) {
			sb.append(lecteur.nextLine() + "\n");
		}
		lecteur.close();
		texteStatistique(sb.toString());
	}
	
	public static void texteStatistique(String texte) {
		// liste des voyelles
		String voyelles = "aeiouy";
		// liste des caracteres de mots
		String caracteresMot = "azertyuiopqsdfghjklmwxcvbn0123456789";
		// tableau des compteurs pour chaque voyelles du texte
		int[] compteurs = new int[voyelles.length()];
		int nbVoyelles = 0;
		int nbMots = 0;
		boolean dansMot = false; // au début, nous ne somme pas dans un mot
		for (int pos = 0; pos < texte.length(); pos++){
			char c = texte.charAt(pos); // je récupere le caractere courant
			// je regarde si le caractere est une des voyelles
			for (int index = 0; index < voyelles.length(); index++) {
				if (c == voyelles.charAt(index)) {
					compteurs[index]++;
					nbVoyelles++;
					break;
				}
			}
			// je verifie si nous somme dans un mot ou pas
			if (caracteresMot.indexOf(Character.toLowerCase(c)) >= 0) {
				// c'est un caractere de mot
				dansMot = true;
			}
			else {
				// ce n'est pas un caractere de mot
				if (dansMot)
					nbMots++;
				dansMot = false;
			}
		}
		if (dansMot)
			nbMots++;
		
		
		for (int index = 0; index < voyelles.length(); index++) {
			System.out.println("proportion de " + voyelles.charAt(index)
								+ " = " + (compteurs[index] *100.0 / nbVoyelles)
								+ "%");
		}
		System.out.println("nombre de mots = " + nbMots);
	}
	
	
	

}
