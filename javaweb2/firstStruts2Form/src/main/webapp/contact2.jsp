<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscrit toi!</title>
<style type="text/css">
	.errorLabel { 
		color: red;
		font-weight: bold;
	}
	.errorMessage { color: red;}
	.errorField {
		background-color: pink;
	}
</style>
</head>
<body>
<h2>coolPeople.io</h2>
<s:actionerror cssErrorClass="errorMessage"/>
<s:form action="saveContact2" method="post" theme="simple">
	<s:fielderror  fieldName="nom"/><s:textfield name="nom" label="ton nom" cssErrorClass="errorField" /><br />
	<s:fielderror  fieldName="prenom"/><s:textfield name="prenom" label="ton prenom" cssErrorClass="errorField"/><br />
	<s:fielderror  fieldName="age"/><s:textfield name="age" label="ton age" cssErrorClass="errorField"/><br />
	<s:submit />
</s:form>
</body>
</html>