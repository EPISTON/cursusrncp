package humanRessource;

public class Employe extends Personne {
	public static final String POSTE_DEFAUT = "stromtrooper";
	public static final double SALAIRE_DEFAUT = 5000;
	
	private String poste;
	private double salaire;
	
	public String getPoste() {return poste;}
	public void setPoste(String poste) {this.poste = poste;}
	public double getSalaire() {return salaire;}
	public void setSalaire(double salaire) {this.salaire = salaire;}
	
	
	public Employe() { this(NOM_DEFAUT, PRENOM_DEFAUT, POSTE_DEFAUT, SALAIRE_DEFAUT); }
	public Employe(String nom, String prenom, String poste, double salaire) {
		super(nom, prenom);
		setPoste(poste);
		setSalaire(salaire);
	}
	
	@Override
	public void afficher() {
		System.out.print("Employe : " + getPoste() + ", " + getSalaire() + " => ");
		super.afficher();
		// super permet ici de préciser que l'on souhaite rappeler la méthode
		// afficher de notre parent, et pas la notre
		// on ne peut pas chainer en faisant super.super, autrement dit
		// on ne peut pas court-circuiter une méthode du parent si celui-ci
		// ne l'autorise pas (en fournissant sa propre méthode)
	}
	
	// on a le droit d'augmenter la "visibilité" d'une méthode overridée
	// mais pas de la réduire
	@Override
	public void methode1() {
		// TODO Auto-generated method stub
		super.methode1();
	}
	@Override
	public String toString() {
		return "Employe [poste=" + poste + ", salaire=" + salaire 
				+ ", getNom()=" + getNom() + ", getPrenom()="
				+ getPrenom() + "]";
	}
	@Override
	public void contacter(String message) {
		System.out.println("envoie de " + message + " au poste " + getPoste());
		
	}
	
	
	/*@Override
	public String toString() {
		return "Employe " + getNom();
	}*/
	
	
	
	
	
}
