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
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">

<h2>User :<%=mail %></h2>
<a href="home"><input type="button" value="Add New Product"></a>
<a href="logout" ><input type="button" value="Logout"></a>

<%
 List<Product> list=(List<Product>)request.getAttribute("productList");
%>
<h2 style="color:green;">----------{ Products List }----------</h2>

<table align="center">
 <thead>
 	<tr>
 	<th><h3>Product Id</h3></th>
 	<th><h3>Product Name</h3></th>
 	<th><h3>Product Price</h3></th>
 	<th><h3>Action</h3></th>
 	</tr>
 </thead>
 
 <tbody>

<%
for(Product s:list){
	%>
	<tr>
		<td><%out.println(s.getId()); %></th>
		<td><%out.println(s.getName()); %></th>
		<td><%out.println(s.getPrice()); %></th>
		<td>
			<a href="edit?id=<%out.println(s.getId()); %>" ><input type="button" value="Edit" ></a>
			<a href="delete?id=<%out.println(s.getId()); %>"><input type="button" value="Delete" style="color:red;" ></a>
		</th>
	</tr>
	<%
}
%>
	
	</tbody>
</table>
<br>
<br>
	<a href="saveData"><input type="button" value="Download"></a>
	
	<%String message=(String)request.getAttribute("msg");
	if(message != null){
		out.println(message);
	}
	%>

</body>
</html>