package com.courtalon.springStrutsJpaExo4Form.actions;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.springStrutsJpaExo4Form.metier.Categorie;
import com.courtalon.springStrutsJpaExo4Form.metier.Illustration;
import com.courtalon.springStrutsJpaExo4Form.metier.Produit;
import com.courtalon.springStrutsJpaExo4Form.repositories.ICategorieDAO;
import com.courtalon.springStrutsJpaExo4Form.repositories.IIllustrationDAO;
import com.courtalon.springStrutsJpaExo4Form.repositories.IProduitDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class IndexAction extends ActionSupport implements ModelDriven<Produit>
{
	
	private static Logger log = LogManager.getLogger(IndexAction.class); 
	private static final long serialVersionUID = 1L;

	private IProduitDAO produitDAO;
	private ICategorieDAO categorieDAO; 
	private IIllustrationDAO illustrationDAO;
	
	// j'ai besoin deu categorieDAO pour le formulaire d'edition du produit
	// afin d'afficher les choix possible de categorie
	public void setProduitDAO(IProduitDAO produitDAO) {this.produitDAO = produitDAO;}
	public void setCategorieDAO(ICategorieDAO categorieDAO) {this.categorieDAO = categorieDAO;}
	public void setIllustrationDAO(IIllustrationDAO illustrationDAO) {this.illustrationDAO = illustrationDAO;}

	// liste des produits a afficher dans la page JSP
	private List<Produit> produits;
	public List<Produit> getProduits() {return produits;}
	
	// le modele manipulé par cette classe d'action
	private Produit model;
	@Override
	public Produit getModel() {
		if (model == null)
			model = new Produit();
		return model;
	}
	
	// servire a récupérer depuis le formulaire
	// quelle categorie est choisie pour l'edition du produit
	private int categorieID;
	public int getCategorieID() {return categorieID;}
	public void setCategorieID(int categorieID) {this.categorieID = categorieID;}
	
	// cette liste servira, entre autre, a remplir la liste déroulante de choix
	// de categorie dans le formulaire de Produit
	private List<Categorie> categories;
	public List<Categorie> getCategories() {
		if (categories == null)
			categories = categorieDAO.findAll();
		return categories;
	}
	
	private int illustrationID;
	public int getIllustrationID() {return illustrationID;}
	public void setIllustrationID(int illustrationID) {this.illustrationID = illustrationID;}

	private List<Illustration> illustrations;
	public List<Illustration> getIllustrations() {
		if (illustrations == null)
			illustrations = illustrationDAO.findAll();
		return illustrations;
	}
	
	
	
	// actions
	
	public String index() {
		log.info("appel de index!");
		produits = produitDAO.findAll();
		return SUCCESS;
	}

	public String filter() {
		log.info("appel de filter!");
		produits = produitDAO.findByCategorie(getCategorieID());
		return SUCCESS;
	}
	
	public String edit() {
		Produit p = produitDAO.findByID(getModel().getId());
		if (p == null) {
			return "notfound";
		}
		getModel().setNom(p.getNom());
		getModel().setPoids(p.getPoids());
		getModel().setPrix(p.getPrix());
		getModel().setStock(p.getStock());
		getModel().setCategorie(p.getCategorie());
		if (p.getCategorie() == null)
			setCategorieID(0);
		else
			setCategorieID(p.getCategorie().getId());
		if (p.getIllustration() == null)
			setIllustrationID(0);
		else
			setIllustrationID(p.getIllustration().getId());
		
		return SUCCESS;
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public String save() {
		// quand je sauvegarde un produit
		// l'objet model, en provenance du formulaire
		// ne contient PAS la categorie associée
		// je passe en deuxieme parametre l'identifiant de la
		// categorie a associer a ce produit
		// ce qui permet a save de récupérer l'entite categorie
		// correspondante
		produitDAO.save(getModel(), getCategorieID(), getIllustrationID());
		return SUCCESS;
	}
	
	public String remove() {
		produitDAO.remove(getModel().getId());
		return SUCCESS;
	}



	
}
