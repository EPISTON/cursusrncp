package javaExercice5_1Form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.print.CancelablePrintJob;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import metier.Produit;

public class FenetreSaisie extends JFrame implements ActionListener, DocumentListener
{

	private FenetreListe parent;
	
	private JLabel labelNom;
	private JLabel labelPrix;
	private JLabel labelPoids;
	private JTextField champsNom;
	private JTextField champsPrix;
	private JTextField champsPoids;
	
	private JButton saveButton;
	
	private Pattern nomMotif;
	
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
		champsNom.getDocument().addDocumentListener(this);
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
		champsPrix.getDocument().addDocumentListener(this);
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
		champsPoids.getDocument().addDocumentListener(this);
		panelLigne.add(champsPoids);
		
		// ajout de la troisieme ligne 
		add(panelLigne);
		
		saveButton = new JButton("sauver produit");
		add(saveButton);
		saveButton.addActionListener(this);
		
		// desactive la possibilité de redimensionner la fenetre pour l'utilisateur
		setResizable(false);
		
		nomMotif = Pattern.compile("^[-' _a-z0-9]{2,40}$", Pattern.CASE_INSENSITIVE);
		
		validateField();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Produit p = new Produit(champsNom.getText(),
									Double.parseDouble(champsPrix.getText()),
									Double.parseDouble(champsPoids.getText()));
			parent.ajouterProduit(p);
			// je cache ma fenetre, saisie terminée
			this.setVisible(false);
		}
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,
										"saisissez bien des nombres",
										"saisie incorrecte",
										JOptionPane.WARNING_MESSAGE);
		}
		
	}


	private void validateField() {
		//System.out.println("validation!");
		boolean isValid = true;
		// si le motif ne match pas le nom fournis
		if (!nomMotif.matcher(champsNom.getText()).matches()) {
			// nom n'est pas valide
			champsNom.setBackground(Color.PINK);
			isValid = false;
		}
		else {
			champsNom.setBackground(new Color(50,200, 50));
		}
		
		try {
			Double.parseDouble(champsPrix.getText()); // verifier si la conversion marche
			champsPrix.setBackground(new Color(50, 200, 50));
		}
		catch (NumberFormatException ex) {
			isValid = false;
			champsPrix.setBackground(Color.PINK);
		}
		try {
			Double.parseDouble(champsPoids.getText()); // verifier si la conversion marche
			champsPoids.setBackground(new Color(50, 200, 50));
		}
		catch (NumberFormatException ex) {
			isValid = false;
			champsPoids.setBackground(Color.PINK);
		}
		saveButton.setEnabled(isValid);
		
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {validateField();}
	@Override
	public void insertUpdate(DocumentEvent e) { validateField();}
	@Override
	public void removeUpdate(DocumentEvent e) { validateField();}
	
}
