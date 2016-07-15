package javaExercice3_1Form;

public class Produit {
	private static final String DEFAULT_NOM = "noname";
	private static final double DEFAULT_PRIX = 0;
	private static final double DEFAULT_POIDS = 0;
	private static final double DISCOUNT_RATE = 5;
	
	private static int compteurProduit = 0;
	
	private final int id;
	private String denomination;
	private double prix;
	private double poids;
	
	public int getId() {return id;}
	public String getDenomination() {return denomination;}
	public void setDenomination(String denomination) {
		if (denomination == null || denomination.length() < 3)
			denomination = DEFAULT_NOM;
		else if (denomination.length() > 50)
			denomination = denomination.substring(0, 50);
		
		this.denomination = denomination;
	}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {
		this.prix = (prix < DEFAULT_PRIX)? DEFAULT_PRIX : prix;
	}
	public double getPoids() {return poids;}
	public void setPoids(double poids) {
		this.poids = (poids < DEFAULT_POIDS)? DEFAULT_POIDS : poids;
	}
	
	public double getPrice(int quantite) {
		if (quantite <= 10)
			return getPrix() * quantite;
		else
			return getPrix() * quantite * (1 -(DISCOUNT_RATE / 100.0));
	}
	
	
	public Produit() { this(DEFAULT_NOM, DEFAULT_PRIX, DEFAULT_POIDS);}
	public Produit(String denomination, double prix, double poids) {
		this.id = ++compteurProduit;
		setDenomination(denomination);
		setPrix(prix);
		setPoids(poids);
	}
	
	public void afficher() {
		System.out.println("Produit [id=" + id 
				+ ", denomination=" + denomination
				+ ", prix=" + prix
				+ ", poids=" + poids + "]");
	}
	
	
	
	

}
