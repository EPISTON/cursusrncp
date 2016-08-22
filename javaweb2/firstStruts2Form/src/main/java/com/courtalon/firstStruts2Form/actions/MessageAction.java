package com.courtalon.firstStruts2Form.actions;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.courtalon.firstStruts2Form.metier.Message;
import com.courtalon.firstStruts2Form.utils.MessageDAO;
import com.opensymphony.xwork2.ActionSupport;

public class MessageAction extends ActionSupport implements ServletContextAware
{
	private static final long serialVersionUID = 1L;

	private MessageDAO messageDAO;
	
	// cette propriété sera automatiquement transimise
	// par stuts2 a la page jsp (via la ValueStack)
	// du moment que je fournit un getter publique
	private List<Message> messages;
	public List<Message> getMessages() {return messages;}

	public String liste() {
		// ma methode d'action doit préparer les donnée pour la vue (page jsp)
		// ici, on veu la liste des message pour les afficher
		// je les requettes avec le DAO, et le fourni dans ma propriété "messages"
		this.messages = messageDAO.findAll();
		return SUCCESS;
	}

	// cette méthode est appelée automatiquement par struts2 avec le servlet context en parametre
	@Override
	public void setServletContext(ServletContext ctx) {
		// je récupère le DAO depuis le servlet context
		// celui-ci avaiut été préparé par le BDDListener
		this.messageDAO = (MessageDAO)ctx.getAttribute("messageDAO");
		
	}
	
	
}
