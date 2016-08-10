package javaCalculatorForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Expression {
	protected  List<Expression> operandes;
	
	// ajout d'un enfant a mon element composite
	// dans notre cas ici, ajout d'un operande a notre expression
	public void addOperande(Expression op) {
		operandes.add(op);
	}
	public void removeOperande(Expression op) {
		operandes.remove(op);
	}
	// le constructeur initialise ma liste d'opérandes (vide)
	public Expression() {
		operandes = new ArrayList<>();
	}
	
	// cette fonction sera overridé par les classes concrete d'expression
	// comme operation, valeur immediate, etc...
	// evaluer({x: 15, y: 10...})
	// cette map contient la liste des valeurs que von prendre les variables
	public abstract double evaluer(Map<String, Double> variables);
	public double evaluer() {return evaluer(new HashMap<>());}
	
}