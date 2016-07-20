package javaException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		System.out.println("votre age ? ");
		try {
			int age = saisie_age();
			
			if (age < 20)
				System.out.println("salut!");
			else if (age < 210)
				System.out.println("bonjour");
			else {
				System.out.println("les aliens sont parmis nous");
				return;
			}
		}
		catch (AgeException ex) {
			System.out.println("l'age est invalide");
		}
		catch (Exception ex) {
			System.out.println("ouhla, une erreur est survenue..." + ex.getMessage());
		}
		finally {
			System.out.println("nettoyage");
		}
		
		System.out.println("fin du programme");
		
	}
	
	public static int saisie_age()
	{
		Scanner reader = new Scanner(System.in);
		int age = 0;
		while (true) {
			try {
				System.out.println("votre age ?");
				String saisie = reader.nextLine();
				age = Integer.parseInt(saisie);
				if (age < 0 || age > 200) {
					// ici, nous déclenchons notre porpre erreur personnalisée
					// pour signaler un age incohérent
					// en utilisant notre propre classe AgeException
					// et le mot clé throw
					// Cette exception ne force pas le catch, car elle
					// hérite de runtimeException, c'est une exception "masquable"
					AgeException ageex = new AgeException();
					throw ageex;
				}
				return age;
			}
			catch (NumberFormatException ex) {
				System.out.println("age non numerique " + ex.getMessage());
			}
		}
	}

}
