<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>liste des produits</title>
</head>
<body>
<h2>BioBio.io</h2>
<table border="1">
<tr>
<th>ID</th>
<th>nom</th>
<th>prix</th>
<th>poids</th>
<th>stock</th>
<th>categorie</th>
<th>actions</th></tr>
<s:iterator value="produits" >
<tr>
<td><s:property value="id" /></td>
<td><s:property value="nom" /></td>
<td><s:property value="prix" /></td>
<td><s:property value="poids" /></td>
<td><s:property value="stock" /></td>
<td><s:property value="categorie.libelle" /></td>

<td>
<s:form action="edit" method="post" theme="simple">
	<s:hidden name="id" /><s:submit value="editer" />	
</s:form>
<s:form action="delete" method="post" theme="simple">
	<s:hidden name="id" /><s:submit value="supprimer" />	
</s:form>
</td>
</tr>
</s:iterator>
</table>
<s:form action="create" method="post" theme="simple">
	<s:submit value="creation" />	
</s:form>

</body>
</html>