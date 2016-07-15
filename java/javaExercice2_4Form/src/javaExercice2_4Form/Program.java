package javaExercice2_4Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("année ? ");
		int annee = Integer.parseInt(lecteur.nextLine());
		System.out.println("mois ? ");
		int mois = Integer.parseInt(lecteur.nextLine());
		calendrier(annee, mois);
	}
	
	public static void calendrier(int annee, int mois) {
		cal_head(annee, mois);
		cal_body(annee, mois);
	}
	
	public static void cal_head(int annee, int mois) {
		System.out.println("----------------------------");
		System.out.println("     " + nomMois(mois) + " " + annee);
		System.out.println("----------------------------");
		System.out.println(" LUN MAR MER JEU VEN SAM DIM");
	}
	
	public static String nomMois(int mois) {
		switch(mois) {
			case 1: return "janvier";
			case 2: return "fevrier";
			case 3: return "mars";
			case 4: return "avril";
			case 5: return "mai";
			case 6: return "juin";
			case 7: return "juillet";
			case 8: return "aout";
			case 9: return "septembre";
			case 10: return "octobre";
			case 11: return "novembre";
			case 12: return "decembre";
		}
		return "";
	}
	
	public static void cal_body(int annee, int mois) {
		int nbjours = nbjoursMois(annee, mois);
		int jourDebut = jourDebutMois(annee, mois);
		//System.out.println("nb jours = " + nbjours);
		//System.out.println("jourDebut = " + jourDebut);
		
		// retrait initial pour caler sur le bon jour de la semaine
		for (int j = 0; j < jourDebut; j++)
			System.out.print("    ");
		
		for (int j = 1; j <= nbjours; j++) {
			if (j < 10)
				System.out.print(" ");
			System.out.print("  " + j);
			if ((j + jourDebut) % 7 == 0)
				System.out.println();
		}
		
	}

	public static int jourDebutMois(int annee, int mois) {
		int compteurJours = 2; // le 1ere janvier 1800 est un mercredi
		
		// compte les jours des années précédentes
		for (int an = 1800; an < annee; an++)
			compteurJours += (est_bissextile(an)? 366 : 365);
		
		// compte les jours des mois précédents
		for (int m = 1; m < mois; m++)
			compteurJours += nbjoursMois(annee, m);
		
		return compteurJours % 7; // renvoie le numéro du jour de la semaine
	}
	

	public static int nbjoursMois(int annee, int mois) {
		switch(mois) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				return (est_bissextile(annee)) ? 29 : 28;
		}
		return 0;
	}
	
	
	public static boolean est_bissextile(int annee) {
		return ((annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0);
	}
	
	
	
}
