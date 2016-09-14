package com.courtalon.produitBioForm.actions;

import java.util.List;

import com.courtalon.produitBioForm.metier.Produit;
import com.courtalon.produitBioForm.repositories.IProduitDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ProduitAction extends ActionSupport {
	
	private IProduitDAO produitDAO;
	public void setProduitDAO(IProduitDAO produitDAO) {this.produitDAO = produitDAO;}
	

	private List<Produit> produits;
	public List<Produit> getProduits() {
		return produits;
	}
	
	private String searchTerm;
	public String getSearchTerm() {return searchTerm;}
	public void setSearchTerm(String searchTerm) {this.searchTerm = searchTerm;}
	
	//  GET -> /produit/
	public String liste() {
		produits = produitDAO.findAll();
		return SUCCESS;
	}
	
	//  GET -> /produit/search/:searchterm
	public String searchByName() {
		produits = produitDAO.findByName(getSearchTerm());
		return SUCCESS;
	}
	

}
