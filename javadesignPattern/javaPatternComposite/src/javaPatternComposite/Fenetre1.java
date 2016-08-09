package javaPatternComposite;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre1 extends JFrame {
	
	public Fenetre1() {
		super("composite!");
		
		JPanel panel1 = new JPanel();
		add(panel1);
		JButton bouton1 = new JButton("hoho");
		panel1.add(bouton1);
		
		
		
	}

}
