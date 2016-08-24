package com.courtalon.struts2BoutiqueForm.metier;

public class Produit {
	private int id;
	private String nom;
	private double prix;
	private double poids;
	private int stock;
	private int categorieId;
	private Categorie categorie; // cette objet est "optionnellement" chargé
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public double getPoids() {return poids;}
	public void setPoids(double poids) {this.poids = poids;}
	public int getStock() {return stock;}
	public void setStock(int stock) {this.stock = stock;}
	public int getCategorieId() {return categorieId;}
	public void setCategorieId(int categorieId) {this.categorieId = categorieId;}
	public Categorie getCategorie() {return categorie;}
	public void setCategorie(Categorie categorie) {this.categorie = categorie;}
	
	public Produit() { this(0, "", 0, 0, 0, 0);}
	public Produit(int id, String nom, double prix, double poids, int stock, int categorieId) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poids = poids;
		this.stock = stock;
		this.categorieId = categorieId;
	}

	
	
}
