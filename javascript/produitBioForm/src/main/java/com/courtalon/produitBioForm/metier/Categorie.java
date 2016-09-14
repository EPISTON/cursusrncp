package com.courtalon.produitBioForm.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {
	private int id;
	private String libelle;
	private Set<Produit> produits;
	
	@OneToMany(mappedBy="categorie")
	public Set<Produit> getProduits() {
		if (produits == null)
			produits = new HashSet<>();
		return produits;
	}
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length=50)
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Categorie() { this(0, "");}
	public Categorie(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	
	
}
