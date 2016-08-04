package produitManagerForm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class BDDListener
 *
 */
public class BDDListener implements ServletContextListener {

    public BDDListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/base_produitmanagerform",
					"root",
					"");
			System.out.println("connection a la base réussie!");
			
			// instanciation du produitDAO (auquel on fournit la connection a la base)
			ProduitDAO produitdao = new ProduitDAO(connection);
			sce.getServletContext().setAttribute("produitDAO", produitdao);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
}
