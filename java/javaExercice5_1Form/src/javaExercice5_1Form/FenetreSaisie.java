package javaExercice5_1Form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.CancelablePrintJob;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.Produit;

public class FenetreSaisie extends JFrame implements ActionListener
{

	private FenetreListe parent;
	
	private JLabel labelNom;
	private JLabel labelPrix;
	private JLabel labelPoids;
	private JTextField champsNom;
	private JTextField champsPrix;
	private JTextField champsPoids;
	
	private JButton saveButton;
	
	
	public FenetreSaisie(FenetreListe parent) {
		super("saisie produit");
		this.parent = parent;
		setSize(350, 130);
		setLocationRelativeTo(parent);
		// si on ferme cette fenetre, elle est juste cachée
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		// je découpe ma fenetre verticalement
		BoxLayout bl = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(bl);
		
		// un ligne de saisie
		JPanel panelLigne = new JPanel();
		BoxLayout bl2 = new BoxLayout(panelLigne, BoxLayout.X_AXIS);
		panelLigne.setLayout(bl2);
		
		labelNom = new JLabel("nom produit");
		panelLigne.add(labelNom);
		champsNom = new JTextField(30);
		panelLigne.add(champsNom);
		
		// ajout de la première ligne 
		add(panelLigne);
		
		// deuxieme ligne
		
		panelLigne = new JPanel();
		bl2 = new BoxLayout(panelLigne, BoxLayout.X_AXIS);
		panelLigne.setLayout(bl2);
		
		labelPrix = new JLabel("prix produit");
		panelLigne.add(labelPrix);
		champsPrix = new JTextField(30);
		panelLigne.add(champsPrix);
		
		// ajout de la deuxieme ligne 
		add(panelLigne);
		
		// troisieme ligne
		
		panelLigne = new JPanel();
		bl2 = new BoxLayout(panelLigne, BoxLayout.X_AXIS);
		panelLigne.setLayout(bl2);
		
		labelPoids = new JLabel("poids produit");
		panelLigne.add(labelPoids);
		champsPoids = new JTextField(30);
		panelLigne.add(champsPoids);
		
		// ajout de la troisieme ligne 
		add(panelLigne);
		
		saveButton = new JButton("sauver produit");
		add(saveButton);
		saveButton.addActionListener(this);
		
		// desactive la possibilité de redimensionner la fenetre pour l'utilisateur
		setResizable(false);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Produit p = new Produit(champsNom.getText(),
								Double.parseDouble(champsPrix.getText()),
								Double.parseDouble(champsPoids.getText()));
		parent.ajouterProduit(p);
		// je cache ma fenetre, saisie terminée
		this.setVisible(false);
	}
	
}
