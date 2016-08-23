package com.courtalon.struts2EnchereForm.actions;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;

import com.courtalon.struts2EnchereForm.metier.Enchere;
import com.courtalon.struts2EnchereForm.utils.BDDListener;
import com.courtalon.struts2EnchereForm.utils.EnchereDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// j'implement ModelDriven, ici pour les enchere
public class EnchereActionMD extends ActionSupport implements
						ServletContextAware, ModelDriven<Enchere> {

	private Logger log = LogManager.getLogger(EnchereActionMD.class);
	private EnchereDAO enchereDAO;

	private Enchere model;
	@Override
	public Enchere getModel() {
		if (model == null)
			model = new Enchere();
		log.info("appel de getModel()");
		return model;
	}

	// la liste des encheres
	private List<Enchere> encheres;
	public List<Enchere> getEncheres() {
		return encheres;
	}
	
	public String liste() {
		log.info("appel de liste");
		encheres = enchereDAO.findAll();
		return SUCCESS;
	}
	
	public String edit() {
		log.info("appel de edit");
		// retrouver l'enchere a editer
		Enchere enchere = enchereDAO.findById(getModel().getId());
		if (enchere == null)
			return "notfound"; // enchere inconnue, rien a editer
		getModel().setDescription(enchere.getDescription());
		getModel().setPrixDepart(enchere.getPrixDepart());
		getModel().setEnchereMinimum(enchere.getEnchereMinimum());
		getModel().setPrixActuel(enchere.getPrixActuel());
		return SUCCESS;
	}
	
	public String create() {
		log.info("appel de create");
		// retrouver l'enchere a editer
		getModel().setPrixDepart(10);
		getModel().setEnchereMinimum(1.0);
		getModel().setPrixActuel(10);
		return SUCCESS;
	}
	
	public String save() {
		log.info("appel de save");
		Enchere enchere = getModel();
		if (enchere.getPrixActuel() < enchere.getPrixDepart()) {
			addFieldError("prixActuel", "le prix actuel ne peut etre inférieur au prix de depart");
			return INPUT;			
		}
		enchereDAO.save(enchere);
		return SUCCESS;
	}
	
	public String delete() {
		enchereDAO.deleteOne(getModel().getId());
		return SUCCESS;
	}
	
	public String encherir() {
		// retrouver l'enchere a encherir
		Enchere enchere = enchereDAO.findById(getModel().getId());
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
