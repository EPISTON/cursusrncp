package com.courtalon.springStrutsJpaExo4Form.repositories;

import java.util.List;

import com.courtalon.springStrutsJpaExo4Form.metier.Categorie;

public interface ICategorieDAO {
	List<Categorie> findAll();
	Categorie findByID(int id);
	Categorie save(Categorie c);
	void remove(int cid);
}
