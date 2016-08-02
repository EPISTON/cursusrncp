package webtodoForm.metier;

public class Tache {
	private String description;
	private String categorie;
	private int priorite;
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public String getCategorie() {return categorie;}
	public void setCategorie(String categorie) {this.categorie = categorie;}
	public int getPriorite() {return priorite;}
	public void setPriorite(int priorite) {this.priorite = priorite;}
	
	public Tache() { this("", "", 0);}
	public Tache(String description, String categorie, int priorite) {
		super();
		setDescription(description);
		setCategorie(categorie);
		setPriorite(priorite);
	}
	

}
