package com.courtalon.firstStrutsSpringJpaForm.actions;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.courtalon.firstStrutsSpringJpaForm.metier.Message;
import com.courtalon.firstStrutsSpringJpaForm.repository.IMessageDAO;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private static Logger log = LogManager.getLogger(ActionSupport.class); 
	private static final long serialVersionUID = 1L;

	// spring injectera automatique le bean DAO dont le nom correspond
	// grace a "autowire=byName"
	// ceci fonction car grace au plugin "struts-spring", c'est spring qui
	// initialise les action déclarées dans struts2
	// ATTENTION, toujours passer par l'interface, car sinon, spring ne peut
	// pas injecter les traitements liés au contexte de persistence (gestion transactions)
	private IMessageDAO messageDAO;
	public void setMessageDAO(IMessageDAO messageDAO) {this.messageDAO = messageDAO;}

	private List<Message> messages;
	public List<Message> getMessages() {return messages;}



	public String index() {
		log.info("appel de index!");
		messages = messageDAO.findAll();
		return SUCCESS;
	}

}
