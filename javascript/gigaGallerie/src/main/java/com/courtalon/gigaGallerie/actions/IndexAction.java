package com.courtalon.gigaGallerie.actions;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private static Logger log = LogManager.getLogger(IndexAction.class); 
	private static final long serialVersionUID = 1L;

	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	public String index() {
		log.info("appel de index!");
		message = "bonjour depuis index le " + new Date();
		return SUCCESS;
	}

}
