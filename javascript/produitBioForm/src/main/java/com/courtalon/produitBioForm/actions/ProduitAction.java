package com.courtalon.produitBioForm.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.courtalon.produitBioForm.metier.Produit;
import com.courtalon.produitBioForm.repositories.ProduitRepository;
import com.opensymphony.xwork2.ActionSupport;

public class ProduitAction extends ActionSupport {
	
		
	@Autowired
	private ProduitRepository produitRepository;
	public ProduitRepository getProduitRepository() {return produitRepository;}
	public void setProduitRepository(ProduitRepository produitRepository) {this.produitRepository = produitRepository;}

	private Iterable<Produit> produits;
	public Iterable<Produit> getProduits() {
		return produits;
	}
	
	private String searchTerm;
	public String getSearchTerm() {return searchTerm;}
	public void setSearchTerm(String searchTerm) {this.searchTerm = searchTerm;}
	
	//  GET -> /produit/
	public String liste() {
		produits = produitRepository.findAll(); 
		//produits = produitDAO.findAll();
		return SUCCESS;
	}
	
	//  GET -> /produit/search/:searchterm
	public String searchByName() {
		produits = produitRepository.findByNomContaining(getSearchTerm());
		return SUCCESS;
	}
	

}
