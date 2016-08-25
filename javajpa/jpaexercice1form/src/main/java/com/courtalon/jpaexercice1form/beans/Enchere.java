package com.courtalon.jpaexercice1form.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Enchere {
	
	private int id;
	private String description;
	private double prixDepart;
	private double prixActuel;
	private double enchereMinimum;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length=200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="P_depart")
	public double getPrixDepart() {
		return prixDepart;
	}
	public void setPrixDepart(double prixDepart) {
		this.prixDepart = prixDepart;
	}
	@Column(name="P_actuel")
	public double getPrixActuel() {
		return prixActuel;
	}
	public void setPrixActuel(double prixActuel) {
		this.prixActuel = prixActuel;
	}
	public double getEnchereMinimum() {
		return enchereMinimum;
	}
	public void setEnchereMinimum(double enchereMinimum) {
		this.enchereMinimum = enchereMinimum;
	}
	public Enchere() { this(0, "", 0,0,0);}
	public Enchere(int id, String description, double prixDepart, double prixActuel, double enchereMinimum) {
		super();
		this.id = id;
		this.description = description;
		this.prixDepart = prixDepart;
		this.prixActuel = prixActuel;
		this.enchereMinimum = enchereMinimum;
	}
	@Override
	public String toString() {
		return "Enchere [id=" + id + ", description=" + description + ", prixDepart=" + prixDepart + ", prixActuel="
				+ prixActuel + ", enchereMinimum=" + enchereMinimum + "]";
	}
	
	
}
