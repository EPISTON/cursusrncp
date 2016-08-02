package webtodoForm.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webtodoForm.metier.Tache;

/**
 * Servlet implementation class TodoListServlet
 */
public class TodoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    // va afficher la page d'accueil avec la liste des taches
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// je récupere la todoliste
		List<Tache> taches = (List<Tache>)getServletContext()
										.getAttribute("taches");
		
		// ici, on fera le filtrage/tri/etc
		// pas implémenté pour l'instant...
		
		// je met la liste des taches a afficher dans la requette
		// pour que la page jsp puisse l'afficher
		request.setAttribute("taches", taches);
		
		// je demande a la page liste.jsp de faire l'affichage
		getServletContext().getRequestDispatcher("/liste.jsp")
							.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// je récupere les parametres du formulaire de creation de tache
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		int priorite = Integer.parseInt(request.getParameter("priorite"));
		// j'instancie une nouvelle tache avec ces parametres
		Tache newtache = new Tache(description, categorie, priorite);
		// ajoute a la liste des taches globale
		List<Tache> taches = (List<Tache>)getServletContext().getAttribute("taches");
		taches.add(newtache);
		
		// je rapelle doGet pour faire un affichage "classique" de ma liste de taches
		doGet(request, response);
	}

}
