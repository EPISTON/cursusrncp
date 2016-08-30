package com.courtalon.jpa_supermapform.beans;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Trajet {
	private int id;
	private String nom;
	private Adresse depart;
	private Adresse arrivee;
	
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
	
	// @AttributeOverrides permet de supllanter/redéfinir
	// les définitions de colonnes en provenance de l'embeddable (ici Adresse)
	// comme par exemple dans notre cas présent de renommer toutes les colonnes
	// de l'adresse pour éviter les doublons entre adresse de départ et d'arrivé
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="rue", column=@Column(name="DEPART_RUE")),
		@AttributeOverride(name="codePostal", column=@Column(name="DEPART_CP")),
		@AttributeOverride(name="ville", column=@Column(name="DEPART_VILLE")),
		@AttributeOverride(name="pays", column=@Column(name="DEPART_PAYS"))
	})
	public Adresse getDepart() {
		return depart;
	}
	public void setDepart(Adresse depart) {
		this.depart = depart;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="rue", column=@Column(name="ARRIVE_RUE")),
		@AttributeOverride(name="codePostal", column=@Column(name="ARRIVE_CP")),
		@AttributeOverride(name="ville", column=@Column(name="ARRIVE_VILLE")),
		@AttributeOverride(name="pays", column=@Column(name="ARRIVE_PAYS"))
	})
	public Adresse getArrivee() {
		return arrivee;
	}
	public void setArrivee(Adresse arrivee) {
		this.arrivee = arrivee;
	}
	
	
	public Trajet() { this(0, "", new Adresse(), new Adresse());}
	public Trajet(int id, String nom, Adresse depart, Adresse arrivee) {
		super();
		this.id = id;
		this.nom = nom;
		this.depart = depart;
		this.arrivee = arrivee;
	}
	@Override
	public String toString() {
		return "Trajet [id=" + id + ", nom=" + nom + ", depart=" + depart + ", arrivee=" + arrivee + "]";
	}
	
	

}
