package com.courtalon.jpaboutiqueform.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produit {
	private int id;
	private String nom;
	private double prix;
	private double poids;
	private int stock;
	private Categorie categorie;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@ManyToOne
	@JoinColumn(name="CAT_ID") // permet de personnaliser la cle étrangère
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Produit() { this(0, "", 0, 0, 0);}
	public Produit(int id, String nom, double prix, double poids, int stock) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poids = poids;
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poids=" + poids + ", stock=" + stock + "]";
	}
}
