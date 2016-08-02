package webtodoForm.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
		// je copie la liste des taches
		List<Tache> sortedList = new ArrayList<>(taches);
		
		String tri = request.getParameter("tri");
		tri = (tri == null)? "priorite" : tri;
		switch(tri) {
			case "priorite":
				// et je la tri par ordre inverse de priorité
				Collections.sort(sortedList,
								(t1, t2) -> ((Integer)t2.getPriorite()).compareTo(t1.getPriorite()));
				break;
			case "description":
				Collections.sort(sortedList,
						(t1, t2) -> t1.getDescription().compareTo(t2.getDescription()));
				break;
			case "categorie":
				Collections.sort(sortedList,
						(t1, t2) -> t1.getCategorie().compareTo(t2.getCategorie()));
				break;
		}
		
		
		// je met la liste des taches a afficher dans la requette
		// pour que la page jsp puisse l'afficher
		request.setAttribute("taches", sortedList);
		
		// je demande a la page liste.jsp de faire l'affichage
		getServletContext().getRequestDispatcher("/liste.jsp")
							.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("terminer")) {
			//suppression d'une tache
			// recuperation du nom de la tache a supprimer (champ hidden)
			String desc = request.getParameter("description");
			List<Tache> taches = (List<Tache>)getServletContext().getAttribute("taches");
			// je retire la tache avec la même description
			taches.removeIf(t -> t.getDescription().equals(desc));
		}
		else {
			// ajout d'une tache
			// je récupere les parametres du formulaire de creation de tache
			String description = request.getParameter("description");
			String categorie = request.getParameter("categorie");
			int priorite = Integer.parseInt(request.getParameter("priorite"));
			// j'instancie une nouvelle tache avec ces parametres
			Tache newtache = new Tache(description, categorie, priorite);
			// ajoute a la liste des taches globale
			List<Tache> taches = (List<Tache>)getServletContext().getAttribute("taches");
			taches.add(newtache);
		}
		/*
		// je rapelle doGet pour faire un affichage "classique" de ma liste de taches
		doGet(request, response);*/
		response.sendRedirect("todoList");
	}

}
