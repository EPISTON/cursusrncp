<%@page import="bloggingForm.metier.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>HypeBlog.io, the new hype blog for you're life experience</title>
</head>
<body>
<h2>HypeBlog.io, the app</h2>
<a href="?tri=1">tri par titre</a>
<div id="listeposts">
<% 
List<Post> posts = (List<Post>)request.getAttribute("posts");
for (Post p : posts) {			
%>
<div class="blogpost">
	<h3 class="titre"><%= p.getTitre() %></h3>
	<p class="corps"><%= p.getCorps() %></p>
	<p class="infos">ecrit par <%= p.getAuteur() %> dans la categorie <%= p.getCategorie() %></p>
	<form method="post" action="PostEdit">
		<input type="hidden" 
			   name="id"
			   value="<%= p.getId() %>"/>
		<input type="submit" value="supprimer post" name="action" />
	</form>
	<form method="get" action="PostEdit">
		<input type="hidden" 
			   name="id"
			   value="<%= p.getId() %>"/>
		<input type="submit" value="editer post" name="action" />
	</form>
</div>
<%
}
%>
</div>

<form method="get" action="PostEdit">
	<input type="hidden" name="id" value="0">
	<input type="submit" name="action" value="creer post" />
</form>
</body>
</html>