package javaCalculatorForm;

import java.util.Map;

public class Variable extends Expression {

	private String variableName;
	
	public Variable(String variableName) {
		this.variableName = variableName;
	}

	// je renvois la valeur correspondant a mon nom de variable
	// dans la Map "variables" passée en parametre a evaluer
	@Override
	public double evaluer(Map<String, Double> variables) {
		return variables.get(variableName);
	}
}
