<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de test</title>

</head>
<body>
<h2>avant inclusion</h2>
<!--  jsp:include permet d'inclure une sous page dans ma propre page -->
<jsp:include page="hautdepage.jsp"/>
<h2>apres inclusion</h2>
<!-- <p><jsp:getProperty property="" name=""/></p> -->

</body>
</html>