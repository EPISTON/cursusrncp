package javaObjetForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {
	private static final String NOM_DEFAUT = "doe";
	
	private final int id; // ici, l'attribut id est final
	// cela signifie que sa valeur DOIT etre initialisée
	// dans le constructeur, et qu'une fois initialisée, sa
	// valeur ne changera plus
	private String nom;
	private String prenom;
	private String email;
	private double soldeCompte;
	
	// ce bloc de code est le "constructeur de classe"
	// il est appelé une seule fois au chargement initial de la classe
	static {
		System.out.println("appel constructeur de classe Client");
		File f = new File("id.txt");
		try {
			Scanner reader = new Scanner(f);
			compteurId = Integer.parseInt(reader.nextLine());
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static int compteurId;
	public static int getCompteurId() {
		return compteurId;
	}

	// accesseurs
	public int getId() {
		return id;
	}
	
	// getter de soldeCompte
	public double getSoldeCompte() {
		return soldeCompte;
	}
	// setter de soldeCompte
	public void setSoldeCompte(double soldeCompte) {
		this.soldeCompte = (soldeCompte >= 0 )? soldeCompte : 0.0;
	}
		
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	// constructeurs
	
	public Client() {
		this(NOM_DEFAUT, "john", "john@doe.com", 100.0); // je rapelle l'autre constructeur
		
		/*this.nom = "doe";
		this.prenom = "john";
		this.email = "john@doe.com";
		this.soldeCompte = 100.0;*/
	}
	
	public Client(String nom, String prenom, String email, double solde) {
		compteurId++;
		this.id = compteurId;
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setSoldeCompte(solde);
	}
	
	// methodes
	
	public void afficher() {
		// this -> permet d'accéder a l'instance sur laquelle la méthode est appelée
		// s'il n'y a pas d'ambiguité, this est facultatif pour accéder
		// a une méthode ou un attribut de notre instance
		System.out.println("client(" + this.id + ": " + nom + ", " + this.prenom + " : " + soldeCompte);
	}
	
}
