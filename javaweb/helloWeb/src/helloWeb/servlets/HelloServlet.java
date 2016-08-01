package helloWeb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupération des données en provenance du formulaire
		String nom = request.getParameter("nom");
		int age = Integer.parseInt(request.getParameter("age"));
		
		String message = "";
		if (age < 20) {
			message = "salut, " + nom + ", poke va bien";
		}
		else {
			message = "bonjour, " + nom + ", comment vas le dos";
		}
		// j'ajoute un attribut "message" a la requette
		request.setAttribute("message", message);
		
		// je veux passer le controle a une page JSP pour formater la réponse HTML
		getServletContext().getRequestDispatcher("/page1.jsp").forward(request, response);
		
	}

}
