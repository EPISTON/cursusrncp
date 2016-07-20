package javaExercice5_1Form;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import metier.Produit;

public class FenetreListe extends JFrame implements ActionListener
{
	public static final String EDIT_PRODUIT = "edition";
	public static final String TRI_PRODUIT = "trier";
	public static final String SAVE_PRODUIT = "sauver";
	
	public static final String TRI_PRIX = "tri par prix";
	public static final String TRI_POIDS = "tri par poids";
	public static final String TRI_NOM = "tri par nom";
	
	private JPanel panelHaut;
	
	// la liste des produits
	private DefaultListModel<Produit> data;
	private JList<Produit> liste;

	private JButton afficherSaisie;
	
	private FenetreSaisie fenetreEdition;
	
	private JComboBox<String> choixTri;
	private JButton sauver;
	
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
		
		choixTri = new JComboBox<>(new String[] {TRI_PRIX, TRI_POIDS, TRI_NOM});
		choixTri.setSelectedIndex(0);
		panelHaut.add(choixTri);
		choixTri.setActionCommand(TRI_PRODUIT);
		choixTri.addActionListener(this);
		
		sauver = new JButton("sauvegarder");
		sauver.setActionCommand(SAVE_PRODUIT);
		sauver.addActionListener(this);
		panelHaut.add(sauver);
	}
	
	private void trier_liste() {
		// j'ai ma collection de produit a trier
		List<Produit> produits = new ArrayList<Produit>();
		// je copie le contenu de mon defaultlistModel
		// dans la liste a trier
		for (int i = 0; i < data.size(); i++)
			produits.add(data.getElementAt(i));
		
		// je récupere le choix de tri actuel dans la combobox
		switch (choixTri.getItemAt(choixTri.getSelectedIndex())) 
		{
			case TRI_PRIX:
				// je trie ma liste
				Collections.sort(produits);
				break;
		}
		
		
		//je recopie ma liste dans le defaultListModel
		data.clear();
		for (Produit p : produits)
			data.addElement(p);
		
	}

	// la methode de gestion de nos evenements
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case EDIT_PRODUIT:
				// quand on clique sur editer, afficher la fenetre d'edition
				fenetreEdition.setVisible(true);
				break;
			case TRI_PRODUIT:
				trier_liste();
				break;
			case SAVE_PRODUIT:
			try {
				// j'ouvre le fichier en ecriture
				PrintWriter pw = new PrintWriter("produits.csv");
				// j'ecris pour chaque produit une ligne csv dans le fichier
				for (int i = 0; i < data.size(); i++)
					pw.println(data.getElementAt(i).saveToCsv());
				// je ferme le fichier
				pw.close();
				JOptionPane.showMessageDialog(this, "sauvegarde effectuée!");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
				break;
		}
		
	}
	// methode permettant a la fenetre de saisie
	// de me demander d'ajouter le produit qu'elle vient de creer
	// dans ma liste
	public void ajouterProduit( Produit p) {
		data.addElement(p);
		trier_liste();
	}
	
}