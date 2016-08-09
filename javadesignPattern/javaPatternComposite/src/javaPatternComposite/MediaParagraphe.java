package javaPatternComposite;

public class MediaParagraphe extends MediaElement {
	private String debut;
	private String fin;
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public MediaParagraphe(String debut, String fin) {
		super();
		this.debut = debut;
		this.fin = fin;
	}
	@Override
	public void afficher() {
		System.out.println(debut);
		super.afficher();
		System.out.println(fin);
	}
	
	
	
}
