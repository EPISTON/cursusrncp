#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.actions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ${package}.metier.*;
import ${package}.repositories.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategorieAction extends ActionSupport implements ModelDriven<Categorie>
{
	private static Logger log = LogManager.getLogger(CategorieAction.class); 
	private static final long serialVersionUID = 1L;

	private ICategorieDAO categorieDAO;

	public void setCategorieDAO(ICategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}

	private Categorie model;
	@Override
	public Categorie getModel() {
		if (model == null)
			model = new Categorie();
		return model;
	} 
	
	private List<Categorie> categories;

	public List<Categorie> getCategories() {
		return categories;
	}
	
	public String index() {
		categories = categorieDAO.findAll();
		return SUCCESS;
	}
	
	public String edit() {
		Categorie c = categorieDAO.findByID(getModel().getId());
		if (c == null)
			return "notfound";
		getModel().setLibelle(c.getLibelle());
		return SUCCESS;
	}
	public String create() {
		return SUCCESS;
	}
	public String save() {
		categorieDAO.save(getModel());
		return SUCCESS;
	}
	public String remove() {
		categorieDAO.remove(getModel().getId());
		return SUCCESS;
	}
	
}
