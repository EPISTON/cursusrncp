package humanRessource;

// une classe abstract ne peut etre instanciée
public abstract class Personne {
	public static final String NOM_DEFAUT = "doe";
	public static final String PRENOM_DEFAUT = "john";
	
	private String nom;
	private String prenom;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	
	// cette méthode est abstraite, les descendants
	// devront fournir le code correspondant pour etre concret
	public abstract void contacter(String message);
	
	
	public Personne() { this(NOM_DEFAUT, PRENOM_DEFAUT);}
	public Personne(String nom, String prenom) {
		setNom(nom);
		setPrenom(prenom);
	}
	
	public void afficher() {
		System.out.println("Personne : " + getNom() + ", " + getPrenom());
	}
	
	protected void methode1() {
		System.out.println("methode1 de Personne");
	}
	
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) // si c'est la même instance, alors c'est egale
			return true;
		if (obj == null) // si l'autre objet est null, ce n'est pas egale
			return false;
		if (getClass() != obj.getClass()) // si l'on a pas la meme classe, ce n'est pas egale
			return false;
		Personne other = (Personne) obj;
		// comparaison de nom
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		// puis, si les noms sont egaux, de prenom
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		// si nom et prenom ne sont pas différents, nos 2 personnes sont egales
		return true;
	}
	
	
	
}
