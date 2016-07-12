package javaExercice1_2Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("texte a tester");
		String saisie = lecteur.nextLine();
		
		int debut = 0;
		int fin = saisie.length() - 1;
		boolean est_palindrome = true;
		while(debut < fin) {
			if (saisie.charAt(debut) != saisie.charAt(fin)) {
				est_palindrome = false;
				break;
			}
			debut++;
			fin--;
		}
		
		if (est_palindrome)
			System.out.println("c'est un palindrome");
		else
			System.out.println("ce n'est pas un palindrome");

	}

}
