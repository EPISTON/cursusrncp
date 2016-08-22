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
	
	private List<Enchere> encheres;
	public List<Enchere> getEncheres() {
		return encheres;
	}

	public String liste() {
		encheres = enchereDAO.findAll();
		return SUCCESS;
	}
	
	@Override
	public void setServletContext(ServletContext ctx) {
		enchereDAO = (EnchereDAO)ctx.getAttribute(BDDListener.ENCHERE_DAO);

	}

}
