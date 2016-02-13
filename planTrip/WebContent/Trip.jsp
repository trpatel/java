<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Plan Your Trip</title>
</head>
<body>
<center>
<h1>Trip Planner</h1>
<br>
<form action="/planTrip/Trip" method="post">
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
</table>
</form>
<br><br>
<table border="2" cellspacing="2">
<tbody>
<tr><th align="center" valign="middle">Destination</th><th align="center" valign="middle">Anticipated Vacation</th>
	<th align="center" valign="middle">Total Cost</th><th align="center" valign="middle">Weekly Deposit</th>
	<th align="center" valign="middle">Weekly Withdrawal</th></tr>

<c:forEach items="${triplist.destinations}" var="loc">
<tr>
<td><c:out value="${loc.location}"/></td>
<td><c:out value="${loc.expect}"/></td>
<td><c:out value="${loc.tripCost}"/></td>
<td><c:out value="${loc.fund}"/></td>
<td><c:out value="${loc.withdrawal}"/></td>
</tr>
</c:forEach>

</tbody>
</table>
</center>
</body>
</html>