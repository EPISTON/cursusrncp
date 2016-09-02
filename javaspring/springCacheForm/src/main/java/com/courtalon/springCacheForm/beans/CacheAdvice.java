package com.courtalon.springCacheForm.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.support.ArgumentConvertingMethodInvoker;

public class CacheAdvice implements MethodInterceptor {

	private Map<String, Map<String, Object>> globalcache = new HashMap<>();
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		System.out.println("avant appel de " + mi.getMethod().getName());
		Object[] arguments = mi.getArguments();
		String clef = Arrays.toString(arguments);
		// la clef pour la methode combine
		//		le nom de la classe
		//		le nom de la methode
		//		le type de retour
		//		le type des arguments
		String methodClef = mi.getThis().getClass().getName() + "#"
							+ mi.getMethod().getName() + "#"
							+ mi.getMethod().getReturnType().getName() + "#"
							+ Arrays.toString(mi.getMethod().getParameterTypes());
		
		// le cache pour cette méthode
		Map<String, Object> cache = null;
		// si le cache n'existe pas, on le cree
		if (globalcache.containsKey(methodClef)) {
			System.out.println("je connais déja la methode : " + methodClef);
			cache = globalcache.get(methodClef);
		}
		else {
			System.out.println("nouvelle methode, creation cache : " + methodClef);
			cache = new HashMap<>();
			globalcache.put(methodClef, cache);
		}
		// si on a déjà la valeur en cache,  on la renvoie directement
		if (cache.containsKey(clef)) {
			System.out.println("lecture depuis le cache");
			return cache.get(clef);
		}
		else {
			System.out.println("nouvelle valeur a mettre dans le cache");
			Object returnValue = mi.proceed();
			cache.put(clef, returnValue);
			return returnValue;
		}
	}

}
