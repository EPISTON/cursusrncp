<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ma page jsp</title>
</head>
<body>
<h1><%= request.getAttribute("message") %></h1>
<ul>
<%
	for (int i = 1; i < 10; i++) {
		out.println("<li>" + i + "</li>");
	}
%>
</ul>
</body>
</html>