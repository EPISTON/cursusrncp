package javaCollection;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class FenetreListe extends JFrame {
	
	// un panel auto-scrollant
	private JScrollPane paneauListe;
	// le composant graphique JList
	private JList<String> liste;
	// les données affichées par la JList
	private DefaultListModel<String> data;
	
	
	
	public FenetreListe() {
		super("liste de choix");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		
		// je creer mon tableau de donnée (vide)
		data = new DefaultListModel<>();
		// je creer ma jlist associée au tableau
		liste = new JList<>(data);
		// je place ma jlist dans le scrollPane
		paneauListe = new JScrollPane(liste);
		
		add(paneauListe, BorderLayout.CENTER);
		
		data.addElement("Paris");
		data.addElement("Montpellier");
		data.addElement("Toulouse");
		data.addElement("Lyon");
		data.addElement("Clermont-Ferrand");
		data.addElement("Rennes");
		data.addElement("Rouen");
		data.addElement("Trifouilli les oeuielleres sur Seine afluent nord");
		
		
	}

}
