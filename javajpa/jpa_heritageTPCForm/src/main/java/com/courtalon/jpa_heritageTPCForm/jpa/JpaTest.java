package com.courtalon.jpa_heritageTPCForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.courtalon.jpa_heritageTPCForm.beans.*;


public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testHibernate");
        Scanner input = new Scanner(System.in);

        input.nextLine();
        System.out.println("--------------------------------------");
		prepareData(emf);

        input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);

        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close();
		System.out.println("done...");
	}




	public static void prepareData(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Client c1 = new Client(0, "eponge", "bob", "bob0001", 200.0);
		em.persist(c1);
		Employe e1 =new Employe(0, "etoile", "patrick", "chasseur de meduse", 150.0);
		em.persist(e1);
		Personne p1 = new Personne(0, "doe", "john");
		em.persist(p1);
		ClientGold cg1 = new ClientGold(0, "crab", "capitaine", "cc00002", 550.0, "CG00001");
		em.persist(cg1);
		
		for (int i = 0; i < 250; i++) {
			em.persist(new Client(0, "client" + i, "joe"+ i, "CLI"+ i, 200.0));
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
		
		List<Client> clients = 
				em.createQuery("select c from Client as c",
				Client.class).getResultList();
		
		for (Client c : clients)
			System.out.println(c);
		
		List<Personne> personnes = 
				em.createQuery("select p from Personne as p",
						Personne.class).getResultList();
		for (Personne p : personnes)
			System.out.println(p);
		
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
