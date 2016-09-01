package com.courtalon.springAcademyForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springAcademyForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        IArtiste a1 = ctx.getBean("AndreRieu", IArtiste.class);
        IArtiste a2 = ctx.getBean("patrickTopalof", IArtiste.class);
        IArtiste a3 = ctx.getBean("anthonyGatto", IArtiste.class);
        
        a1.faireNumero();
        a2.faireNumero();
        a3.faireNumero();
        
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
