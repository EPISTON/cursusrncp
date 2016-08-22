package com.courtalon.struts2EnchereForm.metier;

public class Enchere {
	private int id;
	private String description;
	private double prixDepart;
	private double enchereMinimum;
	private double prixActuel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrixDepart() {
		return prixDepart;
	}
	public void setPrixDepart(double prixDepart) {
		this.prixDepart = prixDepart;
	}
	public double getEnchereMinimum() {
		return enchereMinimum;
	}
	public void setEnchereMinimum(double enchereMinimum) {
		this.enchereMinimum = enchereMinimum;
	}
	public double getPrixActuel() {
		return prixActuel;
	}
	public void setPrixActuel(double prixActuel) {
		this.prixActuel = prixActuel;
	}
	public Enchere() { this(0, "", 0, 0 ,0); }
	public Enchere(int id, String description, double prixDepart, double enchereMinimum, double prixActuel) {
		super();
		this.id = id;
		this.description = description;
		this.prixDepart = prixDepart;
		this.enchereMinimum = enchereMinimum;
		this.prixActuel = prixActuel;
	}
	
	
}
