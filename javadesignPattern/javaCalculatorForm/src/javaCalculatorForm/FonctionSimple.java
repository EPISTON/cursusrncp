package javaCalculatorForm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class FonctionSimple extends Expression {

	private Method fonction;
	
	public FonctionSimple(String nomFonction) {
		Class cls = Math.class;
		fonction = null;
		try {
			fonction = cls.getMethod(nomFonction, double.class);
		} catch (NoSuchMethodException e) {e.printStackTrace();}
		catch (SecurityException e) {e.printStackTrace();}
	}
	
	@Override
	public double evaluer(Map<String, Double> variables) {
		double operande = operandes.get(0).evaluer(variables);
		double resultat = 0.0;
		try {
			// ici, j'appele la fonction en lui donnant en parametre le premier operande
			resultat = (double)fonction.invoke(null, operande);
		} catch (IllegalAccessException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		return resultat;
	}

}
