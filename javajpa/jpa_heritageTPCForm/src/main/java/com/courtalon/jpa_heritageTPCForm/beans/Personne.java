package com.courtalon.jpa_heritageTPCForm.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) 
public class Personne {
	private int id;
	private String nom;
	private String prenom;
	
	@TableGenerator(name="mon_generateur",
					pkColumnName="nom_sequence",
					pkColumnValue="personnes",
					valueColumnName="noBloc",
					table="mes_sequences",
					allocationSize=100)
	@Id @GeneratedValue(strategy=GenerationType.TABLE,generator="mon_generateur")
	//@Id @GeneratedValue(strategy=GenerationType.TABLE)
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {	return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	
	public Personne() {this(0, "", ""); }
	public Personne(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	

}
