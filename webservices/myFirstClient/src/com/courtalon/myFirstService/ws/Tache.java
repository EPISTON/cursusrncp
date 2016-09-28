package com.courtalon.myFirstService.ws;

public class Tache {
	private int id;
	private String titre;
	private int priorite;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getPriorite() {
		return priorite;
	}
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
	public Tache() { this(0, "", 0);}
	public Tache(int id, String titre, int priorite) {
		super();
		this.id = id;
		this.titre = titre;
		this.priorite = priorite;
	}
	
	
	
}
