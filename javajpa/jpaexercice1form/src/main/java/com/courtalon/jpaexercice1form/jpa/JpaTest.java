package com.courtalon.jpaexercice1form.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.courtalon.jpaexercice1form.beans.*;

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
		Random rd = new Random();
		for (int i = 1; i <= 20; i++) {
			em.persist(new Enchere(	0,
									"enchere" + i,
									rd.nextDouble() * 100.0 + 10.0,
									rd.nextDouble() * 100.0 + 20.0,
									rd.nextDouble() * 10.0 + 1.0));
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
		Scanner input = new Scanner(System.in);
		System.out.println("identifiant enchere ? ");
		int id = Integer.parseInt(input.nextLine());
		// recherche de l'enchere voulue dans la base
		Enchere e1 = em.find(Enchere.class, id);
		System.out.println(e1);
		
		System.out.println("-----------------");
		TypedQuery<Enchere> requette1 = em.createQuery("select e from Enchere as e", Enchere.class);
		// pour faire de la "pagination"
		//requette1.setFirstResult(5);
		//requette1.setMaxResults(10);
		List<Enchere> encheres = requette1.getResultList();
		for (Enchere e2 : encheres)
			System.out.println(e2);
		
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
		Scanner input = new Scanner(System.in);
		System.out.println("no enchere a modifier ?");
		int id = Integer.parseInt(input.nextLine());
		Enchere e1 = em.find(Enchere.class, id);
		System.out.println(e1);
		System.out.println("nouveau prix actuel ?");
		e1.setPrixActuel(Double.parseDouble(input.nextLine()));
		System.out.println("ok1");
		System.out.println("prix minimum ? ");
		double prixMin = Double.parseDouble(input.nextLine());
		TypedQuery<Enchere> requette1 = em.createQuery(
				"select e from Enchere as e where e.prixActuel > :pmin"
				, Enchere.class);
		requette1.setParameter("pmin", prixMin);
		List<Enchere> encheres = requette1.getResultList();
		for (Enchere e2 : encheres)
			System.out.println(e2);
		
		System.out.println("ok2");
		
		Query requette2 = em.createQuery("select e.description, e.prixDepart from Enchere as e "
				+ " where e.prixActuel > 50 AND e.enchereMinimum > 5");
		List<Object[]> data = requette2.getResultList();
		for (Object[] ligne : data)
			System.out.println(Arrays.toString(ligne));
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

	
}
