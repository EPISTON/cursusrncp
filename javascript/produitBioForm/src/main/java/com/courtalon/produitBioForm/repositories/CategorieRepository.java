package com.courtalon.produitBioForm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.courtalon.produitBioForm.metier.Categorie;

public interface CategorieRepository 
		extends CrudRepository<Categorie, Integer>
{

}
