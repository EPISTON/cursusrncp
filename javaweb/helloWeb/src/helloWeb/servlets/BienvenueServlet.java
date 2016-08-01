package helloWeb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BienvenueServlet
 */
public class BienvenueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BienvenueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// je vais renvoyer du html
		response.setContentType("text/html");
		// on récupere un writer dans notre réponse
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><title>bienvenue</title></head>");
		pw.println("<body>");
		pw.println("<h2>vive la choucroute</h2>");
		pw.println("<h3> fait le " + new Date() + " </h3>");
		pw.println("</body></html>");
		pw.close();
	}

}
