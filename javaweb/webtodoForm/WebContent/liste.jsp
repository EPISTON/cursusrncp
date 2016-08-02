<%@page import="webtodoForm.metier.Tache"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TodoList.io, the new hype webapp for you're life</title>
</head>
<body>
<h2>Todolist.io, the app</h2>
<a href="todoList?tri=categorie">tri categorie</a>
<a href="todoList?tri=description">tri description</a>
<a href="todoList?tri=priorite">tri priorite</a>
<table border="1">
	<tr><th>Description</th><th>Categorie</th><th>Priorité</th><th>actions</th></tr>
<% 
List<Tache> taches = (List<Tache>)request.getAttribute("taches");
for (Tache t : taches) {			
%>
<tr>
	<td><%= t.getDescription() %></td>
	<td><%= t.getCategorie() %></td>
	<td><%= t.getPriorite() %></td>
	<td><form method="post">
			<input type="hidden" 
				   name="description"
				   value="<%= t.getDescription()%>"/>
			<input type="submit" value="terminer" name="action" />
		</form>
	</td>
</tr>
<%
}
%>
</table>
<form method="post">
	<label for="description">description</label> <input type="text" name="description" id="description" /><br />
	<label for="categorie">categorie</label> <input type="text" name="categorie" id="categorie"/><br />
	<label for="priorite">priorite</label> <select name="priorite" id="priorite">
				<option value="1" >faible</option>
				<option value="2" >moyenne</option>
				<option value="3" >haute</option>
				<option value="4" >critique</option>
			  </select><br />
	<input type="submit" value="ajouter" />
</form>

</body>
</html>