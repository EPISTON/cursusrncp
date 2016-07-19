package javaCollection;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

	public static void main(String[] args) {
		
		ArrayList tab = new ArrayList();
		
		tab.add("lundi");
		tab.add("mardi");
		tab.add("mercredi");
		
		for (Object str: tab) {
			System.out.println(str);
		}
		System.out.println("taille tab = " + tab.size());
	
		System.out.println("-----------------------");
		tab.add(1, "bonjour");
	
		for (Object str: tab) {
			System.out.println(str);
		}
	
		tab.remove(2);
		System.out.println("-----------------------");
		for (Object str: tab) {
			System.out.println(str);
		}
		
		String resultat = (String)tab.get(0) + (String)(tab.get(1));
	
		// collection générique
		ArrayList<String> tab2 = new ArrayList<>();
		
		tab2.add("janvier"); // si pas de position, ajout a la fin
		tab2.add("fevrier");
		// le compilateur SAIT que ce sont des chaine de caracteres
		System.out.println(tab2.get(0) + tab2.get(1));
		
		// tableau associatif
		HashMap<String, Double> map = new HashMap<>();
		map.put("vincent", 45.5);
		map.put("john", 75.5);
		map.put("arnold", 28.2);
		
		System.out.println(map.get("john"));
		
		double d = 5.5;
		
		Double d2 = 5.5;
		
		// ici, je choisi le type concret de T1 et T2 a la declaration de la paire
		Paire<String, Double> paire1 = new Paire<String, Double>("hello", 5.5);
		System.out.println(paire1);
		
		Paire<Double, Boolean> paire2;
		
		// paire1 et paire2 ne sont pas du même type
		//paire2 = paire1;
		
		//paire1.setValeur1(15.5);
		
		
		//d2.
		
		FenetreListe f1 = new FenetreListe();
		f1.setVisible(true);
		
	}

}
