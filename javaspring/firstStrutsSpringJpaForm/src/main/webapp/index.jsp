<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cuicui.io</title>
</head>
<body>
<h2>liste des messages</h2>
<table border="1">
<tr><th>ID</th><th>titre</th><th>corps</th></tr>
<s:iterator  value="messages" >
<tr>
	<td><s:property value="id" /></td>
	<td><s:property value="titre" /></td>
	<td><s:property value="corps" /></td>	
</tr>
</s:iterator>
</table>
</body>
</html>