package com.courtalon.springCacheForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springCacheForm.beans.*;

public class SpringApp {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        System.out.println("--------------------------------------");
 
        ITextUtils tu = ctx.getBean("tu", ITextUtils.class);
        
        System.out.println(tu.compteVoyelle("aujourd'hui"));
        System.out.println(tu.compteVoyelle("demain"));
        System.out.println(tu.compteVoyelle("aujourd'hui"));
        System.out.println(tu.censure("aujourd'hui"));
        System.out.println(tu.censure("lundi"));
        System.out.println(tu.censure("aujourd'hui"));
        
        
 		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
