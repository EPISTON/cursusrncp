<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscrit toi!</title>
</head>
<body>
<h2>coolPeople.io</h2>
<s:form action="saveContact" method="post">
	<s:textfield name="nom" label="ton nom" />
	<s:textfield name="prenom" label="ton prenom" />
	<s:textfield name="age" label="ton age" />
	<s:submit />
</s:form>
</body>
</html>