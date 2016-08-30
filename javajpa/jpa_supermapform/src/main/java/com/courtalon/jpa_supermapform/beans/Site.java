package com.courtalon.jpa_supermapform.beans;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Site {
	private int id;
	private String nom;
	private Adresse adresse;
	private Localisation loc;
	
	// indique a JPA d'utiliser ce convertisseur avant de persister en base
	@Convert(converter=LocalisationConverter.class)
	public Localisation getLoc() {return loc;}
	public void setLoc(Localisation loc) {this.loc = loc;}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	@Embedded
	public Adresse getAdresse() {return adresse;}
	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	
	public Site() { this(0, "", new Adresse(), new Localisation());}
	public Site(int id, String nom, Adresse adresse, Localisation loc) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.loc = loc;
	}
	@Override
	public String toString() {
		return "Site [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", loc=" + loc + "]";
	}
	
	
	

}
