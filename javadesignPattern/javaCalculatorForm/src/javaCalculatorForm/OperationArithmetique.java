package javaCalculatorForm;

import java.util.Map;

public class OperationArithmetique extends Expression {
	public static final int OPERATION_PLUS  = 0;
	public static final int OPERATION_MOINS  = 1;
	public static final int OPERATION_MULTIPLICATION  = 2;
	public static final int OPERATION_DIVISION  = 3;
	
	private int operation;
	
	public OperationArithmetique(int operation) {
		this.operation = operation;
	}
	
	
	@Override
	public double evaluer(Map<String, Double> variables) {
		double resultat = operandes.get(0).evaluer(variables);
		switch(operation) {
			case OPERATION_PLUS:
				for (int i = 1; i < operandes.size(); i++)
					resultat += operandes.get(i).evaluer(variables);
				break;
			case OPERATION_MOINS:
				for (int i = 1; i < operandes.size(); i++)
					resultat -= operandes.get(i).evaluer(variables);
				break;
			case OPERATION_MULTIPLICATION:
				for (int i = 1; i < operandes.size(); i++)
					resultat *= operandes.get(i).evaluer(variables);
				break;
			case OPERATION_DIVISION:
				for (int i = 1; i < operandes.size(); i++)
					resultat /= operandes.get(i).evaluer(variables);
				break;
		}
		return resultat;
	}

}
