package com.courtalon.springAOPForm.beans;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class MinusculeAdvice implements MethodBeforeAdvice
{

	@Override
	public void before(Method methode, Object[] arguments, Object cible) throws Throwable {
		// methode est un objet décrivant en détail la méthode appelée
		// arguments est le tableau des parametre passé lors de l'appel de cette fonction
		// cible est l'objet depuis lequel est appelé la méthode (this)
		System.out.println("MinusculeAdvice: appel de " + methode.getName() 
						+ " avec les arguments " + Arrays.toString(arguments) +
						" sur l'objet de classe " + cible.getClass().getName());
		
		// je verifie que l'on a bien un parametre de type string, et si oui, je le passe
		// automatiquement en minuscule
		if (arguments.length == 1 /*&& arguments[0] != null && arguments[0] instanceof String*/) {
			arguments[0] = ((String)arguments[0]).toLowerCase();
		}
		
	}

	
	
}
