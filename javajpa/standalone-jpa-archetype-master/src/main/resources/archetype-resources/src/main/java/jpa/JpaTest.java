#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ${package}.beans.Message;

public class JpaTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testHibernate");
        Scanner input = new Scanner(System.in);

        input.nextLine();
        System.out.println("--------------------------------------");
		test1(emf);

        input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);

        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close();
		System.out.println("done...");
	}




	public static void test1(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Scanner input = new Scanner(System.in);
		System.out.println("titre message ? ");
		String titre = input.nextLine();
		System.out.println("corps message ? ");
		String corps = input.nextLine();
		Message msg = new Message(0, titre, corps);
		em.persist(msg);
		//----------------------------------------------------
		tx.commit();
		em.close();
	}


	public static void test2(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Scanner input = new Scanner(System.in);
		System.out.println("identifiant message? ");
		int id = Integer.parseInt(input.nextLine());
		Message msg = em.find(Message.class, id);
		if (msg != null) {
		    System.out.println(msg.getTitre());
		    System.out.println(msg.getCorps());
        }
        else {
		    System.out.println("message inconnu...");            
        }
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
