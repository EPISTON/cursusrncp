package myLoginForm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckLoginServlet
 */
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CheckLoginServlet() {
        // TODO Auto-generated constructor stub
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
		// si le login et mot de passe sont correct -> accès autorisé
		else if (nom.equals("bob") && password.equals("1234")) {
			getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
		}
		// sinon accès refusé
		else {
			getServletContext().getRequestDispatcher("/denied.jsp").forward(request, response);
		}
		
		
	}

}
