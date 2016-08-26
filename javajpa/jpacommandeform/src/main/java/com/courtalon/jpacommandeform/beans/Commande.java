package com.courtalon.jpacommandeform.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="CMDS")
public class Commande {
	private String noCommande;
	private Date dateCommande;
	private double prixUnitaire;
	private int quantite;
	private double prixtotal;
	
	// cette annotation indique a JPA d'appeler cette méthode
	// après avoir chargé une instance de commande depuis la base
	@PostLoad
	public void calculatePrixTotal() {
		System.out.println("appel de post load sur " + getNoCommande());
		setPrixtotal(getPrixUnitaire() * getQuantite()); 
	}
	
	
	@Id
	@Column(length=20)
	public String getNoCommande() {return noCommande;}
	public void setNoCommande(String noCommande) {this.noCommande = noCommande;}
	
	//ici, on indique a jpa que la colonne n'a pas besoin de stocker
	// heure/minuste/seconde, uniquement la date
	@Temporal(TemporalType.DATE) 
	public Date getDateCommande() {return dateCommande;}
	public void setDateCommande(Date dateCommande) {this.dateCommande = dateCommande;}
	
	public double getPrixUnitaire() {return prixUnitaire;}
	public void setPrixUnitaire(double prixUnitaire) {this.prixUnitaire = prixUnitaire;}
	public int getQuantite() {return quantite;}
	public void setQuantite(int quantite) {this.quantite = quantite;}
	
	// l'annotation transient indique a jpa d'ignorer cette colonne
	@Transient
	public double getPrixtotal() {return prixtotal;}
	public void setPrixtotal(double prixtotal) {this.prixtotal = prixtotal;}
	
	public Commande() {this("CMD0000", null, 0.0, 0); }
	public Commande(String noCommande, Date dateCommande, double prixUnitaire, int quantite) {
		super();
		this.noCommande = noCommande;
		this.dateCommande = dateCommande;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.prixtotal = quantite * prixUnitaire;
	}
	@Override
	public String toString() {
		return "Commande [noCommande=" + noCommande + ", dateCommande=" + dateCommande + ", prixUnitaire="
				+ prixUnitaire + ", quantite=" + quantite + ", prixtotal=" + prixtotal + "]";
	}
	
	

}
