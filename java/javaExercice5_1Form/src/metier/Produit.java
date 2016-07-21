package metier;

import java.util.Comparator;

public class Produit implements Comparable<Produit>
{
	private String nom;
	private double prix;
	private double poids;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public double getPoids() {return poids;}
	public void setPoids(double poids) {this.poids = poids;}
	
	public Produit() { this("", 0.0, 01.0); }
	public Produit(String nom, double prix, double poids) {
		setNom(nom);
		setPrix(prix);
		setPoids(poids);
	}
	
	@Override
	public String toString() {
		return "Produit [nom=" + nom + ", prix=" + prix + ", poids=" + poids + "]";
	}
	@Override
	public int compareTo(Produit o) {
		if (getPrix() < o.getPrix())
			return -1;
		if (getPrix() > o.getPrix())
			return 1;
		return 0;
	}
	
	public String saveToCsv() {
		return getNom() + ";" + getPrix() + ";" + getPoids();
	}
	
	public static Produit loadFromCsv(String line) throws RuntimeException
	{
		// découpage de la ligne par les ";"
		// "chaise;45.5;2.2" -> split(";") -> ["chaise", "45.5", "2.2"]
		String[] champs = line.split(";");
		if (champs.length != 3) {
			throw new RuntimeException("format csv produit invalide");
		}
		return new Produit(champs[0],
							Double.parseDouble(champs[1]),
							Double.parseDouble(champs[2]));
	}

	
	// déclaration d'une classe interne statique
	// celle-ci aura accès au attributs et méethodes statique de la classe
	// externe, ici Produit
	public static class PoidsComparator implements Comparator<Produit>{
		@Override
		public int compare(Produit o1, Produit o2) {
			if (o1.getPoids() < o2.getPoids())
				return -1;
			if (o1.getPoids() > o2.getPoids())
				return 1;
			return 0;
		}
	}
	public static class NomComparator implements Comparator<Produit>{
		@Override
		public int compare(Produit o1, Produit o2) {
			return o1.getNom().compareTo(o2.getNom());
		}
	}
}
