package javaSwing2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MicroOnde extends JFrame {

	private JButton porte;
	private JLabel marque;
	private JPanel panelCommande;
	private JTextField timer;
	private JPanel clavier;
	
	public MicroOnde() {
		super("micro onde");
		
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		porte = new JButton();
		// la porte est placée au centre de la fenetre
		add(porte, BorderLayout.CENTER);
		
		marque = new JLabel("micro onde atomisator 5000");
		add(marque, BorderLayout.NORTH);
		
		panelCommande = new JPanel();
		add(panelCommande, BorderLayout.EAST);
		
		BoxLayout bl = new BoxLayout(panelCommande, BoxLayout.Y_AXIS);
		panelCommande.setLayout(bl);
		
		timer = new JTextField("00:00", 10);
		panelCommande.add(timer);
		
		clavier = new JPanel();
		clavier.setLayout(new GridLayout(3, 3));
		for (int i = 1; i <= 9; i++) {
			clavier.add(new JButton("" + i));
		}
		panelCommande.add(clavier);
		
	}
	
	
	
}
