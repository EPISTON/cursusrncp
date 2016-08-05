package bloggingForm.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest rq = (HttpServletRequest)request;
		String path = rq.getRequestURI();
		System.out.println("path = " + path );
		HttpSession session = rq.getSession();
		if (session.getAttribute("username") == null) {
			// nous ne somme pas loggué
			System.out.println("nous ne somme pas logués");
			if (!path.endsWith("/Login")) {
				System.out.println("tentative d'accès non authorisé");
				HttpServletResponse rp = (HttpServletResponse)response;
				rp.sendRedirect("Login");
				return;
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
