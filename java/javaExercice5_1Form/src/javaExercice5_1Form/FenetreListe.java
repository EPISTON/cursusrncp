package javaExercice5_1Form;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
	public static final String LOAD_PRODUIT = "charger";
	
	public static final String TRI_PRIX = "tri par prix";
	public static final String TRI_POIDS = "tri par poids";
	public static final String TRI_NOM = "tri par nom";
	public static final String TRI_POIDS_PRIX = "tri par prix au kg";
	
	private JPanel panelHaut;
	
	// la liste des produits
	private DefaultListModel<Produit> data;
	private JList<Produit> liste;

	private JButton afficherSaisie;
	
	private FenetreSaisie fenetreEdition;
	
	private JComboBox<String> choixTri;
	private JButton sauver;
	private JButton charger;
	
	
	private Comparator<Produit> produitPoidsComparator; // comparer via le poids
	private Comparator<Produit> produitNomComparator; // comparer via le nom
	
	
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
		
		choixTri = new JComboBox<>(new String[] {TRI_PRIX, TRI_POIDS, TRI_NOM, TRI_POIDS_PRIX});
		choixTri.setSelectedIndex(0);
		panelHaut.add(choixTri);
		choixTri.setActionCommand(TRI_PRODUIT);
		choixTri.addActionListener(this);
		
		// bouton de sauvegarde
		sauver = new JButton("sauvegarder");
		sauver.setActionCommand(SAVE_PRODUIT);
		sauver.addActionListener(this);
		panelHaut.add(sauver);
		
		// bouton de chargement
		charger = new JButton("charger");
		charger.setActionCommand(LOAD_PRODUIT);
		charger.addActionListener(this);
		panelHaut.add(charger);
		
		// comparateurs pour tri
		produitPoidsComparator = new Produit.PoidsComparator();
		produitNomComparator = new Produit.NomComparator();
		
		// classes anonymes internes
		/*
		produitPoidsComparator = new Comparator<Produit>() {

			@Override
			public int compare(Produit o1, Produit o2) {
				if (o1.getPoids() < o2.getPoids())
					return -1;
				if (o1.getPoids() > o2.getPoids())
					return 1;
				return 0;
			}
		};
		
		produitNomComparator = new Comparator<Produit>() {

			@Override
			public int compare(Produit o1, Produit o2) {
				return o1.getNom().compareTo(o2.getNom());
			}
		};
		*/
		
		
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
			case TRI_POIDS:
				Collections.sort(produits, produitPoidsComparator);
				break;
			case TRI_NOM:
				Collections.sort(produits, produitNomComparator);
				break;
			case TRI_POIDS_PRIX:
				Collections.sort(produits, (p1, p2) -> {
					return new Double(p1.getPrix() / p1.getPoids())
							.compareTo(p2.getPrix() /p2.getPoids());
				});
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
			case LOAD_PRODUIT:
				try {
					// j'ouvre le fichier en lecture
					Scanner reader = new Scanner(new File("produits.csv"));
					// je vide la Jlist
					data.clear();
				
					// tant qu'il reste des lignes a lire dans le fichier
					while (reader.hasNextLine()) {
						// lire la ligne suivante
						String line = reader.nextLine();
						// appel du "chargeur" de la classe Produit avec la ligne
						data.addElement(Produit.loadFromCsv(line));
					}
					reader.close();
				}
				catch (RuntimeException ex) {
					// erreur si le csv est malformé
					JOptionPane.showMessageDialog(this, "format de fichier invalide: "
													+  ex.getMessage());
				}
				catch(FileNotFoundException ex) {
					// erreur a l'ouverture du fichier
					JOptionPane.showMessageDialog(this, "pas de fichier a charger");
				}
				trier_liste(); // je retri la liste chargée
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
