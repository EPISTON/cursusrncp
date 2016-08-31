package com.courtalon.firstSpringForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.firstSpringForm.beans.Message;
import com.courtalon.firstSpringForm.beans.Missive;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        //Message msg = (Message)ctx.getBean("msg1");
        Message msg = ctx.getBean("msg1", Message.class);
        Message msg2 = ctx.getBean("msg2", Message.class);
        
        System.out.println(msg);
        System.out.println(msg2);
        
        Missive msv1 = ctx.getBean("missive1", Missive.class);
        System.out.println(msv1);
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
