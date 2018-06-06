<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Multiplication Table ver 2.0</title>
</head>
<body>
	<table border='1'>
		<tr>
			<td></td>
			<c:forEach var="j" begin="1" end="9">
				<td>${j}</td>
			</c:forEach>
		</tr>

		<c:forEach var="i" begin="1" end="9">
			<tr>
				<td>${i}</td>
				<c:forEach var="j" begin="1" end="9">
					<td>${i*j}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>