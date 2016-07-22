package javaThread2Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		// le compteur qui sera utilisé en même temps par les 2 threads
		CompteurPartage compteur = new CompteurPartage();
		
		// les deux travailleurs (Thread) qui accederont au même compteur
		WorkerCompteur w1 = new WorkerCompteur("bob", compteur);
		WorkerCompteur w2 = new WorkerCompteur("joe", compteur);
		
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);
		
		System.out.println("appuyez sur entrée pour commencer");
		reader.nextLine();
		// démarrage des threads
		t1.start();
		t2.start();
		
		try {
			// on attends qu'ils se terminent
			t1.join();
			t2.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		// on affiche le resultat du compteur partagé
		System.out.println("fini!!!");
		System.out.println("valeur compteur partagé = " + compteur.getCompteur());
		StringBuffer sb = new StringBuffer();
		

	}

}
