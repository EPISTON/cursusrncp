package com.courtalon.firstStruts2Form.actions;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private static Logger log = LogManager.getLogger(ActionSupport.class); 
	private static final long serialVersionUID = 1L;

	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	// toutes les methodes d'actions de struts2
	// c'est a dire une méthode qui sera appelé pour gerer la réponse a une requette (voire struts.xml)
	// on pour signature pas d'argument et une valeur de retour String
	// cette valeur de retour est ce qu'on appel un resultat en struts2: "result"
	public String index() {
		log.info("appel de index!");
		message = "bonjour depuis index le " + new Date();
		return SUCCESS;
	}

}
