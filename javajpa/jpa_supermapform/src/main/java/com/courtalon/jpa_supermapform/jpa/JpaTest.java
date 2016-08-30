package com.courtalon.jpa_supermapform.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.courtalon.jpa_supermapform.beans.*;

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
		Adresse a1 = new Adresse("221b baker street", "12345", "londre", "angleterre");
		Adresse a2 = new Adresse("2 rue du larcin", "75016", "paris", "france");
		
		Site s1 = new Site(0, "holmes home", a1, new Localisation(0, 50));
		Site s2 = new Site(0, "hotel particulier d'arsene lupin", a2, new Localisation(2, 45));
		
		em.persist(s1);
		em.persist(s2);
		//em.persist(new Site(0, "test", null));
		
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
		
		Site s1 = em.find(Site.class, 1);
		System.out.println(s1.getAdresse().getVille());
		System.out.println(s1.getLoc());
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
