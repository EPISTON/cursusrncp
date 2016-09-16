package com.courtalon.produitBioForm.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.produitBioForm.metier.Produit;

public interface ProduitRepository 
		extends PagingAndSortingRepository<Produit, Integer>
{
	Iterable<Produit> findByNomContaining(String name);
	Iterable<Produit> findByNomIgnoreCaseContaining(String name);
//	Iterable<Produit> findByNomAndByStock(String name, int stock);
}
