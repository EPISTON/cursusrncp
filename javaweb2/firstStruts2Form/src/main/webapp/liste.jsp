<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>liste des message</title>
</head>
<body>
<h2>coolMessage.io</h2>
<table border="1">
<tr><th>ID</th><th>titre</th><th>corps</th></tr>
<s:iterator value="messages" >
<tr>
<td><s:property value="id" /></td>
<td><s:property value="titre" /></td>
<td><s:property value="corps" /></td>
</tr>
</s:iterator>
</table>
</body>
</html>