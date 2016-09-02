package com.courtalon.springAOPForm.beans;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class VoyelleInterceptor implements MethodInterceptor
{

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object[] arguments = mi.getArguments();
		// si l'argument n'est pas traitable correctement, renvoyer directement 0
		if (arguments.length != 1 || !(arguments[0] instanceof String) || arguments[0] == null) {
			System.out.println("on apelle pas l'original, renvoie directment 0");
			return 0;
		}
		// je passe l'argument en minuscule
		arguments[0] = ((String)arguments[0]).toLowerCase();
		// appel la fonction originale
		Object returnValue = mi.proceed();
		System.out.println("on a appele l'original, renvoie " + returnValue);
		
		return returnValue;
	}

	
}
