package com.courtalon.produitBioForm.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.produitBioForm.metier.Produit;


public class ProduitDAO implements IProduitDAO {

	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}

	@Override
	@Transactional
	public List<Produit> findAll() {
		return em.createQuery("from Produit", Produit.class).getResultList();
	}

	@Override
	@Transactional
	public List<Produit> findByName(String nom) {
		TypedQuery<Produit> q = em.createQuery("select p from Produit as p where p.nom like :name",
						Produit.class);
		q.setParameter("name", "%" + nom + "%");
		return q.getResultList();
	}

}
