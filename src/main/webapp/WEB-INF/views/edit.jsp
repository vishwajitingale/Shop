<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.shop.model.Product" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <%
Object mail=session.getAttribute("userId");
if(mail==null){
	response.sendRedirect("login");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Data</title>
</head>
<body align="center">
<h2>User :<%=mail %></h2>

<a href="list"><input type="button" value="Back"></a>
<a href="home"><input type="button" value="Add New Product"></a>
<a href="logout" ><input type="button" value="Logout"></a>

<br>
	<h2 style="color:green;">----------{ Edit Product Details }----------</h2>
	
	<%
	Product product=(Product)request.getAttribute("productObj");
	%>
	
	<form action="update">
	Product Id : <input type="text" name="productId" readonly value="<%=product.getId()%>"><br><br>
	Product Name : <input type="text" name="productName" value=<%=product.getName()%>><br><br>
	Set New Price :<input type="number" name="productPrice" value="<%=product.getPrice()%>" ><br><br>
	<button type="submit">Submit</button>
	</form>
</body>
</html>