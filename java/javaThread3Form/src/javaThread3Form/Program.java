package javaThread3Form;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Program {

	public static void main(String[] args) {
/*
		ExecutorService serv =  Executors.newFixedThreadPool(4);
		
		List<Worker1> workers = new ArrayList<>();
		workers.add(new Worker1("prof"));
		workers.add(new Worker1("timide"));
		workers.add(new Worker1("grincheux"));
		workers.add(new Worker1("simplet"));
		workers.add(new Worker1("atchoum"));
		workers.add(new Worker1("dormeur"));
		workers.add(new Worker1("joyeux"));
		
		for (Worker1 w : workers)
			serv.submit(w);
		
		System.out.println("attente de la fin du travail");
		serv.shutdown();
		try {
			while (true) {
				serv.awaitTermination(5, TimeUnit.SECONDS);
				if (serv.isTerminated())
					break;
				if (!workers.isEmpty()) {
					System.out.println("je veux arreter " + workers.get(0).getNom());
					workers.get(0).setMustStop(true);
					workers.remove(0);
				}
				System.out.println("on continue d'attendre");
			}
			
		} catch (InterruptedException e) {e.printStackTrace();}
		*/
		System.out.println("fin du programme 1");
	
		ExecutorService serv2 = Executors.newFixedThreadPool(4);
		
		Future<String> promesse = serv2.submit(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {e.printStackTrace();}
			return "coucou";
		});
		
		System.out.println("attente du resultat");
		try {
			
			System.out.println("j'ai recu -> " + promesse.get());
			
		} catch (InterruptedException e) {e.printStackTrace();}
		catch (ExecutionException e) {e.printStackTrace();}
		
	}

}
