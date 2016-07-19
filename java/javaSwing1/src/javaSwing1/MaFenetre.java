package javaSwing1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MaFenetre extends JFrame implements ActionListener
{
	public final static String ACTION_SALUTATION = "salutation";
	public final static String ACTION_MESSAGE = "message";
	
	private JButton bouton1;
	private JTextField saisie1;
	private JLabel label1;
	private JCheckBox checkbox1;
	private JButton bouton2;
	
	public MaFenetre() {
		super("ma super fenetre"); // le titre de la fenetre
		// définir la taille de ma fenetre
		setSize(800, 600);
		
		// centrer la fenetre
		setLocationRelativeTo(null);
		
		// terminer le programme quand on ferme la fenetre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		// le flowLayout organise les composants graphiques
		// de gauche a droite et de haut en bas
		setLayout(new FlowLayout());
		
		bouton1 = new JButton("cliquez moi!");
		bouton1.setActionCommand(ACTION_SALUTATION);
		add(bouton1);
		
		// champ de saisie texte mono-ligne
		saisie1 = new JTextField(30);
		add(saisie1);
	
		// un jlabel permet d'afficher du texte
		label1 = new JLabel("un super label");
		add(label1);
		
		// case a cocher
		checkbox1 = new JCheckBox("cochez moi!");
		checkbox1.setSelected(true);
		add(checkbox1);
		
		// ajout 2eme bouton
		bouton2 = new JButton("2eme bouton");
		bouton2.setActionCommand(ACTION_MESSAGE);
		add(bouton2);
		
		// creation d'un menu
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("fichiers");
		menu1.add("ouvrir");
		menu1.add("fermer");
		JMenuItem menusalutation = new JMenuItem("salutation");
		menu1.add(menusalutation);
		// ma fenetre ecoute au click sur ce menuItem
		menusalutation.addActionListener(this);
		// l'actionCommand envoyée sera la meme que pour le bouton1
		menusalutation.setActionCommand(ACTION_SALUTATION);
		menubar.add(menu1);
		
		// ajout du menu dans ma fenetre
		setJMenuBar(menubar);
		
		// mise en place du callback
		bouton1.addActionListener(this);
		bouton2.addActionListener(this);
		
		/*bouton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "hohoho");
			}
		});*/
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("action déclenchée");
		/*JOptionPane.showMessageDialog(this,
				"felicitation, vous etes le 100000 visiteur");*/
		/*if ( e.getSource() == bouton1)
			JOptionPane.showMessageDialog(this, saisie1.getText());
		else
			JOptionPane.showMessageDialog(this, "autre message");
		*/
		
		// e.getActionCommand récupere la commande
		// associée au composant déclenchement l'evenement, ici
		// nos boutons
		
		switch (e.getActionCommand()) {
			case ACTION_SALUTATION:
				JOptionPane.showMessageDialog(this, saisie1.getText());
				break;
			case ACTION_MESSAGE:
				JOptionPane.showMessageDialog(this, "autre message");
				break;
		}
		
	}
	
}
