package javaCalculatorForm;

import java.util.Map;

public class ValeurImmediate extends Expression {
	private double valeur;
	
	public double getValeur() {return valeur;}
	public void setValeur(double valeur) {this.valeur = valeur;}

	public ValeurImmediate(double valeur) {
		//super();
		setValeur(valeur);
	}
	
	// je fournis ma propre version de evaluer()
	// ici, pour valeur immediate, l'evaluation renvoie directement sa propre valeur
	@Override
	public double evaluer(Map<String, Double> variables) {
		return getValeur();
	}

}
