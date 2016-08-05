<%@page import="bloggingForm.metier.Post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
</head>
<body>
<form method="post" action="Login">
	<label for="login">login</label>
	<input  type="text" name="login" id="login" value=""/><br />
	<input type="submit" value="logmein" name="action" />
</form>
</body>
</html>