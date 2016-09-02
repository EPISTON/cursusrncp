package com.courtalon.springAOPForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springAOPForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        System.out.println("--------------------------------------");
        
        ITextUtils tu = ctx.getBean("tu", ITextUtils.class);
        
        
        System.out.println(tu.censure("bonjour MONDE"));
        System.out.println(tu.compteVoyelle("bonjour MONDE"));
        
        System.out.println(tu.censure(null));
        System.out.println(tu.compteVoyelle(null));
        System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
