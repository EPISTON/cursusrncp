<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>liste des encheres</title>
</head>
<body>
<h2>SuperEnchere.io</h2>
<table border="1">
<tr><th>ID</th><th>description</th><th>prixDepart</th><th>enchereMinimum</th><th>prixActuel</th></tr>
<s:iterator value="encheres" >
<tr>
<td><s:property value="id" /></td>
<td><s:property value="description" /></td>
<td><s:property value="prixDepart" /></td>
<td><s:property value="enchereMinimum" /></td>
<td><s:property value="prixActuel" /></td>
</tr>
</s:iterator>
</table>
</body>
</html>