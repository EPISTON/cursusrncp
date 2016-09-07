package com.courtalon.springStrutsJpaExo4Form.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springStrutsJpaExo4Form.metier.Categorie;
import com.courtalon.springStrutsJpaExo4Form.metier.Illustration;
import com.courtalon.springStrutsJpaExo4Form.metier.Produit;

public class ProduitDAO implements IProduitDAO {
	
	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}

	@Override
	@Transactional
	public List<Produit> findByCategorie(int cid) {
		if (cid == 0)
			return findAll();
		TypedQuery<Produit>  q =em.createQuery(
				"select p from Produit as p where p.categorie.id = :cid",
				Produit.class);
		q.setParameter("cid", cid);
		return q.getResultList();
	}

	@Override
	@Transactional
	public List<Produit> findAll() {
		return em.createQuery("from Produit", Produit.class).getResultList();
	}

	
	@Override
	@Transactional
	public Produit findByID(int id) {
		return em.find(Produit.class, id);
	}

	@Override
	public Produit save(Produit p) {
		return save(p,
				(p.getCategorie() == null )? 0 : p.getCategorie().getId(),
				(p.getIllustration() == null )? 0 : p.getIllustration().getId()	);
	}

	@Override
	@Transactional
	public Produit save(Produit p, int cid, int iid) {
		Produit existing = em.find(Produit.class, p.getId());
		// je récupere la categorie choisie pour ce produit
		Categorie c = em.find(Categorie.class, cid);
		Illustration i = em.find(Illustration.class, iid);
		if (existing != null) {
			// produit existant
			// ici, si le Produit est déjà associé a cette categorie c
			// le find renvera la même instance de categorie, et donc pas de
			// mise a jour du categorie_ID
			// ici, l'important est de bien sauvegarde un objet produit associé
			// a un objet categorie en provenance de la base/entityManager
			p.setCategorie(c);
			p.setIllustration(i);
			return em.merge(p);
		}
		else {
			// nouveau produit
			// j'associe le nouveau Produit a cette categorie
			p.setCategorie(c);
			p.setIllustration(i);
			em.persist(p);
			return p;
		}
	}

	@Override
	@Transactional
	public void remove(int pid) {
		Produit existing = em.find(Produit.class, pid);
		if (existing != null)
			em.remove(existing);
	}

}
