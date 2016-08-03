<%@page import="produitManagerForm.metier.Produit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edition Produit</title>
</head>
<body>
<% Produit p = (Produit)request.getAttribute("produit"); %>
<form method="post" action="edition">
	<label for="nom">nom</label>
		<input type="text" name="nom" id="nom" value="<%= p.getNom() %>"/><br />
	<label for="prix">prix</label>
		<input type="text" name="prix" id="prix" value="<%= p.getPrix() %>"/><br />
	<label for="poids">poids</label>
		<input type="text" name="poids" id="poids" value="<%= p.getPoids() %>"/><br />
	<label for="stock">stock</label>
		<input type="text" name="stock" id="stock" value="<%= p.getStock() %>"/><br />
	<input type="hidden" name="id" value="<%= p.getId() %>" />
	<input type="submit" value="sauvegarder" name="action" />
</form>



</body>
</html>