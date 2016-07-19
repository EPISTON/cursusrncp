package metier;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Ticket implements Comparable<Ticket>
{
	public static final int IDENTIFIANT_DEFAUT = 0;
	public static final String DESCRIPTION_DEFAUT = "aucune";
	public static final  int URGENCE_DEFAUT = 1;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	
	private int identifiant;
	private Date dateTicket;
	private String description;
	private int urgence;
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public Date getDateTicket() {
		return dateTicket;
	}
	public void setDateTicket(Date dateTicket) {
		this.dateTicket = dateTicket;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUrgence() {
		return urgence;
	}
	public void setUrgence(int urgence) {
		this.urgence = urgence;
	}
	
	public Ticket(int identifiant, Date dateTicket, String description, int urgence) {
		setIdentifiant(identifiant);
		setDateTicket(dateTicket);
		setUrgence(urgence);
		setDescription(description);
	}
	
	@Override
	public String toString() {
		return "Ticket [identifiant=" + identifiant 
				+ ", dateTicket=" + format.format(dateTicket) 
				+ ", description=" + description
				+ ", urgence=" + urgence + "]";
	}
	
	public String saveToCsv() {
		return getIdentifiant() + ";" 
			+ format.format(getDateTicket()) + ";" 
			+ getDescription() + ";" + getUrgence();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identifiant;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (identifiant != other.identifiant)
			return false;
		return true;
	}
	
	// cette méthode est définie dans l'interface Comparable
	// elle doit renvoyer le résdultat de la comparaison de notre ticket avec un autre
	// -1 si inferieure
	// +1 si supérieure
	// 0  si egale
	@Override
	public int compareTo(Ticket t) {
		if (getUrgence() < t.getUrgence())
			return -1;
		else if (getUrgence() > t.getUrgence())
			return 1;
		// l'urgence est la même, comparer les dates
		return getDateTicket().compareTo(t.getDateTicket());
	}
	
	

}
