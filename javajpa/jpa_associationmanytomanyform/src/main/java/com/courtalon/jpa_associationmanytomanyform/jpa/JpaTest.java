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
		test3(emf);

		input.nextLine();
		System.out.println("--------------------------------------");
		test4(emf);
		
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

	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Auteur a = new Auteur(0, "j.r.r Tolkien", "tolkien@gandalfForever.com","anglais");
		
		Livre l = new Livre(0, "le hobbit", new Date(69, 10, 10), 287, 25.99);
		em.persist(a);
		em.persist(l);
		
		//l.getAuteurs().add(a);
		
		 // cet appel ne sauvegarderas PAS l'association, car
		// la collection livres de Auteur n'est pas maitre de l'association
		// c'est ici juste le "mirroir" de la collection "auteurs" de Livre
		//a.getLivres().add(l);
		
		// peut importe laquelle on appele, au final addAuteur sera appelé
		//l.addAuteur(a);
		a.addLivre(l);
	
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

	public static void test4(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("id auteur a supprimer ?");
		Auteur a = em.find(Auteur.class, Integer.parseInt(input.nextLine()));
		System.out.println(a);
		// appel manuel possible de clearLivre
		// mais il sera de toute facon appelé automatiquement avant remove
		// grace a l'annotation @PostRemove
		//a.clearLivre();
		System.out.println("---------------");
		em.remove(a);
	
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
	
}
