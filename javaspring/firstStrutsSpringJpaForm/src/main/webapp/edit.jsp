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
<h2>edition messages</h2>
<s:form action="save" method="post">
<s:hidden name="id" />
<s:textfield name="titre" label="titre du message" />
<s:textfield name="corps" label="corps du message" />
<s:submit value="sauvegarder" />
</s:form>
</body>
</html>