package com.courtalon.springStrutsJpaExo4Form.repositories;

import java.util.List;

import com.courtalon.springStrutsJpaExo4Form.metier.Produit;

public interface IProduitDAO {
	List<Produit> findAll();
	List<Produit> findByCategorie(int cid);
	Produit findByID(int id);
	Produit save(Produit p);
	Produit save(Produit p, int cid);
	void remove(int pid);

}
