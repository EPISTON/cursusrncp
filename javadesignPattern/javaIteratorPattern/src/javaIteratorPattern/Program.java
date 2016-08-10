package javaIteratorPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Program {

	public static void main(String[] args) {
		List<String> films = new ArrayList<>();
		films.add("oui oui a la campagne");
		films.add("independance day 5, le retour de la vengeance");
		films.add("l'architecte java qui sauva le monde");
		films.add("la soupe au quinoa");
		
		for (String titre : films) {
			System.out.println(titre);
		}
		System.out.println("-----------------------");
		
		// je récupere un nouveau "curseur" pour parcourir ma liste de films
		Iterator<String> curseur1 = films.iterator();
		// hasNext vérifie s'il ya encore des elements a parcourir dans la collection
		while (curseur1.hasNext()) {
			// next récupere le prochain element et met a jour le curseur
			String value= curseur1.next();
			System.out.println(value);
		}
		System.out.println("---------------------");
		Interval i1 = new Interval(5, 12);
		System.out.println(i1);
		
		for (int i : i1) {
			System.out.println(i);
		}

	}

}
