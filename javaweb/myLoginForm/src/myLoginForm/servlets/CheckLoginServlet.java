package myLoginForm.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckLoginServlet
 */
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, String> credentials;
	
    /**
     * Default constructor. 
     */
    public CheckLoginServlet() {
        // TODO Auto-generated constructor stub
    }
    
    

    // cette méthode est appelé automatiquement par tomcat
    // une fois apres la constrcution de la servlet
    // avec en parametre toute la configuration de celle-ci
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		credentials = new HashMap<String, String>();
		String[] logins = config.getInitParameter("logins").split(";");
		String[] passwords = config.getInitParameter("passwords").split(";");
		for (int i = 0; i < logins.length; i++) {
			credentials.put(logins[i], passwords[i]);
		}
	}

    private boolean check_credentials(String login, String password) {
    	if (credentials.containsKey(login) && credentials.get(login).equals(password)) 
    		return true;
    	else
    		return false;
    }

    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String password = request.getParameter("password");

		// si on ne recoit pas de nom, ou pas de mot de passe -> accès refusé
		if (nom == null || password == null) {
			getServletContext().getRequestDispatcher("/denied.jsp").forward(request, response);
		}
		else if (check_credentials(nom, password)) {
			getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
		}
		else {
			getServletContext().getRequestDispatcher("/denied.jsp").forward(request, response);
		}
	/*	// si le login et mot de passe sont correct -> accès autorisé
		else if (nom.equals("bob") && password.equals("1234")) {
			getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
		}
		// sinon accès refusé
		else {
			getServletContext().getRequestDispatcher("/denied.jsp").forward(request, response);
		}
		*/
		
	}

}
