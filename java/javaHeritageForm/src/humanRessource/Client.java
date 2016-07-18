package humanRessource;

public class Client extends Personne
{
	public static final String EMAIL_DEFAUT = "noemail@ocp.com";
	public static final double SOLDE_DEFAUT = 0.0;
	private String email;
	private double soldeCompte;
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public double getSoldeCompte() {return soldeCompte;}
	public void setSoldeCompte(double soldeCompte) {this.soldeCompte = soldeCompte;}

	public Client() { this(NOM_DEFAUT, PRENOM_DEFAUT, EMAIL_DEFAUT, SOLDE_DEFAUT); }
	public Client(String nom, String prenom, String email, double soldeCompte) {
		super(nom, prenom); // chainage explicite vers le constructeur du parent
		//setNom(nom);
		//setPrenom(prenom);
		setEmail(email);
		setSoldeCompte(soldeCompte);
	}

	// cette annotation indique au compilateur java de vérifier
	// si on remplace bien une méthode existante chez nos ancetres
	@Override
	public void afficher() {
		System.out.println("Client: " + getNom() +", " + getPrenom()
							+ ", " + getEmail() + ", " + getSoldeCompte());
		// this.methode1(); --> j'ai accès a methode1 car "protected"
	}
	@Override
	public String toString() {
		return "Client [email=" + email + ", soldeCompte=" + soldeCompte + ", getNom()=" + getNom() + ", getPrenom()="
				+ getPrenom() + "]";
	}
	@Override
	public void contacter(String message) {
		System.out.println("envoie de " + message + " a " + getEmail());
	}
	
	
	
}
