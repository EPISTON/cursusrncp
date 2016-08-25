package com.courtalon.jpa_basicmappingform.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.courtalon.jpa_basicmappingform.beans.*;

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
		Produit p = new Produit(0, "spaghetti presto", 8.99, 0.5, 40);
		Produit p2 = new Produit(0, "champignon farci", 12.99, 0.75, 35);
		Produit p3 = new Produit(0, "saumon a l'americaine", 11.99, 0.45, 70);
		Produit p4 = new Produit(0, "choucroute garnie", 15.99, 1.0, 12);
		Produit p5 = new Produit(0, "tourte a la viande", 22.99, 1.5, 30);
		em.persist(p);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.persist(p5);
		
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
		Produit p = em.find(Produit.class, 2);
		System.out.println(p);
		
		System.out.println("---------------------------");
		// requette en JPAQL / EJBQL
		// TypedQuery<Produit> requette = em.createQuery("from Produit", Produit.class);
		TypedQuery<Produit> requette = em.createQuery("select p from Produit as p",
													  Produit.class);
		// execution de la requette
		List<Produit> produits = requette.getResultList();
		for (Produit pr : produits) {
			System.out.println(pr);
		}
		System.out.println("apres liste------------------------");
		p = em.find(Produit.class, 3);
		System.out.println(p);
		p.setPrix(p.getPrix() * 1.1);
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
		// l'entite pointé par la variable p (produit saumon)
		// est dans l'état détaché, car son entityManager est fermé
		// il n'est plus suivi par aucun entitymanager, et n'est plus
		// synchronisé avec la base
		System.out.println("apres em.close");
		p.setPoids(p.getPoids() * 0.9);
		savep = p;
	}

	public static Produit savep;
	
	
	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Produit p2 = em.find(Produit.class, 3);
		
		//em.persist(savep);
		// je veux "re-tracker" l'objet savep
		Produit p = em.merge(savep);
		System.out.println("p == savep ? " + (p == savep));
		System.out.println("p == p2 ? " + (p == p2));
		System.out.println(p);
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
	
	
}
