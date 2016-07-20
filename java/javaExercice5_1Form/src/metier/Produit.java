package metier;

public class Produit {
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

}
