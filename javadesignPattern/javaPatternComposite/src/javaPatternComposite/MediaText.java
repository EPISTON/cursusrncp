package javaPatternComposite;

public class MediaText extends MediaElement {
	private String texte;
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public MediaText(String texte) {
		super();
		this.texte = texte;
	}

	public void afficher() {
		System.out.println(getTexte());
		super.afficher();
	}
	
	

}
