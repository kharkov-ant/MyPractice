<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Part3</title>
</head>
<body>
	<form method="POST" action="AddName">
		<input name="name" type="text" /> <input type="submit" value="ok" />
	</form>
	<br>
	<c:forEach items="${sessionScope.names}" var="name">
		<c:out value="${name}" />
		<br>
	</c:forEach>
	<br />
	<form method="POST" action="Delete">
		<input type="submit" value="remove" />
	</form>
</body>
</html>