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
import com.opensymphony.xwork2.ModelDriven;

public class IndexAction extends ActionSupport implements ModelDriven<Message>
{
	
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

	// cet attribut et son getter seront automatiquement appelé par struts2
	// car nous implémentons ModelDriven
	// l'objet message renvoyé servira a communiquer pour tout les champs d'un
	// message individuel
	private Message model;
	@Override
	public Message getModel() {
		if (model == null)
			model = new Message();
		return model;
	}



	public String index() {
		log.info("appel de index!");
		messages = messageDAO.findAll();
		return SUCCESS;
	}

	public String edit() {
		// j'essaye de charger le Message avec l'dientifiant en provenance de PARAM
		// celui-ci, normalement, a été injecté dans le model
		Message m = messageDAO.findByID(getModel().getId());
		if (m == null)
			return "notfound";
		
		getModel().setTitre(m.getTitre());
		getModel().setCorps(m.getCorps());
		return SUCCESS;
	}
	
	// pour editer un nouveau message
	// rien besoin de faire ici, sauf si on veu "pré-remplir" le formulaire
	public String create() {
		return SUCCESS;
	}
	
	// pour supprimer un message
	public String remove() {
		messageDAO.remove(getModel().getId());
		return SUCCESS;
	}
	
	public String save() {
		Message m = getModel();
		m = messageDAO.save(m);
		return SUCCESS;
	}
	
	

	
}
