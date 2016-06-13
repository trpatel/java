<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Plan Your Trip - Login</title>
<style type="text/css">
	.page{ background-color: rgb(230,230,250); margin-top:-20px;  }
	.headtop{
				height:43px;
				width:100%;
				margin-top:-8px;
				margin-left:-10px;
				position:fixed;
				background-color:rgb(50, 50, 50);
	}
	.section{
				color:white;
				text-decoration:none;
				margin:50px;
				font-size:30px;
	}
	.body{ width:960px; height:1000px; padding-top:15px; background-color: white; }
</style>
</head>
<body class="page">
<center>
<div class="headtop">
	<table><tr>
		<td><a href="/planTrip/Trip.jsp" class="section">Home</a></td>
		<td><a href="/planTrip/Contact.jsp" class="section">Contact</a></td>
		<td><a href="/planTrip/Login.jsp" class="section">Login</a></td>
		<td><a href="/planTrip/Logout.jsp" class="section">Logout</a></td>
	</tr></table>
</div>
<div class="body">
<h1>Trip Planner</h1>
	<form action="/planTrip/LoginServlet" method="get">
		<table>
			<tr><td>User ID:</td><td><input type="text" name="txtUserID" size="20"></td></tr>
			<tr><td>Password:</td><td><input type="text" name="txtPassword" size="20"></td></tr>
			<tr><td></td><td align="center"><input type="submit" name="bttn" value="Login"></td></tr>
		</table>
	</form>
</div>
</center>
</body>
</html>