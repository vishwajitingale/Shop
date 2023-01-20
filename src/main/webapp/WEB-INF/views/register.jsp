<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>
<body align="center">
<h2 style="color:green;">----------{ Registration Form }----------</h2>
<%String msg=(String)request.getAttribute("msg");
if(msg != null){
	out.println(msg);}
%><br>

<%String name=(String)request.getAttribute("uName");
 String emailId=(String)request.getAttribute("mail");
%>

<form method="post" action="save" >

    Name 
    <input type="text" name="fname" required  value="<%if(name!=null){
    out.println(name);}%>" /><br/><br/>

    Email
    <input type="text" name="email" required value="<%if(emailId!=null){
    out.println(emailId);}%>"/> <br/><br/>
    
    Password
    <input type="password" name="pass" required /> <br/><br/>

    Confirm Password 
    <input type="password" name="rpass" required /> <br/>

<br/>
    <input type="submit" value="Submit" />                                    
</form>  
<br/><br/>
<div>If you are already registered</div>
<br>
<a href="login" ><input type="button" value="Go to Login"></a>

</body>
</html>