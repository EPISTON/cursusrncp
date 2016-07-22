package javaThread3Form;

import java.util.concurrent.atomic.AtomicBoolean;

public class Worker1 implements Runnable {

	private String nom;
	private AtomicBoolean mustStop;
	
	public boolean isMustStop() {
		return mustStop.get();
	}
	public void setMustStop(boolean mustStop) {
		this.mustStop.set(mustStop);
	}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}

	
	public Worker1(String nom) {
		setNom(nom);
		this.mustStop = new AtomicBoolean(false);
	}

	@Override
	public void run() {
		for (int i = 1; i <= 40; i++) {
			try {
				Thread.sleep(1000);// on attend 1000 millisecondes
			} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(getNom() + " iteration = " + i);
			if (isMustStop()) {
				break;
			}
		}
		System.out.println(getNom() + " travail fini!");
	}

}
