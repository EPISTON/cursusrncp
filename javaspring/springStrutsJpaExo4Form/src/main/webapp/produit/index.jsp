<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Boutique.io</title>
</head>
<body>
<h2>boutique.io</h2>
<s:a action="produit/index">liste des produits</s:a>
<s:a action="categorie/index">liste des categories</s:a>
<table border="1">
<tr><th>Image</th><th>nom</th><th>prix</th><th>poids</th><th>stock</th><th>categorie</th><th>actions</th></tr>
<s:iterator  value="produits" >
<tr>
	<td>
		<img src="../image/affiche/<s:property value="illustration.id" />" width="64" />
	</td>
	<td><s:property value="nom" /></td>
	<td><s:property value="prix" /></td>
	<td><s:property value="poids" /></td>
	<td><s:property value="stock" /></td>
	<td><s:a action="produit/filter/%{categorie.id}" ><s:property value="%{categorie.libelle}" /></s:a></td>
	<td>
	<s:a action="produit/edit/%{id}">edition</s:a>
	<s:a action="produit/remove/%{id}" onclick="return confirm('etes vous sur?');" >suppression</s:a>
	</td>	
</tr>
</s:iterator>
</table>
<s:a action="produit/create">creation nouveau produit</s:a>
</body>
</html>