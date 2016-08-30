package com.courtalon.jpa_universityForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.courtalon.jpa_universityForm.beans.*;

public class JpaTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testHibernate");
        Scanner input = new Scanner(System.in);

        input.nextLine();
        System.out.println("--------------------------------------");
		initData(emf);

/*        input.nextLine();
		System.out.println("--------------------------------------");
		requete1(emf);

		input.nextLine();
		System.out.println("--------------------------------------");
		requete2(emf);

		input.nextLine();
		System.out.println("--------------------------------------");
		requete3(emf);
*/
		input.nextLine();
		System.out.println("--------------------------------------");
		requete4(emf);
		
		
        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close();
		System.out.println("done...");
	}




	public static void initData(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		em.persist(new Professeur(0, "albert Einstein", 40000.0));
		em.persist(new Professeur(0, "chuck Norris", 35000.0));
		em.persist(new Professeur(0, "wolfgang Amadeus Mozart", 30000.0));
		
		em.persist(new Matiere(0, "java"));
		em.persist(new Matiere(0, "parachutisme"));
		em.persist(new Matiere(0, "piano"));
		em.persist(new Matiere(0, "programmation commando"));
		
		Random rd = new Random();
		for (int i = 1; i <= 10; i++) {
			Cours c = new Cours(0, new Date(rd.nextInt(15) + 100, 3, 3), rd.nextInt(15) + 35 );
			c.setMatiere(em.find(Matiere.class, rd.nextInt(4) + 1));
			c.setProfesseur(em.find(Professeur.class, rd.nextInt(3) + 1));
			em.persist(c);
		}
		
		for (int i = 1; i <= 100; i++) {
			Etudiant et = new Etudiant(0, "archimede"+ i, "eureka@hamam.com");
			for (int j = 1; j <= 10; j++) {
				if (rd.nextDouble() < 0.33) {
					// j'ajoute l'etudiant courant au cours no J
					em.find(Cours.class, j).getEtudiants().add(et);
				}
			}
			em.persist(et);
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}


	public static void requete1(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		System.out.println("années minimale ? ");
		Scanner input = new Scanner(System.in);
		int annee = Integer.parseInt(input.nextLine());
		Date d = new Date(annee - 1900, 0, 1);
		
		TypedQuery<Etudiant> q1 = em.createQuery(
				"select distinct(et) from Etudiant as et join et.cours as c where c.date > :datemin",
				Etudiant.class);
		q1.setParameter("datemin", d);
		List<Etudiant> etudiants = q1.getResultList();
		for (Etudiant et: etudiants)
			System.out.println(et);
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

	public static void requete2(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Query q1 = em.createQuery("select c, count(et.id) "
				+ " from Cours as c join c.etudiants as et group by c");
		List<Object[]> data = q1.getResultList();
		for (Object[] ligne : data) {
			System.out.println(Arrays.toString(ligne));
		}
		System.out.println("----------------------------------");
		Query q2 = em.createQuery("select c, 100.0 * count(et.id) / c.capaciteMax "
				+ " from Cours as c join c.etudiants as et group by c");
		data = q2.getResultList();
		for (Object[] ligne : data) {
			System.out.println(Arrays.toString(ligne));
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

	public static void requete3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Query q1 = em.createQuery("select m, count(et.id) "
								+ " from Matiere as m"
								+ " join m.cours as c "
								+ " join c.etudiants as et "
								+ " group by m");
		
		List<Object[]> data = q1.getResultList();
		for (Object[] ligne : data) {
			System.out.println(Arrays.toString(ligne));
		}
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
	
	public static void requete4(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
/*		TypedQuery<Etudiant> q1 = em.createQuery(
				"select et from Etudiant as et where EXISTS("
				+ " select p from Professeur as p"
				+ " join p.cours as c "
				+ " join c.etudiants as et2 "
				+ " where et.id = et2.id AND p.id = :pid"
				+ ")",
				Etudiant.class);*/
		TypedQuery<Etudiant> q1 = em.createQuery(
				"select distinct(et) from Etudiant as et "
				+ " join et.cours as c "
				+ " where c.professeur.id = :pid "
				+ " ORDER BY et.id",
				Etudiant.class);

		q1.setParameter("pid", 1);
		List<Etudiant> etudiants = q1.getResultList();
		for (Etudiant et : etudiants) {
			System.out.println(et);
		}
		System.out.println("----------------");
		TypedQuery<Etudiant> q2 = em.createQuery(
				"select et from Etudiant as et where NOT EXISTS("
				+ " select p from Professeur as p"
				+ " join p.cours as c "
				+ " join c.etudiants as et2 "
				+ " where et.id = et2.id AND p.id = :pid"
				+ ") ORDER BY et.id",
				Etudiant.class);
		q2.setParameter("pid", 1);
		etudiants = q2.getResultList();
		for (Etudiant et : etudiants) {
			System.out.println(et);
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
}
