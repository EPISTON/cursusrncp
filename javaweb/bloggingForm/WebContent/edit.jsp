<%@page import="bloggingForm.metier.Post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edition Post</title>
<link rel="stylesheet" type="text/css" href="style/bootstrap.css" />
<style type="text/css">
	form label {
		color: green;
	}
</style>
</head>
<body>
<% Post p = (Post)request.getAttribute("post"); %>

<div class="panel panel-primary margin-bottom-40">
	<div class="panel-heading">
		<h3 class="panel-title text-center">edition d'un post</h3>
	</div>
	<div class="panel-body">
		<form method="post" action="PostEdit" class="margin-bottom-40" role="form">
			<div class="form-group">
				<label for="titre">titre</label>
					<input class="form-control" type="text" name="titre" id="titre" value="<%= p.getTitre() %>"/><br />
			</div>
			<div class="form-group">
			<label for="corps">corps</label>
				<textarea class="form-control" name="corps" id="corps" rows="5" cols="50"><%= p.getCorps() %></textarea>
			</div>
			<div class="form-group">
			<label for="categorie">categorie</label>
				<input class="form-control" type="text" name="categorie" id="categorie" value="<%= p.getCategorie() %>"/><br />
			</div>
			<input type="hidden" name="auteur" id="auteur" value="<%= session.getAttribute("username") %>"/><br />
			<input type="hidden" name="id" value="<%= p.getId() %>" />
			<input class="btn btn-success" type="submit" value="sauvegarder" name="action" />
		</form>
	</div>
</div>


</body>
</html>