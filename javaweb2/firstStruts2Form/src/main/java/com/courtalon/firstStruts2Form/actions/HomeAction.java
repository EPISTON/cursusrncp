package com.courtalon.firstStruts2Form.actions;

import java.util.Arrays;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {

	// les données de mon formulaire
	private String nom;
	private String prenom;
	private int age;
	
	// le message a transmettre a la page jsp
	private String message;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	public String getMessage() {return message;}
	
	// une classe d'action DOIT avoit un constructeur sans parametres
	public HomeAction() {
	}
	
	// afficher le formulaire
	public String index() {
		// le contenu par défaut du formulaire
		setNom("doe");
		setPrenom("john");
		setAge(25);
		return SUCCESS;
	}
	
	// receptionner le formulaire
	public String save() {
		// liste de noms interdits
		String[] forbiddenNames = {"hitler", "bieber", "hilton", "kylo"};
		boolean found = false;
		for (String name : forbiddenNames) {
			if (name.equals(getNom().toLowerCase())) {
				found = true;
				break;
			}
		}
		// si le nom correspond a un des noms interdit
		if (found) {
			// ajouter l'erreur sur le champ nom
			addFieldError("nom", "nom interdit");
			// et renvoyer le resultat "input"
			return INPUT;
		}
		if (getNom().equals("jobs") && getPrenom().equals("steve")) {
			addActionError("nous n'acceptons pas les gens non vivant apple");
			return INPUT;
		}
		
		
		this.message = "bonjour, " 
						+ getNom() + " " 
						+ getPrenom() + ", tu as " 
						+ getAge() + " ans";
		return SUCCESS;
	}
	
	
	
	
	
}
