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
<h2>boutique.io, edition Produit</h2>
<s:form action="produit_save" method="post">
<s:hidden name="id" />
<s:textfield name="nom" label="nom produit" />
<s:textfield name="prix" label="prix produit" />
<s:textfield name="poids" label="poids produit" />
<s:textfield name="stock" label="stock produit" />
<s:select list="categories"
		  name="categorieID"
		  listKey="id"
		  listValue="libelle"
		  label="categorie produit" />
<s:submit value="sauvegarder"/>
</s:form>
</body>
</html>