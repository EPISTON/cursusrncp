package webtodoForm.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import webtodoForm.metier.Tache;

/**
 * Application Lifecycle Listener implementation class TodoListInitialiser
 *
 */
public class TodoListInitialiser implements ServletContextListener {

    
    public TodoListInitialiser() {
        // TODO Auto-generated constructor stub
    }

    // m�thode est appel�e une fois a l'arret de la webapp
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    // m�thode est appel�e une fois au d�marrage de la webapp
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("d�marrage de la webapp");
    	// je r�cup�re le contexte de la webapp (objet "global" de notre application)
    	ServletContext ctx = arg0.getServletContext();
    	// je cree une todoliste vide
    	List<Tache> taches = new ArrayList<>();
    	// je stocke cette liste comme attribut du contexte de la webapp
    	// comme cela, elle est accessible de partout
    	ctx.setAttribute("taches", taches);
      }
	
}
