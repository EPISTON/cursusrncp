package bloggingForm.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		request.setAttribute("posts", postDAO.findAll());
		getServletContext().getRequestDispatcher("/liste.jsp")
							.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
