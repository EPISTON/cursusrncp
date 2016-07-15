package javaObjetForm;

public class Program {

	public static void main(String[] args) {
		
		System.out.println(Client.getCompteurId());
		
		Client c1; // déclaration de ma variable Client c1
		c1 = new Client(); // instanciation du client
		//c1.afficher();
		c1.setNom("schwarzenegger");
		c1.setPrenom("arnold");
		c1.setEmail("governator@california.gov");
		c1.setSoldeCompte(45000);
		
		System.out.println(c1.getNom());
		
		c1.afficher();
		
		Client c2 = new Client("Dujardin", "jean", "jean@surfeur.com", 35000); // l'instanciation du client passe par le constructeur
		c2.afficher();
		c2.setSoldeCompte(-500);
		c2.afficher();
		
	}

}
