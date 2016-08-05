package bloggingForm.metier;

public class Post {
	private int id;
	private String titre;
	private String corps;
	private String categorie;
	private String auteur;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	public String getCategorie() {return categorie;}
	public void setCategorie(String categorie) {this.categorie = categorie;}
	public String getAuteur() {return auteur;}
	public void setAuteur(String auteur) {this.auteur = auteur;}
	
	public Post() { this(0, "", "", "", "");}
	public Post(int id, String titre, String corps, String categorie, String auteur) {
		super();
		this.id = id;
		this.titre = titre;
		this.corps = corps;
		this.categorie = categorie;
		this.auteur = auteur;
	}
	
}
