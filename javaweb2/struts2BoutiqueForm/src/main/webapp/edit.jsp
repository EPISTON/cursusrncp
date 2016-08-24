<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edition Produit</title>
</head>
<body>
<h2>BioBio.io</h2>
<s:form action="save" method="post">
<s:hidden name="id"/>
<s:textfield name="nom" label="nom produit"/>
<s:textfield name="prix" label="prix produit"/>
<s:textfield name="poids" label="poids produit"/>
<s:textfield name="stock" label="stock produit"/>
<s:select list="categories" name="categorieId" listKey="id" listValue="libelle" />
<s:submit value="sauver"/>
</s:form>
</body>
</html>