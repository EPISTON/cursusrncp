package com.courtalon.struts2EnchereForm.actions;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.courtalon.struts2EnchereForm.metier.Enchere;
import com.courtalon.struts2EnchereForm.utils.BDDListener;
import com.courtalon.struts2EnchereForm.utils.EnchereDAO;
import com.opensymphony.xwork2.ActionSupport;

public class EnchereAction extends ActionSupport implements ServletContextAware {
	private EnchereDAO enchereDAO;
	
	// la liste des encheres
	private List<Enchere> encheres;
	public List<Enchere> getEncheres() {
		return encheres;
	}

	// champ du formulaire pour une enchere
	// sert a communiquer dans les 2 sens avec le formulaire
	//		 a l'affichage action -> jsp (get)
	//		a la reception formulaire -> action (set)
	private int id;
	private String description;
	private double prixDepart;
	private double enchereMinimum;
	private double prixActuel;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public double getPrixDepart() {return prixDepart;}
	public void setPrixDepart(double prixDepart) {this.prixDepart = prixDepart;}
	public double getEnchereMinimum() {return enchereMinimum;}
	public void setEnchereMinimum(double enchereMinimum) {this.enchereMinimum = enchereMinimum;}
	public double getPrixActuel() {return prixActuel;}
	public void setPrixActuel(double prixActuel) {this.prixActuel = prixActuel;}

	public String liste() {
		encheres = enchereDAO.findAll();
		return SUCCESS;
	}
	
	public String edit() {
		// retrouver l'enchere a editer
		Enchere enchere = enchereDAO.findById(getId());
		if (enchere == null)
			return "notfound"; // enchere inconnue, rien a editer
		setDescription(enchere.getDescription());
		setPrixDepart(enchere.getPrixDepart());
		setEnchereMinimum(enchere.getEnchereMinimum());
		setPrixActuel(enchere.getPrixActuel());
		return SUCCESS;
	}
	
	public String create() {
		// retrouver l'enchere a editer
		setId(0);
		setDescription("");
		setPrixDepart(10.0);
		setEnchereMinimum(1.0);
		setPrixActuel(10.0);
		return SUCCESS;
	}
	
	public String save() {
		Enchere enchere = new Enchere(getId(),
										getDescription(),
										getPrixDepart(),
										getEnchereMinimum(),
										getPrixActuel());
		if (getPrixActuel() < getPrixDepart()) {
			addFieldError("prixActuel", "le prix actuel ne peut etre inférieur au prix de depart");
			return INPUT;			
		}
		enchereDAO.save(enchere);
		return SUCCESS;
	}
	
	public String delete() {
		enchereDAO.deleteOne(getId());
		return SUCCESS;
	}
	
	public String encherir() {
		// retrouver l'enchere a encherir
		Enchere enchere = enchereDAO.findById(getId());
		if (enchere == null)
			return "notfound"; // enchere inconnue, rien a encherir
		// j'encheri sur l'enchere
		enchere.setPrixActuel(enchere.getPrixActuel() + enchere.getEnchereMinimum());
		// je sauvegarde l'enchere encheri
		enchereDAO.save(enchere);
		return SUCCESS;
	}
	
	@Override
	public void setServletContext(ServletContext ctx) {
		enchereDAO = (EnchereDAO)ctx.getAttribute(BDDListener.ENCHERE_DAO);

	}

}
