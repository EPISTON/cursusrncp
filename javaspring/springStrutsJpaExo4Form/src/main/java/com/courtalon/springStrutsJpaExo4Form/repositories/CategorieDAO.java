package com.courtalon.springStrutsJpaExo4Form.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springStrutsJpaExo4Form.metier.Categorie;

public class CategorieDAO implements ICategorieDAO {

	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}

	
	@Override
	@Transactional
	public List<Categorie> findAll() {
		return em.createQuery("from Categorie", Categorie.class).getResultList();
	}

	@Override
	@Transactional
	public Categorie findByID(int id) {
		return em.find(Categorie.class, id);
	}

	@Override
	@Transactional
	public Categorie save(Categorie c) {
		Categorie existing = em.find(Categorie.class, c.getId());
		if (existing != null)
			return em.merge(c);
		else {
			em.persist(c);
			return c;
		}
	}

	@Override
	@Transactional
	public void remove(int cid) {
		Categorie existing = em.find(Categorie.class, cid);
		if (existing != null)
			em.remove(existing);
	}

}
