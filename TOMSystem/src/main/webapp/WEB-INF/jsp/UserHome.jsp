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
<select><option>Select Category</option></select>

<br>
<table border="1">
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

</body>
</html>