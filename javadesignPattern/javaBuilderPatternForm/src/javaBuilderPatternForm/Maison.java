package javaBuilderPatternForm;

import java.util.Date;

public class Maison {
	private double hauteur;
	private double largeur;
	private int nombrePiece;
	private int nbEtage;
	private String adresse; // requis
	private boolean hasGarage;
	private boolean hasCheminee;
	private Date dateConstruction; // requis
	private double prix;
	
	public double getHauteur() {
		return hauteur;
	}
	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}
	public double getLargeur() {
		return largeur;
	}
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}
	public int getNombrePiece() {
		return nombrePiece;
	}
	public void setNombrePiece(int nombrePiece) {
		this.nombrePiece = nombrePiece;
	}
	public int getNbEtage() {
		return nbEtage;
	}
	public void setNbEtage(int nbEtage) {
		this.nbEtage = nbEtage;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public boolean isHasGarage() {
		return hasGarage;
	}
	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}
	public boolean isHasCheminee() {
		return hasCheminee;
	}
	public void setHasCheminee(boolean hasCheminee) {
		this.hasCheminee = hasCheminee;
	}
	public Date getDateConstruction() {
		return dateConstruction;
	}
	public void setDateConstruction(Date dateConstruction) {
		this.dateConstruction = dateConstruction;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public Maison(double hauteur, double largeur, int nombrePiece, int nbEtage, String adresse, boolean hasGarage,
			boolean hasCheminee, Date dateConstruction, double prix) {
		super();
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.nombrePiece = nombrePiece;
		this.nbEtage = nbEtage;
		this.adresse = adresse;
		this.hasGarage = hasGarage;
		this.hasCheminee = hasCheminee;
		this.dateConstruction = dateConstruction;
		this.prix = prix;
	}
	
	@Override
	public String toString() {
		return "Maison [hauteur=" + hauteur + ", largeur=" + largeur + ", nombrePiece=" + nombrePiece + ", nbEtage="
				+ nbEtage + ", adresse=" + adresse + ", hasGarage=" + hasGarage + ", hasCheminee=" + hasCheminee
				+ ", dateConstruction=" + dateConstruction + ", prix=" + prix + "]";
	}
	
	// Builder est une classe interne de Maison
	public static class Builder {
		private double hauteur;
		private double largeur;
		private int nombrePiece;
		private int nbEtage;
		private String adresse; // requis
		private boolean hasGarage;
		private boolean hasCheminee;
		private Date dateConstruction; // requis
		private double prix;
		
		public Builder(String adresse, Date dateConstruction) {
			this.adresse = adresse;
			this.dateConstruction = dateConstruction;
		}
		
		public Builder hauteur(double hauteur) {
			this.hauteur = hauteur;
			return this;
		}
		public Builder largeur(double largeur) {
			this.largeur = largeur;
			return this;
		}
		public Builder nombrePiece(int nombrePiece) {
			this.nombrePiece = nombrePiece;
			return this;
		}
		public Builder nbEtage(int nbEtage) {
			this.nbEtage = nbEtage;
			return this;
		}
		public Builder addCheminee() {
			this.hasCheminee = true;
			return this;
		}
		public Builder addGarage() {
			this.hasGarage = true;
			return this;
		}
		
		public Builder prix(double prix) {
			this.prix = prix;
			return this;
		}
		public Maison build() {
			if (adresse == null || dateConstruction == null)
				throw new RuntimeException(
						"maison doit avoir une dateConstruction et une adresse!");
			return new Maison(hauteur,
							 largeur,
							 nombrePiece,
							 nbEtage,
							 adresse,
							 hasGarage,
							 hasCheminee,
							 dateConstruction,
							 prix);
		}
		
	}
	
	
}
