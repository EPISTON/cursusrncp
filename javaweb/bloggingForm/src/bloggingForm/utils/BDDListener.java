package bloggingForm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
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
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	ServletContext ctx = arg0.getServletContext();
    	
    	try {
			Class.forName(ctx.getInitParameter("bddDriverClass"));
			Connection connection = DriverManager.getConnection(
					ctx.getInitParameter("bddUrl"),
					ctx.getInitParameter("bddLogin"),
					ctx.getInitParameter("bddpassword"));
			
			PostDAO postdao = new PostDAO(connection);
			ctx.setAttribute("postDAO", postdao);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
	
}
