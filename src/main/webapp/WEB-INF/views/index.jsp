<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
<%@ page import="com.shop.model.Product" %>

<%
Object mail=session.getAttribute("userId");
if(mail==null){
	response.sendRedirect("login");
}
%>

<html>

<body align="center">
<h2>User :<%=mail %></h2>

<a href="list"><input type="button" value="Back"></a>
<a href="logout" ><input type="button" value="Logout"></a>

	
	<h2 style="color:green;">----------{ Insert New Product }----------</h2>
	<form action="saveProduct" method = "post">
		Product Id : <input type="text" name="id" placeholder="Enter Here" required><br><br>
		Product Name : <input type="text" name="name" placeholder="Enter here" required><br><br>
		Product Price : <input type="text" name="price" placeholder="Enter here" required><br><br>
		<button type="submit">Submit</button>
	</form>
	
</body>	

</html>
