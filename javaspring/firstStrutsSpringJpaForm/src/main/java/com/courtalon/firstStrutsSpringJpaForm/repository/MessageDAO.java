package com.courtalon.firstStrutsSpringJpaForm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.firstStrutsSpringJpaForm.metier.Message;

public class MessageDAO implements IMessageDAO {

	// ceci indique a spring d'injecter ici l'entity manager en provenance
	// de l'entity manager factory (grace a annotationDriven)
	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}
	

	
	// transactional indique a spring de mettre en place (via AOP)
	// toute la logique nécéssaire au requettage
	// c.a.d un entityManager pret a l'emploie, une transaction ouverte
	// qui sera automatiquement commitée a la sortie de la fonction
	@Override
	@Transactional
	public List<Message> findAll() {
		return em.createQuery("select m from Message as m", Message.class).getResultList();
	}

	@Override
	@Transactional
	public Message findByID(int id) {
		return em.find(Message.class, id);
	}

	@Override
	@Transactional
	public Message save(Message m) {
		Message existing = em.find(Message.class, m.getId());
		// si l'entite existe deja en base, faire un Merge
		if (existing != null) {
			existing = em.merge(m);
		}
		// sinon, faire un persist car nouvelle entité
		else {
			em.persist(m);
			existing = m;
		}
		return existing;
	}

	@Override
	@Transactional
	public void remove(int id) {
		Message existing =  em.find(Message.class, id);
		if (existing != null)
			em.remove(existing);
	}

}
