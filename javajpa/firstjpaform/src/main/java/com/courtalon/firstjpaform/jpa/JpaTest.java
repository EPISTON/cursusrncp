package com.courtalon.firstjpaform.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.courtalon.firstjpaform.beans.Message;

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
		Scanner input = new Scanner(System.in);
		System.out.println("titre message ? ");
		String titre = input.nextLine();
		System.out.println("corps message ? ");
		String corps = input.nextLine();
		Message msg = new Message(0, titre, corps);
		// insertion dans la base
		// persist sert uniquement pour "creer" une nouvelle ligne
		// c'est à dire une insertion
		em.persist(msg);
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
		System.out.println("identifiant message? ");
		int id = Integer.parseInt(input.nextLine());
		// la méthode find permet de recharger/trouver une entité en indiquant
		// une valeur pour sa clé primaire
		// la méthode find à 2 parametre:
		//    la classe de l'objet recherché (quel est l'entité voulue)
		//    la valeur de la clé primaire
		Message msg = em.find(Message.class, id);
		// s'il ne le trouve pas, il renvoie null
		if (msg != null) {
		    System.out.println(msg.getTitre());
		    System.out.println(msg.getCorps());
		    
		    System.out.println("nouveau titre de message?");
		    String titre = input.nextLine();
		    msg.setTitre(titre);
		    System.out.println("ok, modification dans l'objet faite");
		    System.out.println("en fait non, je met un autre titre : 'yolo'");
		    msg.setTitre("yolo");
        }
        else {
		    System.out.println("message inconnu...");            
        }
		
		
		
		//----------------------------------------------------
		tx.commit();
		System.out.println("apres commit 1 ...");
		tx.begin();
		msg.setTitre("vive pikachu");
		System.out.println("apres pokemon");
		tx.commit();
		em.close();
	}

}
