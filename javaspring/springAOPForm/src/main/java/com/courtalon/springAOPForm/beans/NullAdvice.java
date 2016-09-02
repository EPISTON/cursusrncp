package com.courtalon.springAOPForm.beans;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class NullAdvice implements MethodBeforeAdvice
{
	@Override
	public void before(Method methode, Object[] arguments, Object cible) throws Throwable {
		
		System.out.println("nullAdvice: appel de " + methode.getName() 
						+ " avec les arguments " + Arrays.toString(arguments) +
						" sur l'objet de classe " + cible.getClass().getName());
		
		
		if (arguments.length == 1 && arguments[0] == null ) {
			arguments[0] = "";
		}
		
	}
}
