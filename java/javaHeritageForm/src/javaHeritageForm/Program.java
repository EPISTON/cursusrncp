package javaHeritageForm;

import java.util.Random;

import humanRessource.Client;
import humanRessource.Employe;
import humanRessource.Personne;

public class Program {

	public static void main(String[] args) {
		//Personne p1 = new Personne("Kylo", "ren");
		Client c1 = new Client("Solo", "Han", "han@leboncoin.gx", -300.0);
		//p1.afficher();
		c1.afficher();
		
		// Client -> Personne : conversion implicite
		// la conversion enfant -> parent marche toujours, donc implicite
		Personne p2 = new Client("Binks", "jarjar", "jarjar@sithlord.com", 450.0);
		p2.afficher();

		// parent -> enfant doit etre explicite, elle peut echouer
		// si l'objet présent dans la variable parent n'est pas "compatible"
		// avec le type enfant vers lequel on convertit
		Random rd = new Random();
		
		if (rd.nextBoolean())
			p2 = new Client("skywalker", "luke", "luke@jedibook.com", 200.0);
		else
			p2 = new Client();
			//p2 = new Personne("leHut", "jabba");
		
		Client c2;
		// instanceof permet de tester si une variable contient un objet
		// du type voulu
		if (p2 instanceof Client){
			c2 = (Client)p2;
			System.out.println(c2.getEmail());
		}
		else {
			c2 = null;
			System.out.println("p2 n'est pas un client!");
		}
		
		Employe e1 = new Employe("chewbacca", "chewie", "mecanicien", 8000);
		e1.methode1();
		
		// la classe Objecte est a la "racine" de l'heritage
		// tous les objets en JAVA descende de la classe Object
		Object o1 = e1;
		
		System.out.println(e1);
		System.out.println(p2);
		System.out.println(o1);
		/*
		Personne pers1 = new Personne("kenobi", "obiwan");
		Personne pers2 = new Personne("kenobi", "obiwan");
		System.out.println("pers1 == pers2 => " + (pers1 == pers2));
		System.out.println("pers1 equals pers2 => " + (pers1.equals(pers2)));
		System.out.println("hash pers1 => " + pers1.hashCode());
		System.out.println("hash pers2 => " + pers2.hashCode());
*/
		Personne[] peoples = new Personne[6];
		for (int i = 0; i < peoples.length; i++) {
			if (rd.nextBoolean())
				peoples[i] = new Client("bob" + i, "eponge"+ i, "bob@eponge"+i, 1000);
			else
				peoples[i] = new Employe("patrick" + i, "star" +i, "poste" + i, 1000);
		}
		
		for (Personne p : peoples)
			p.contacter("bonjour");
		
	}

}
