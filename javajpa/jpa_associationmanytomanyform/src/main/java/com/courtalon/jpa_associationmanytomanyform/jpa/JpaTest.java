package com.courtalon.jpa_associationmanytomanyform.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.courtalon.jpa_associationmanytomanyform.beans.*;

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
		
		em.persist(new Auteur(0, "emile Zola", "emile@sncf.com", "francais"));
		em.persist(new Auteur(0, "jules Vernes", "jule@aventure.com", "francais"));
		em.persist(new Auteur(0, "justin Bieber", "justin@yolo.com", "anglais"));
		em.persist(new Auteur(0, "samuel Beckett", "sam@spa.com", "anglais"));
		em.persist(new Auteur(0, "miguel de Cervantes", "miguel@eolienne.com", "espagnol"));
		em.persist(new Auteur(0, "choderlos de Laclos", "robertos@arlequin.com", "espagnol"));
		
		Random rd = new Random();
		for (int i = 1;  i <= 100; i++) {
			Livre l = new Livre(0,
					"titre" + i,
					new Date(rd.nextInt(16) + 100,rd.nextInt(12),15),
					rd.nextInt(300) + 150,
					rd.nextDouble() * 50.0 + 10.0);
			// pourt chaque auteur, 40% de chances qu'il soit auteur de ce livre
			for (int j = 1; j <= 6; j++) {
				if (rd.nextDouble() < 0.4)
					l.getAuteurs().add(em.find(Auteur.class, j));
			}
			em.persist(l);
		}
		
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
		Livre l = em.find(Livre.class, 1);
		System.out.println(l);
		for (Auteur a : l.getAuteurs())
			System.out.println(a);
		
		System.out.println("----------------------");
		
		Auteur a = em.find(Auteur.class, 1);
		System.out.println(a);
		for (Livre l1 : a.getLivres()) {
			System.out.println(l1);
		}
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
