package javaExercice2_1Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("saisissez du texte a inverser");
		String texte = lecteur.nextLine();
		String resultat = inverse(texte);
		System.out.println(resultat);

	}
	
	public static String inverse(String chaine) {
		// j'utilise un Stringbuilder pour les performances
		StringBuilder sb = new StringBuilder(chaine.length());
		
		// je parcour ma chaine en sens inverse
		// et je construit ma chaine inversée avec le StringBuilder
		for (int pos = chaine.length() - 1; pos >= 0; pos--) {
			sb.append(chaine.charAt(pos));
		}
		return sb.toString();
	}

}
