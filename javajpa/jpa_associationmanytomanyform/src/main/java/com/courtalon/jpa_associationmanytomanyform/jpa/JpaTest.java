package com.courtalon.jpa_associationmanytomanyform.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		createData(emf);

      /*  input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);

		input.nextLine();
		System.out.println("--------------------------------------");
		test3(emf);

		input.nextLine();
		System.out.println("--------------------------------------");
		test4(emf);
		*/

		input.nextLine();
		System.out.println("--------------------------------------");
		complexQuery(emf);

        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close();
		System.out.println("done...");
	}




	public static void createData(EntityManagerFactory emf)
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
	
	public static void complexQuery(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Scanner input = new Scanner(System.in);
		System.out.println("prix minimum ? ");
		double prixmin = Double.parseDouble(input.nextLine());
		TypedQuery<Livre> q1 = em.createQuery(
				"select l from Livre as l "
				+ " join l.auteurs as a where l.prix > :prixmin AND a.id = :aid",
				Livre.class);

		q1.setParameter("prixmin", prixmin);
		q1.setParameter("aid", 3);
		List<Livre> livres = q1.getResultList();
		for (Livre l : livres)
			System.out.println(l);
		
		// je calcul la moyenne du prix des livres de justin Bieber
		System.out.println("----------------------------------");
		Query q2 = em.createQuery("select a.nom, avg(l.prix) from Auteur as a "
				+ " join a.livres as l where a.id= :aid group by a.nom");
		
		q2.setParameter("aid", 3);
		System.out.println("-------------------------------------");
		List<Object[]> data = q2.getResultList();
		for (Object[] ligne : data)
			System.out.println(Arrays.toString(ligne));
		
		System.out.println("----------------------------------");
		Query q3 = em.createQuery("select a.nom, avg(l.prix) from Auteur as a "
				+ " join a.livres as l group by a.nom");
		
		System.out.println("-------------------------------------");
		data = q3.getResultList();
		for (Object[] ligne : data)
			System.out.println(Arrays.toString(ligne));
		
		
		System.out.println("----------------------------------");
		// cette requette filtre les livres pour ne considérer que ceux dont le
		// nombre de pages est > 300 (clause where)
		// ensuite, regroupe par auteur les lignes en calculant la moyenne des prix
		// puis ne garde sur ces regroupements que ceux dont le prix moyen
		// est > 36 (clause having)
		Query q4 = em.createQuery("select a.nom, avg(l.prix) from Auteur as a "
				+ " join a.livres as l where l.nbPages > 300 group by a.nom "
				+ " having avg(l.prix) > 35");
		
		System.out.println("-------------------------------------");
		data = q4.getResultList();
		for (Object[] ligne : data)
			System.out.println(Arrays.toString(ligne));
		
		System.out.println("----------------------------------");
		// selectionner tous les livres dont justin Bieber n'est pas auteur
		// le mot clé DISTINCT est disponnible si nécéssaire
		// JPA est plus limité que SQL
		// vous ne pouvez pas faire d'aggregation sur le resultat d'une sous-requette
		Query q5 = em.createQuery("select l.titre, l.nbPages from Livre as l "
				+ " where NOT EXISTS("
				+ "select l2 from Livre as l2 join l2.auteurs as a2 "
				+ " where l2.id = l.id AND a2.id = 3"
				+ ")");
		
		System.out.println("-------------------------------------");
		data = q5.getResultList();
		for (Object[] ligne : data)
			System.out.println(Arrays.toString(ligne));
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
	
	
}
