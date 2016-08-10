package javaCalculatorForm;

import java.util.HashMap;

public class Program {

	public static void main(String[] args) {

		//Expression expr = new ValeurImmediate(3.1415);
		
		//System.out.println(expr.evaluer());
		
		// 2 + 3
		Expression expr1 = new OperationArithmetique(OperationArithmetique.OPERATION_PLUS);
		expr1.addOperande(new ValeurImmediate(2));
		expr1.addOperande(new ValeurImmediate(3));
		System.out.println(expr1.evaluer());
		// 10 / 2 * 3

		/*
		 * 			expr2 (mutliplication)
		 * 			|					|
		 * 		sousexpr2(division)	  valeurImmediate(3)
		 * 		|				 |
		 * 	valeurImmediate(10) valeurImmediate(2)
		 */
		
		Expression sousexpr2 = new OperationArithmetique(OperationArithmetique.OPERATION_DIVISION);
		sousexpr2.addOperande(new ValeurImmediate(10));
		sousexpr2.addOperande(new ValeurImmediate(2));
		

		Expression expr2 = new OperationArithmetique(OperationArithmetique.OPERATION_MULTIPLICATION);
		expr2.addOperande(sousexpr2);
		expr2.addOperande(new ValeurImmediate(3));
		
		System.out.println(expr2.evaluer());
	/*
		// 3 * sqrt(4 + 12)
		// expr3 = 3 * sqrt(4 + 12)
		// racinecarre = sqrt(4 + 12)
		// addition = 4 + 12
		Expression expr3 = new OperationArithmetique(OperationArithmetique.OPERATION_MULTIPLICATION);
		expr3.addOperande(new ValeurImmediate(3));
		
		Expression racinecarre = new FonctionSimple("sqrt");
		expr3.addOperande(racinecarre);
		
		Expression addition = new OperationArithmetique(OperationArithmetique.OPERATION_PLUS);
		addition.addOperande(new ValeurImmediate(4));
		addition.addOperande(new ValeurImmediate(12));
		racinecarre.addOperande(addition);
		
		System.out.println(expr3.evaluer());
		*/
		
		// 3 * sqrt(x + y)
		Expression expr3 = new OperationArithmetique(OperationArithmetique.OPERATION_MULTIPLICATION);
		expr3.addOperande(new ValeurImmediate(3));
		
		Expression racinecarre = new FonctionSimple("sqrt");
		expr3.addOperande(racinecarre);
		
		Expression addition = new OperationArithmetique(OperationArithmetique.OPERATION_PLUS);
		addition.addOperande(new Variable("x"));
		addition.addOperande(new Variable("y"));
		racinecarre.addOperande(addition);
		
		HashMap<String, Double> variables = new HashMap<>();
		variables.put("x", 20.0);
		variables.put("y", 5.0);
		
		System.out.println(expr3.evaluer(variables));
		
		variables.put("x", 60.0);
		variables.put("y", 40.0);
		System.out.println(expr3.evaluer(variables));
		
		
		
		
	}

}
