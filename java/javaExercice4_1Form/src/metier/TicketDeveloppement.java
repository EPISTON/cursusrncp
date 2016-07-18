package metier;

import java.util.Date;

public class TicketDeveloppement extends Ticket {

	private String nomLogiciel;
	private int version;
	public String getNomLogiciel() {
		return nomLogiciel;
	}
	public void setNomLogiciel(String nomLogiciel) {
		this.nomLogiciel = nomLogiciel;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public TicketDeveloppement(int identifiant, Date dateTicket, String description, int urgence, String nomLogiciel,
			int version) {
		super(identifiant, dateTicket, description, urgence);
		this.nomLogiciel = nomLogiciel;
		this.version = version;
	}
	@Override
	public String toString() {
		return "TicketDeveloppement [" 
				+ super.toString() 
				+ "nomLogiciel=" + nomLogiciel + ", version=" + version + "]";
	}
	@Override
	public String saveToCsv() {
		return super.saveToCsv() + ";" + getNomLogiciel() + ";" + getVersion();
	}
	
	
	
}
