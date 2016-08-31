package com.courtalon.springCamelotForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springCamelotForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
     
        // je récupere le bean "jacqouille" qui fournit le service IChevalier
        IChevalier c1 = ctx.getBean(IChevalier.class, "jacqouille");
        
        c1.partirEnQuete();
        
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
