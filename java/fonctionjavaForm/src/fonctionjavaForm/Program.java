package fonctionjavaForm;

import java.util.Scanner;

public class Program {

	
//  modificateurs (visibilite...)
//	type de retour (type de donnée renvoyée par la fonction)
//  nom de la fonction
// parametres/arguments passés à la fonction, séparé par des virgules
// bloc de code de la fonction 
	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("votre nom ?");
		String saisie = lecteur.nextLine();
		
		//salutation("vincent");
		salutation(saisie);
		salutation("vincent", "mr");
		System.out.println("message = " + salutation2("vincent", "camarade"));
		
		double d1 = 4.5;
		double d2 = 8.3;
		System.out.println("d1=" + d1 + " ,d2=" + d2);
		swap(d1, d2);
		System.out.println("d1=" + d1 + " ,d2=" + d2);
		
		System.out.println("----------------------------");
		double[] sample = {4.5, 8.3};
		System.out.println("sample[0]=" + sample[0] + " ,sample[1]=" + sample[1]);
		swap(sample);
		System.out.println("sample[0]=" + sample[0] + " ,sample[1]=" + sample[1]);
		
		System.out.println("factorielle(5) -> " + factorielle(5));
		
		System.out.println("moyenne = " + moyenne("bob", 5.5, 2.3, 15.5, 18.6, 11.1));
	}
	// ... permet de spécifier un nombre variable de parametres
	// ceux-ci seront stockés dans un tableau automatiquement
	public static double moyenne(String str, double ... values) {
		double somme = 0.0;
		for (double d : values) {
			somme += d;
		}
		return somme / values.length;
	}
	
	// 5! 5*4*3*2*1
	// 7! 7*6*5*4*3*2*1
	// 0! 1
	public static long factorielle(long n) {
		if ( n <= 1)
			return 1;
		else
			return n * factorielle(n-1);
	}
	
	
	// la base de ma nouvelle startup Math.io
	public static void swap(double[] d) {
		System.out.println("d[0]=" + d[0] + " ,d[1]=" + d[1]);
		double z = d[0];
		d[0] = d[1];
		d[1] = z;
		System.out.println("d[0]=" + d[0] + " ,d[1]=" + d[1]);
	}
	
	// la base de ma nouvelle startup Math.io
	public static void swap(double x, double y) {
		System.out.println("x=" + x + " ,y=" + y);
		double z = x;
		x = y;
		y = z;
		System.out.println("x=" + x + " ,y=" + y);
	}

	
	
	
	// ici, le parametre nom est de type String
	// nom est en fait une variable locale a ma fonction
	// qui recoit le contenu du premier argument passé a
	// ma fonction lors de son appel
	public static void salutation(String nom) {
		salutation(nom, "mr");
		//System.out.println("bonjour, " + nom);
	}
	
	public static void salutation(String nom, String civilite) {
		System.out.println("bonjour, " + civilite + " " + nom);
	}
	
	public static void salutation(String nom, int matricule) {
		System.out.println("bonjour, " + matricule + " " + nom);
	}
	
	public static String salutation2(String nom, String civilite) {
		if (civilite.equals("mr")) {
			return "bien le bonjour, " + civilite + " " + nom;
		}
		return "salut, " + civilite + " " + nom;
	}
	
	
}
