<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edition enchere</title>
</head>
<body>
<h2>SuperEnchere.io</h2>
<s:form action="saveMD" method="post">
<s:hidden name="id"/>
<s:textfield name="description" label="description"/>
<s:textfield name="prixDepart" label="prix initial"/>
<s:textfield name="enchereMinimum" label="enchere minimum"/>
<s:textfield name="prixActuel" label="prix actuel"/>
<s:submit value="sauver"/>
</s:form>
</body>
</html>