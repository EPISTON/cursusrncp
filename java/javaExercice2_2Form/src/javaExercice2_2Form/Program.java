package javaExercice2_2Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("somme initiale ? ");
		double somme = Double.parseDouble(lecteur.nextLine());
		System.out.println("somme visée ? ");
		double objectif = Double.parseDouble(lecteur.nextLine());
		System.out.println("taux de rénumération ? ");
		double taux = Double.parseDouble(lecteur.nextLine());
		
		System.out.println("vous devez attendre " 
							+ calculDuree(somme, objectif, taux)
							+ " annees");

	}
	
	public static int calculDuree(double somme, double objectif, double taux) {
		int annee = 0;
		// conversion du taux 5% -> 0.05
		taux = taux / 100.0;
		while (somme < objectif) {
			somme += somme * taux; // chaque année, rajouter les intérêts cumulés
			annee++;
		}
		return annee;
	}
	

}
