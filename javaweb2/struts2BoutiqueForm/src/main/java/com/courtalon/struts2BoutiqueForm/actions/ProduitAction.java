package com.courtalon.struts2BoutiqueForm.actions;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.courtalon.struts2BoutiqueForm.metier.Categorie;
import com.courtalon.struts2BoutiqueForm.metier.Produit;
import com.courtalon.struts2BoutiqueForm.utils.BDDListener;
import com.courtalon.struts2BoutiqueForm.utils.ProduitDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProduitAction extends ActionSupport 
						implements ApplicationAware, ModelDriven<Produit>
{
	
	private static Logger log = LogManager.getLogger(ProduitAction.class); 
	private static final long serialVersionUID = 1L;

	private ProduitDAO produitDAO;
	@Override
	public void setApplication(Map<String, Object> app) {
		produitDAO = (ProduitDAO)app.get(BDDListener.PRODUIT_DAO);
	}
	
	private List<Produit> produits;
	public List<Produit> getProduits() {return produits;}

	// la liste des categories est utilisée pour le choix déroulant
	// de catégorie lors de l'edition/creation/sauvegarde d'un produit
	private List<Categorie> categories;
	public List<Categorie> getCategories() {
		if (categories == null)
			categories = produitDAO.listCategorie();
		return categories;
	}
	
	// action liste
	public String liste() {
		produits = produitDAO.findAll();
		return SUCCESS;
	}
	public String edit() {
		Produit p = produitDAO.findById(getModel().getId());
		if (p == null)
			return "notfound";
		// ceci marche car on a demandé a modelDriven de rappeler getModel
		// après l'action
		this.model = p;
		return SUCCESS;
	}

	public String create() {
		getModel().setCategorieId(1);
		return SUCCESS;
	}
	
	public String save() {
		produitDAO.save(getModel());
		return SUCCESS;
	}
	public String delete() {
		produitDAO.deleteOne(getModel().getId());
		return SUCCESS;
	}

	private Produit model; 
	@Override
	public Produit getModel() {
		if (model == null)
			model = new Produit();
		return model;
	}


	
	
}
