<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="planTrip.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Plan Your Trip</title>
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
<h4>Welcome 
	${sessionScope.user.getUser()}
</h4>
<form action="/planTrip/TripServlet" method="post">
<table>
<tr>
<td>Trip Location:</td><td> <input type="text" name="location" size="25"></td>
</tr>
<br>
<tr>
<td>Trip Cost:</td><td><input type="text" name="tripCost" size="25"></td>
</tr>
<br>
<tr>
<td>Weekly Deposit:</td><td><input type="text" name="fund" size="25"></td>
</tr>
<br>
<tr>
<td>Weekly Withdrawal:</td><td><input type="text" name="withdrawal" size="25"></td>
</tr>
<br>
<tr>
<td align="center"><input type="submit" name="bttn" value="Add"></td>
<td align="center"><input type="submit" name="bttn" value="Delete"></td>
</tr>
<br>
</table>
</form>
<br><br>
<table border="2" cellspacing="2">
<tbody>
<tr><th align="center" valign="middle">Destination</th><th align="center" valign="middle">Anticipated Departure</th>
	<th align="center" valign="middle">Total Cost</th><th align="center" valign="middle">Weekly Deposit</th>
	<th align="center" valign="middle">Weekly Withdrawal</th></tr>

<c:forEach items="${triplist.destinations}" var="loc">
<tr>
<td><c:out value="${loc.location}"/></td>
<td><c:out value="${loc.expect}"/></td>
<td align="right"><c:out value="${loc.tripCost}"/></td>
<td align="right"><c:out value="${loc.fund}"/></td>
<td align="right"><c:out value="${loc.withdrawal}"/></td>
</tr>
</c:forEach>

</tbody>
</table>
</div>
</center>
</body>
</html>