package metier;

import java.util.Date;

public class TicketAgileDev extends TicketDeveloppement
{

	public TicketAgileDev(int identifiant, Date dateTicket, String description, int urgence, String nomLogiciel,
			int version) {
		super(identifiant, dateTicket, description, urgence, nomLogiciel, version);
		
	}
	/*
	public String saveToCsv() {
		return "";
	}
*/
}
