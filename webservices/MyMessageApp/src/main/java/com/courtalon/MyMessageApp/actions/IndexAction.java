package com.courtalon.MyMessageApp.actions;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.myContactService.ws.ContactService;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private static Logger log = LogManager.getLogger(ActionSupport.class); 
	private static final long serialVersionUID = 1L;

	// le service externe auquel je veux acceder
	private ContactService contactService;
	public ContactService getContactService() {
		return contactService;
	}
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}


	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	public String index() {
		log.info("appel de index!");
		message = "bonjour depuis index le " + contactService.findByID(1).getNom();
		return SUCCESS;
	}

}
