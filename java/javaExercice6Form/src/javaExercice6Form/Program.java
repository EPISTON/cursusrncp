package javaExercice6Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		try {
			double t = saisie_temp();
			System.out.println("en kelvin: " + (t + 273.15));
			System.out.println("en fahrenheit: " + (t * (9/5.0) + 32));
		}
		catch (TemperatureException ex) {
			System.out.println(ex.getMessage() + " -> " + ex.getTemp());
		}
		System.out.println("merci d'avoir utilisé notre convertisseur");

	}
	
	public static double saisie_temp() {
		Scanner reader = new Scanner(System.in);
		// boucle infini, on en sort via le return
		while(true) {
			try {
				System.out.println("saisir temperature en degre celsius");
				double temp = Double.parseDouble(reader.nextLine());
				if (temp < -273.15) // zero absolu
				{
					// gestion d'erreur
					throw new TemperatureException(temp);
				}
				else
					return temp; //on ne retourne la temperature que si valeur correcte
			}
			catch (NumberFormatException ex) {
				System.out.println("ce n'est pas un nombre! recommencez");
			}
		}
	}

}
