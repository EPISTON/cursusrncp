package com.courtalon.jpaboutiqueform.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.courtalon.jpaboutiqueform.beans.*;

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
		test5(emf);
		
        input.nextLine();
		System.out.println("--------------------------------------");
		test6(emf);
		
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
		em.persist(new Categorie(0, "meuble"));
		em.persist(new Categorie(0, "informatique"));
		em.persist(new Categorie(0, "vetements"));
		em.persist(new Categorie(0, "alimentaire"));
		em.persist(new Categorie(0, "vaisseau spaciaux"));
	
		Random rd = new Random();
		for (int i = 1; i <= 50; i++) {
			Produit p = new Produit(0,
									"produit"+i,
									rd.nextDouble()*1000.0,
									rd.nextDouble()*250.0,
									rd.nextInt(15));
			p.setCategorie(em.find(Categorie.class, rd.nextInt(5) + 1));
			em.persist(p);
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
		List<Produit> produits = em.createQuery("from Produit", Produit.class).getResultList();
		for (Produit p : produits) {
			System.out.println(p.getNom() + " -> " + p.getCategorie().getLibelle());
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
		Categorie c1 =  em.find(Categorie.class, 1);
		for(Produit p : c1.getProduits()) {
			System.out.println(p.getNom());
		}
		
		
		
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
		
		Produit p = em.find(Produit.class, 1);
		System.out.println(p + " -> " + p.getCategorie());
		System.out.println("no nouvelle categorie ?");
		Scanner input = new Scanner(System.in);
		int cid = Integer.parseInt(input.nextLine());
		p.setCategorie(em.find(Categorie.class, cid));
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
	public static void test5(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Query q1 = em.createQuery(
				"SELECT c.libelle, AVG(p.prix), AVG(p.poids) FROM Categorie AS c"
				+ " JOIN c.produits AS p WHERE p.stock > 5 GROUP BY c.libelle");
		List<Object[]> data = q1.getResultList();
		for (Object[] ligne : data) {
			System.out.println(Arrays.toString(ligne));
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
	public static void test6(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
