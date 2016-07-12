package javacontroleForm;
import java.util.*;

public class Program {

	public static void main(String[] args) {
		// la classe scanner permet de lire le contenu d'un flux
		// ici, en l'occurence, les saisies utilisateur
		Scanner lecteur = new Scanner(System.in);
		
		System.out.println("quelle heure est il ?");
		// je demmande au scanner la prochaine ligne saisie
		String saisie = lecteur.nextLine();
		
		//System.out.println(saisie);
		
		// Integer.parseInt permet de convertir une chaine de caractere
		// en Entier
		int heure = Integer.parseInt(saisie);
		
		
		// le bloc if teste la condition entre parenthese
		// et execute le bloc de code suivant si la condition est vrai
		if (heure < 12) {
			System.out.println("bonjour, un café?");
		}
		else if(heure < 15) {
			System.out.println("bonjour, bon appétit");
		}
		else { // le bloc sinon est executé dans le cas contraire
			System.out.println("bonjour, belle journée!");
		}
		
		System.out.println("-----------------------------");
		
		// switch permet de choisir le code a executer en fonction
		// de la valeur contenu dans une variable
		switch(heure) {
			case 8:
				System.out.println("mooouuiii...");
			case 9:
			case 10:
			case 11:
				System.out.println("bonjour, un café");
				break;
			case 12:
			case 13:
			case 14:
				System.out.println("bonjour, bon appétit");
				break;
			default:
				System.out.println("bonjour, belle journée");
				break;
		}
		
		//  == -> egalité, != -> différent
		//  < , >, <=, >=  -> inferieur strictement, etc.
		//  on ne compare JAMAIS JAMAIS les String avec == !!!!!
		
		int i = 0;
		
		// les boucles
		while( i < 10) {
			System.out.println("i = " + i);
			// ++ = postincrement -> augmente la valeur de 1
			// -- = postdécrement -> diminue la valeur de 1
			i++; // <=> i += 1; //  <=>  i = i + 1;
		}
		
		i = 5;
		int j = i++;
		System.out.println("i = " + i + " j = " + j);
		
		i = 5;
		j = ++i;
		System.out.println("i = " + i + " j = " + j);
		
		// la boucle do while permet de ne tester la "sortie"
		// de la boucle qu'a la fin de celle-ci
		// autrement dit, cette boucle sera au moins exécutée
		// une fois, meme si la condition n'est pas remplie
		i = 12;
		do {
			System.out.println("i = " + i);
			i++;
		}while(i < 10);
		
		System.out.println("-----------------------------");
		
		// for (initialisation boucle; condition arret; mise a jour)
		for (int k = 0; k < 10; k++) {
			if (k == 5)
				continue; // sauter a l'iteration 
			if (k == 8)
				break; // sortie prématurée de la boucle
			System.out.println("k = " + k);
		}
		
		
		
		
	}

}
