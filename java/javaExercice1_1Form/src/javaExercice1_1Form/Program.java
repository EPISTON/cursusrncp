package javaExercice1_1Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		
		int nombre_notes = 0; //compteur de notes
		int note_minimale = Integer.MAX_VALUE; // la note la plus petite
		int note_maximale = Integer.MIN_VALUE; // la note la plus grande
		int somme_notes = 0; // la somme de l'ensemble des notes
		
		while(true) {
			System.out.println("saisir note (-1 pour arreter) :");
			// je recupere la saisie texte de l'utilisateur
			// que je convertit directement en entier
			int note = Integer.parseInt(lecteur.nextLine());
			
			// si la note est -1, on arrete la saisie
			if (note == -1)
				break;
			
			// si cette note est plus petite que le min connu
			// elle devient le nouveau minimum
			if (note < note_minimale)
				note_minimale = note;
			
			if (note > note_maximale)
				note_maximale = note;
			
			// accumulation de la note dans la somme
			somme_notes += note;
			nombre_notes++;
		}
		
		System.out.println("minimum = " + note_minimale);
		System.out.println("maximum = " + note_maximale);
		System.out.println("moyenne = " + (somme_notes / (float)nombre_notes));

	}

}
