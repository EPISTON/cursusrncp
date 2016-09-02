package com.courtalon.springAOPForm.beans;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

public class LogAfterAdvice implements AfterReturningAdvice, ThrowsAdvice
{

	@Override
	public void afterReturning(Object returnValue, Method methode, Object[] arguments, Object cible) throws Throwable {
		
		System.out.println("return value of " + methode.getName() +
						" on " + Arrays.toString(arguments) + " is "
						+ returnValue);
	}

	public void afterThrowing(Method methode, Object[] arguments, Object cible, NullPointerException ex) {
		System.out.println("error on call of " + methode.getName() +
				" on " + Arrays.toString(arguments) + " is "
				+ ex.getMessage());
	
	}
	
	
}
