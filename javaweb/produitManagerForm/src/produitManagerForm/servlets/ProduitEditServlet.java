package produitManagerForm.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import produitManagerForm.metier.Produit;

/**
 * Servlet implementation class ProduitEditServlet
 */
public class ProduitEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduitEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Produit> produits = (List<Produit>)getServletContext().getAttribute("produits");
		// afficher le formulaire de création/edition d'un produit
		String action = request.getParameter("action");
		switch(action) {
			case "creer produit":
				Produit p = new Produit(0, "", 0.0, 0.0, 0);
				// je passe un nouveau produit "vide" au formulaire d'edition
				request.setAttribute("produit", p);
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
				break;
			case "editer produit":
				Produit p2 = produits.stream()
									.filter(
										prod -> prod.getId() == Integer.parseInt(request.getParameter("id")))
									.findFirst().get();
				request.setAttribute("produit", p2);
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
				break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Produit> produits = (List<Produit>)getServletContext().getAttribute("produits");

		String action = request.getParameter("action");
		// si pas d'action, on revient a la liste
		if (action == null)
			response.sendRedirect("");
		
		switch(action) {
			case "sauvegarder":
				Produit p = new Produit(
						Integer.parseInt(request.getParameter("id")),
						request.getParameter("nom"),
						Double.parseDouble(request.getParameter("prix")),
						Double.parseDouble(request.getParameter("poids")),
						Integer.parseInt(request.getParameter("stock"))
						);
				// si on n'a pas d'id (id == 0), c'est un nouveau produit
				if (p.getId() == 0) {
					// ajout du nouveau produit
					
					// prendre le plus grand identifiant connu, et ajouter 1
					// car cet id est "libre"
					int max = 0;
					for(Produit p1 : produits)
						if (p1.getId() > max)
							max = p1.getId();
					p.setId(max + 1);
					// ajout du produit a la liste
					produits.add(p);
				}
				else {
					// produit déjà existant, il faut le mettre a jour
					Produit p2 = produits.stream()
								.filter(
								prod -> prod.getId() == p.getId())
							.findFirst().get();
					// p2 -> le produit a mettre a jour
					// p -> le produit saisie par l'utilisateur
					// mise a jour de p2 avec le contenu de p
					p2.setNom(p.getNom());
					p2.setPoids(p.getPoids());
					p2.setPrix(p.getPrix());
					p2.setStock(p.getStock());
				}
		}
		response.sendRedirect("/produitManagerForm/");
	}

}
