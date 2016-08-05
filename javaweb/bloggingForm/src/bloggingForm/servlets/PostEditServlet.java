package bloggingForm.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bloggingForm.metier.Post;
import bloggingForm.utils.PostDAO;


/**
 * Servlet implementation class PostEditServlet
 */
public class PostEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO postDAO;
	
    public PostEditServlet() {
        super();
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		postDAO = (PostDAO)getServletContext().getAttribute("postDAO");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		switch(action) {
			case "creer post":
				Post p = new Post(0, "", "", "", "");
				// je passe un nouveau produit "vide" au formulaire d'edition
				request.setAttribute("post", p);
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
				break;
			case "editer post":
				Post p2 = postDAO.findByID(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("post", p2);
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// si pas d'action, on revient a la liste
		if (action == null)
			response.sendRedirect("PostList");
		
		switch(action) {
			case "sauvegarder":
				Post p = new Post(
						Integer.parseInt(request.getParameter("id")),
						request.getParameter("titre"),
						request.getParameter("corps"),
						request.getParameter("categorie"),
						request.getParameter("auteur")
						);
				postDAO.save(p);
				break;
			case "supprimer post":
				postDAO.deleteOne(Integer.parseInt(request.getParameter("id")));
			}
		response.sendRedirect("PostList");
		
	}

}
