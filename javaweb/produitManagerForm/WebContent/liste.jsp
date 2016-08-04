<%@page import="produitManagerForm.metier.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>HypeProduct.bio, the new hype webapp for you're bio gluten free life</title>
</head>
<body>
<h2>HypeProduct.bio, the app</h2>
<a href="?tri=1">tri par nom</a>, <a href="?tri=2">tri par prix</a>, <a href="?tri=3">tri par poids</a>
<table border="1">
	<tr><th>nom</th><th>Prix</th><th>Poids</th><th>Stock</th><th>actions</th></tr>
<% 
List<Produit> produits = (List<Produit>)request.getAttribute("produits");
for (Produit p : produits) {			
%>
<tr>
	<td><%= p.getNom() %></td>
	<td><%= p.getPrix() %></td>
	<td><%= p.getPoids() %></td>
	<td><%= p.getStock() %></td>
	<td><form method="post" action="edition">
			<input type="hidden" 
				   name="id"
				   value="<%= p.getId() %>"/>
			<input type="submit" value="supprimer produit" name="action" />
		</form>
		<form method="get" action="edition">
			<input type="hidden" 
				   name="id"
				   value="<%= p.getId() %>"/>
			<input type="submit" value="editer produit" name="action" />
		</form>
	</td>
</tr>
<%
}
%>
</table>
<form method="get" action="edition">
	<input type="hidden" name="id" value="0">
	<input type="submit" name="action" value="creer produit" />
</form>
</body>
</html>