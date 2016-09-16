package com.courtalon.produitBioForm.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import org.springframework.beans.factory.annotation.Autowired;

import com.courtalon.produitBioForm.metier.Categorie;
import com.courtalon.produitBioForm.repositories.CategorieRepository;
import com.opensymphony.xwork2.ActionSupport;

public class CategorieAction extends ActionSupport 
{
	
	@Autowired
	private CategorieRepository categorieRepository;
	public CategorieRepository getCategorieRepository() {return categorieRepository;}
	public void setCategorieRepository(CategorieRepository categorieRepository) {this.categorieRepository = categorieRepository;}
	
	private Iterable<Categorie> categories;
	public Iterable<Categorie> getCategories() {
		return categories;
	}
	
	public String index() {
		categories = categorieRepository.findAll();
		return SUCCESS;
	}
	
}
