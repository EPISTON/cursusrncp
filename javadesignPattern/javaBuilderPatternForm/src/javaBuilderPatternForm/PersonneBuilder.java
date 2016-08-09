package javaBuilderPatternForm;

import java.util.Date;

public class PersonneBuilder {
	private Personne personne;
	
	public PersonneBuilder(String nom, String prenom) {
		personne = new Personne(nom, prenom);
	}
	
	public PersonneBuilder age(int age) {
		personne.setAge(age);
		return this;
	}
	public PersonneBuilder email(String email) {
		personne.setEmail(email);
		return this;
	}
	public PersonneBuilder telephone(String telephone) {
		personne.setTelephone(telephone);
		return this;
	}
	public PersonneBuilder adresse(String adresse) {
		personne.setAdresse(adresse);
		return this;
	}
	public PersonneBuilder dateNaissance(Date dateNaissance) {
		personne.setDateNaissance(dateNaissance);
		return this;
	}
	
	public Personne build() {
		return personne;
	}
	
}
