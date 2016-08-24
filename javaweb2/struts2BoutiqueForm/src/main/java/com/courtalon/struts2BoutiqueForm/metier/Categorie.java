package com.courtalon.struts2BoutiqueForm.metier;

public class Categorie {
	private int id;
	private String libelle;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}

	public Categorie() { this(0, "");}
	public Categorie(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	

}
