package produitManagerForm.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import produitManagerForm.metier.Produit;

/**
 * Application Lifecycle Listener implementation class DataInitialiser
 *
 */
public class DataInitialiser implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DataInitialiser() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent arg0)  {
    	System.out.println("démarrage de la webapp");
    	ServletContext ctx = arg0.getServletContext();
    	List<Produit> produits = new ArrayList<>();
    	ctx.setAttribute("produits", produits);
    }
	
}
