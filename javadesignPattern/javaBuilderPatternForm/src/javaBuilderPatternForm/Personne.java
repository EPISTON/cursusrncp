package javaBuilderPatternForm;

import java.util.Date;

public class Personne {
	private String nom;		// required
	private String prenom;	// required
	private int age;		// optional
	private String email;		// optional
	private String telephone;	// optional
	private String adresse;		// optional
	private Date dateNaissance;	// optional
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getTelephone() {return telephone;}
	public void setTelephone(String telephone) {this.telephone = telephone;}
	public String getAdresse() {return adresse;}
	public void setAdresse(String adresse) {this.adresse = adresse;}
	public Date getDateNaissance() {return dateNaissance;}
	public void setDateNaissance(Date dateNaissance) {this.dateNaissance = dateNaissance;}
	
	public Personne(String nom, String prenom) { this(nom, prenom, 0, "", "", "", null);}
	public Personne(String nom, String prenom, int age, String email, String telephone, String adresse,
			Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
	}
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", email=" + email + ", telephone="
				+ telephone + ", adresse=" + adresse + ", dateNaissance=" + dateNaissance + "]";
	}
	
	
}
