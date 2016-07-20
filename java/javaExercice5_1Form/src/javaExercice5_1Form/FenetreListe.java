package javaExercice5_1Form;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import metier.Produit;

public class FenetreListe extends JFrame implements ActionListener
{
	public static final String EDIT_PRODUIT = "edition";
	
	private JPanel panelHaut;
	
	// la liste des produits
	private DefaultListModel<Produit> data;
	private JList<Produit> liste;

	private JButton afficherSaisie;
	
	private FenetreSaisie fenetreEdition;
	
	
	public FenetreListe() {
		super("product elite manager");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		
		
		// mon panel haut (contenant les boutons)
		// sera découpé horizontalement
		panelHaut = new JPanel();
		BoxLayout bl = new BoxLayout(panelHaut, BoxLayout.X_AXIS);
		panelHaut.setLayout(bl);
		
		afficherSaisie = new JButton("creer produit");
		panelHaut.add(afficherSaisie);
		
		// j'ajoute le panneau contenant les bouton au nord de la fenetre
		add(panelHaut, BorderLayout.NORTH);
		
		// mise en place de liste des produits
		data = new DefaultListModel<>();
		liste = new JList<>(data);
		
		// et placement de celle-ci au centre de ma fenetre
		add(new JScrollPane(liste), BorderLayout.CENTER);
		
		
		fenetreEdition = new FenetreSaisie(this);
		
		afficherSaisie.setActionCommand(EDIT_PRODUIT);
		afficherSaisie.addActionListener(this);
		
	}

	// la methode de gestion de nos evenements
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case EDIT_PRODUIT:
				// quand on clique sur editer, afficher la fenetre d'edition
				fenetreEdition.setVisible(true);
				break;
		}
		
	}
	// methode permettant a la fenetre de saisie
	// de me demander d'ajouter le produit qu'elle vient de creer
	// dans ma liste
	public void ajouterProduit( Produit p) {
		data.addElement(p);
	}
	
}
