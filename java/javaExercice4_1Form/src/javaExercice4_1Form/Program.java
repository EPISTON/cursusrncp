package javaExercice4_1Form;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import metier.Ticket;
import metier.TicketDeveloppement;
import metier.TicketPanne;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		Ticket[] tickets = new Ticket[10];
		Random rd = new Random();
		
		for (int i = 0; i < tickets.length; i++) {
			if (rd.nextBoolean()) {
				tickets[i] = new TicketPanne(i + 1,
						new Date(rd.nextInt(10) + 105, 2, 10),
						"description" +i,
						rd.nextInt(5),
						"MAT000" + i,
						"site no" + i);
			}
			else {
				tickets[i] = new TicketDeveloppement(i + 1,
						new Date(rd.nextInt(10) + 105, 2, 10),
						"bug report " +i,
						rd.nextInt(5),
						"supercloudmanager"+ i,
						rd.nextInt(5));
			}
		}
		
		
		for (Ticket t : tickets)
			System.out.println(t);
		
		// cet objet perment d'ecrire dans un fichier texte
		PrintWriter pw = new PrintWriter("tickets.csv");
		
		// pour chaque ticket
		for (Ticket t : tickets) {
			// j'ecris une ligne dans le fichier avec ses champs au format csv
			pw.println(t.saveToCsv());
		}
		// j'ai fini, je ferme le fichier en ecriture
		pw.close();
		
	}

}
