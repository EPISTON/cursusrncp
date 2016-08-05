package bloggingForm.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bloggingForm.utils.PostDAO;

/**
 * Servlet implementation class PostListServlet
 */
public class PostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PostDAO postDAO;
	
    public PostListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		postDAO = (PostDAO)getServletContext().getAttribute("postDAO");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// je stocke un compteur de visite dans la session
		HttpSession session = request.getSession();
		/*if (session.getAttribute("compteur") != null) {
			Integer compteur = (Integer)session.getAttribute("compteur");
			compteur++;
			System.out.println("compteur = " + compteur);
			session.setAttribute("compteur", compteur);
		}
		else
			session.setAttribute("compteur", 1); // valeur initiale
		*/
		
		
		request.setAttribute("posts", postDAO.findAll());
		getServletContext().getRequestDispatcher("/liste.jsp")
							.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
