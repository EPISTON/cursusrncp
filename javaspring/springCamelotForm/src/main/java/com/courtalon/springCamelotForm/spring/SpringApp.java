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
        IChevalier c1 = ctx.getBean("jacqouille", IChevalier.class );
        IChevalier c2 = ctx.getBean("perceval", IChevalier.class);
        IChevalier c3 = ctx.getBean("caradoc", IChevalier.class);
        
        //ChevalierTableRonde c3 = ctx.getBean("caradoc", ChevalierTableRonde.class);
        c1.partirEnQuete();
        c2.partirEnQuete();
        c3.partirEnQuete();
        
        //Cheval b1 = ctx.getBean("cheval3", Cheval.class);
        //System.out.println("cheval = " + b1.getNom());
        //b1.setNom("ahahah");
        
        // comme le scope du cheval3 est prototype
        // le vhavalier roland n'est pas impacter par la modification
        // sur le premier exemplaire du cheval3
        // il recoit son propre nouvel exemplaire du cheval3
        IChevalier c4 = ctx.getBean("roland", IChevalier.class);
        c4.partirEnQuete();
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
