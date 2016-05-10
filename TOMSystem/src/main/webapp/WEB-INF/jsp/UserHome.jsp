<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TOM</title>
</head>
<body>
<h1>Welcome User</h1>

<form action="displayItems" method="get">

List<String> item = new ArrayList<String>();
item.add("drinks");
item.add("app");
item.add("")
request.setAttribute("startTimeList", startTimeList);

<select name="item"><option>Select Category</option>
		<option name="drinks">Drinks</option>
		<option name="app">Appetisers</option>
		<option>Main Course</option>
		<option>Desserts</option>
</select>
<%
    String Selectedtype = request.getParameter("item");
    

%>    

	<button type="submit" name="action" commandName = "item">Get Items</button>
    </form>


<br>
<td> Selectedtype </td>
<table border="1">
	<th>ID</th>
	<th>Category</th>
	<th>Name</th>
	<th>Picture</th>
	<th>Calories</th>
	<th>Unit Price</th>
	<th>Prep Time</th>
	<c:forEach items="${drinkList}" var="item">
		<tr>
			<td>${item.id}</td>
			<td>${item.category}</td>
			<td>${item.name}</td>
			<td>${item.picture}</td>
			<td>${item.calories}</td>
			<td>${item.unit_price}</td>
			<td>${item.prep_time}</td>
		</tr>
	</c:forEach>
</table>



<!--  <table border="1">
	<th>ID</th>
	<th>Category</th>
	<th>Name</th>
	<th>Picture</th>
	<th>Calories</th>
	<th>Unit Price</th>
	<th>Prep Time</th>
	<c:forEach items="${ItemList}" var="item">
		<tr>
			<td>${item.id}</td>
			<td>${item.category}</td>
			<td>${item.name}</td>
			<td>${item.picture}</td>
			<td>${item.calories}</td>
			<td>${item.unit_price}</td>
			<td>${item.prep_time}</td>
		</tr>
	</c:forEach>
</table>
-->

</body>
</html>