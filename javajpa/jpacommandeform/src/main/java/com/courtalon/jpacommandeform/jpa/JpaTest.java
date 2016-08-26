package com.courtalon.jpacommandeform.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.courtalon.jpacommandeform.beans.*;

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
		em.persist(new Commande("CMD0001", new Date(), 15.0, 5));
		em.persist(new Commande("CMD0002", new Date(), 25.0, 5));
		em.persist(new Commande("CMD0003", new Date(), 35.0, 1));
		em.persist(new Commande("CMD0004", new Date(), 45.0, 6));
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
		System.out.println("avant find");
		Commande c1 = em.find(Commande.class, "CMD0002");
		System.out.println("apres find");
		System.out.println(c1);
		
		System.out.println("avant select");
		List<Commande> commandes = em.createQuery("from Commande", Commande.class).getResultList();
		System.out.println("après select");
		for (Commande c2 : commandes) {
			System.out.println(c2);
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
