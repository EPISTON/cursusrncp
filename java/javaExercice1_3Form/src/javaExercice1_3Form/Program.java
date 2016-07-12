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
		
	
	}

}
