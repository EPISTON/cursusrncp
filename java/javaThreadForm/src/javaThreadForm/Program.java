package javaThreadForm;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("appuyez sur entrée pour commencer");
		reader.nextLine();
		
		Thread t2 = new Thread(() -> compter());
		/*Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				compter();
			}
		});*/
		
		Date debut = new Date();
		// demarrage de l'execution du thread T2
		t2.start();
		
		System.out.println("----------------------");
		compter();
		
		// attend la fin du thread t2
		try {
			t2.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		Date fin = new Date();
		System.out.println("temps ecoulé = " + (fin.getTime() - debut.getTime()));

	}
	
	public static void compter() {
		Random rd = new Random();
		double d = rd.nextDouble();
		for (long l = 0; l < 1*1000*1000*1000; l++) {
			d = Math.sqrt(d * Math.PI);
			if (l % (50*1000*1000) == 0) {
				System.out.println("iteration = " + l);
			}
		}
		System.out.println("d = " + d);
	}
	

}
