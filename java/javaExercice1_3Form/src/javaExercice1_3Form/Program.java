package javaExercice1_3Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("taille du triangle ?");
		
		int taille = Integer.parseInt(lecteur.nextLine());
		
		// afficher le bon nombre de lignes
		for (int ligne = 0; ligne < taille; ligne++) {
			// afficher le bon nombre de caractere pour cette ligne
			for (int colonne = 0; colonne < taille - ligne; colonne++) {
				if (ligne == 0 || colonne == 0 || colonne == taille - ligne -1)
					System.out.print("*");
				else
					System.out.print("-");
			}
			System.out.println();
		}
		
		// dessin du losange
		int taille_demi = taille / 2 + 1;
		String demi_losange_haut = "";
		String demi_losange_bas = "";
		
		for (int ligne = 0; ligne < taille_demi; ligne++) {
			String ligne_courante = "";
			
			// génération du retrait/marge
			for (int colonne = 0; colonne <taille_demi - ligne - 1; colonne++)
				ligne_courante += " ";
			
			// génération du corps du losange
			for (int colonne = 0; colonne < ligne * 2 + 1; colonne++) {
				if (colonne == 0 || colonne == ligne * 2)
					ligne_courante += "*";
				else
					ligne_courante += "-";
			}
			ligne_courante += "\n";
			demi_losange_haut = demi_losange_haut + ligne_courante;
			if (ligne < taille_demi - 1)
				demi_losange_bas = ligne_courante + demi_losange_bas;
		}
		System.out.println(demi_losange_haut + demi_losange_bas);
		
	
	}

}
