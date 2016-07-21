package javaClasseInterne;

import javaClasseInterne.AExterne.AInterne1;
import javaClasseInterne.AExterne.AInterne3;

public class Program {

	public static void main(String[] args) {
		AInterne1 a1 = new AExterne.AInterne1();
		a1.test1(); 
		AInterne1 a2 = new AExterne.AInterne1();
		a2.test1();
		// par contre, AInterne2 est inaccessible depuis le main
	
		AExterne ax1 = new AExterne();
		
		AInterne3 a3 = ax1.new AInterne3();
		a3.test1();
		System.out.println("---------------");
		a3.test1();
		
		AInterne3 a4 = ax1.new AInterne3();
		System.out.println("---------------");
		a4.test1();
		
	}

}
