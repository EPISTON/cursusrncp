package com.courtalon.jpa_associationmanytomanyform.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;

@Entity
public class Auteur {
	private int id;
	private String nom;
	private String email;
	private String langue;
	
	private Set<Livre> livres;

	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getLangue() {return langue;}
	public void setLangue(String langue) {this.langue = langue;}

	// fonction "cosmetique", elle rapelle son symmetrique chez Livre
	// pour faire l'association
	public void addLivre(Livre l) {
		if (l == null)
			return;
		l.addAuteur(this);
	}
	
	// cette fonction desassocie l'auteur de tous ses livres
	// nécéssaire pour eviter des erreur contraintes dans la base
	@PreRemove // appeler cette méthode automatiquement avant remove
	public void clearLivre() {
		for (Livre l : getLivres()) {
			l.getAuteurs().remove(this);
		}
		getLivres().clear();
	}
	
	// attention, la cascade remove ici SUPPRIMERAIS les livres associés a l'auteur
	// et pas seulement l'association
	@ManyToMany(mappedBy="auteurs"/*, cascade=CascadeType.REMOVE*/)
	public Set<Livre> getLivres() {
		if (livres == null)
			livres = new HashSet<>();
		return livres;
	}
	public void setLivres(Set<Livre> livres) {this.livres = livres;}
	
	public Auteur() { this(0, "", "", "");}
	public Auteur(int id, String nom, String email, String langue) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.langue = langue;
	}
	
	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", email=" + email + ", langue=" + langue + "]";
	}
	
	
	

}
