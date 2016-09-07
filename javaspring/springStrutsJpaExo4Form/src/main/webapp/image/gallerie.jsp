<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cuicui.io</title>
<style type="text/css">
div.imagediv {
	position: relative;
	float: left;
	border: black solid 2px;
}
</style>
</head>
<body>
<h2>liste des images</h2>
<s:iterator value="illustrations">
<div class="imagediv">
	<img src="affiche/<s:property value='id' />" width="150" height="150"/>
</div>
</s:iterator>
<s:a action="/image/edit">nouvelle image</s:a>
</body>
</html>
