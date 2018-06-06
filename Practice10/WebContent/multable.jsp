<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Multiplication Table</title>
</head>
<body>
<table border='1'>
		<tr>
			<td></td>
			<%
				for (int j = 1; j < 10; j++) {
			%>
			<td><%=j%></td>
			<%
				}
			%>
		</tr>

		<% for (int i = 1; i < 10; i++) { %>
			<tr>
				<td><%=i %></td>
				<% for (int j = 1; j < 10; j++) { %>
				<td><%=i*j %></td>
				<% } %>
			</tr>
			<% } %>
	</table>
</body>
</html>