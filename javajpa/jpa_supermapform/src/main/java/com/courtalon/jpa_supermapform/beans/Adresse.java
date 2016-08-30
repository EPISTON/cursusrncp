package com.courtalon.jpa_supermapform.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// cet objet es déclaré comme @embeddable, c.a.d
// qu'il peut etre inclus dans une entite
// ses attributs seront alors sauvegardés dans des colonnes
// ajoutées a la table de l'entité ou il est inclus
@Embeddable
public class Adresse {
	private String rue;
	private String codePostal;
	private String ville;
	private String pays;
	
	//@Column(length=50)
	public String getRue() {return rue;}
	public void setRue(String rue) {this.rue = rue;}
	
	public String getCodePostal() {return codePostal;}
	public void setCodePostal(String codePostal) {this.codePostal = codePostal;}
	
	public String getVille() {return ville;}
	public void setVille(String ville) {this.ville = ville;}
	
	public String getPays() {return pays;}
	public void setPays(String pays) {this.pays = pays;}
	
	public Adresse() { this("","","","");}
	public Adresse(String rue, String codePostal, String ville, String pays) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}
	@Override
	public String toString() {
		return "Adresse [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", pays=" + pays + "]";
	}
	
}
