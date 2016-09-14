package com.courtalon.produitBioForm.repositories;

import java.util.List;

import com.courtalon.produitBioForm.metier.Produit;

public interface IProduitDAO {
	
	List<Produit> findAll();
	List<Produit> findByName(String nom);
	
}
