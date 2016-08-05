<%@page import="bloggingForm.metier.Post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edition Post</title>
</head>
<body>
<% Post p = (Post)request.getAttribute("post"); %>
<form method="post" action="PostEdit">
	<label for="titre">titre</label>
		<input type="text" name="titre" id="titre" value="<%= p.getTitre() %>"/><br />
	<label for="corps">corps</label>
		<textarea name="corps" id="corps" rows="5" cols="50"><%= p.getCorps() %></textarea>
	<label for="categorie">categorie</label>
		<input type="text" name="categorie" id="categorie" value="<%= p.getCategorie() %>"/><br />
	<label for="auteur">auteur</label>
		<input type="text" name="auteur" id="auteur" value="<%= p.getAuteur() %>"/><br />
	<input type="hidden" name="id" value="<%= p.getId() %>" />
	<input type="submit" value="sauvegarder" name="action" />
</form>



</body>
</html>