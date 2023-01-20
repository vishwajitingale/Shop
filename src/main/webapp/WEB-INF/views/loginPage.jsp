<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
</head>
<body align="center">
<h2 style="color:green;">----------{ Login }----------</h2>   
<%String msg=(String)request.getAttribute("msg");
if (msg !=null){out.println(msg);}%><br>

<center>
<form action="process" method="post" >  

<table align="center">
<tr>
<td>Email:</th>
<td><input type="text" required name="email"/></th>
</tr> 
<tr>
<td>Password:</th>
<td><input type="password" required name="password"/></th>
</tr>
</table>
<br>
<input type="submit" value="login"/>  
<a href="register" ><input type="button" value="register"></a>
</form>
</center>

</body>
</html>
