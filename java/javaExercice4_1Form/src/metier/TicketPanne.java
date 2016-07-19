package metier;

import java.util.Date;

// final devant une classe interdit tout héritage de celle-ci
public final class TicketPanne extends Ticket {

	private String noMateriel;
	private String localisation;
	
	public String getNoMateriel() {
		return noMateriel;
	}
	public void setNoMateriel(String noMateriel) {
		this.noMateriel = noMateriel;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
	public TicketPanne(int identifiant, Date dateTicket, String description, int urgence, String noMateriel,
			String localisation) {
		super(identifiant, dateTicket, description, urgence);
		this.noMateriel = noMateriel;
		this.localisation = localisation;
	}
	@Override
	public String toString() {
		return "TicketPanne [" +  
				super.toString()
				+"] noMateriel=" + noMateriel 
				+ ", localisation=" + localisation + "]";
	}
	
	@Override
	public String saveToCsv() {
		return super.saveToCsv() + ";" + getNoMateriel() + ";" + getLocalisation();
	}
	
	
	
}
