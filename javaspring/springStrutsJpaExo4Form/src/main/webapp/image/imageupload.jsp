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
<h2>upload image</h2>
<s:form action="image/save" method="post" enctype="multipart/form-data">
<s:textfield name="description" label="description image" />
<s:file name="image" label="fichier image"/>
<s:submit value="uploader" />
</s:form>
</body>
</html>