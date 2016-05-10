<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TOM</title>
<style>
body {
    background: url('http://res.cloudinary.com/grubhub-marketing/image/upload/v1460472623/Marketing%20assets/2016-MarchSeasonal-Desktop-1200by550px-GH.jpg') no-repeat fixed center center;
    background-size: cover;
    font-family: Montserrat;
}

.logo {
    width: 500px;
    height: 200px;
    background: url('http://www.designbolts.com/wp-content/uploads/2012/09/Cool-Creative-Food-Company-Logo-ideas-19.jpg') no-repeat;
    margin: 30px auto;
}

.login-block {
    width: 1000px;
    padding: 20px;
    background: #fff;
    border-radius: 5px;
    border-top: 5px solid #ff656c;
    margin: 0 auto;
    
}

.login-block h1 {
    text-align: center;
    color: #000;
    font-size: 18px;
    text-transform: uppercase;
    margin-top: 0;
    margin-bottom: 20px;
}

.login-block input {
    width: 100%;
    height: 42px;
    box-sizing: border-box;
    border-radius: 5px;
    border: 1px solid #ccc;
    margin-bottom: 20px;
    font-size: 14px;
    font-family: Montserrat;
    padding: 0 20px 0 50px;
    outline: none;
}

.login-block input#username {
    background: #fff url('http://i.imgur.com/u0XmBmv.png') 20px top no-repeat;
    background-size: 16px 80px;
}

.login-block input#username:focus {
    background: #fff url('http://i.imgur.com/u0XmBmv.png') 20px bottom no-repeat;
    background-size: 16px 80px;
}

.login-block input#password {
    background: #fff url('http://i.imgur.com/Qf83FTt.png') 20px top no-repeat;
    background-size: 16px 80px;
}

.login-block input#password:focus {
    background: #fff url('http://i.imgur.com/Qf83FTt.png') 20px bottom no-repeat;
    background-size: 16px 80px;
}

.login-block input:active, .login-block input:focus {
    border: 1px solid #ff656c;
}

.login-block button {
    width: 100%;
    height: 40px;
    background: #ff656c;
    box-sizing: border-box;
    border-radius: 5px;
    border: 1px solid #e15960;
    color: #fff;
    font-weight: bold;
    text-transform: uppercase;
    font-size: 14px;
    font-family: Montserrat;
    outline: none;
    cursor: pointer;
}

.login-block button:hover {
    background: #ff7b81;
}

table, td, th {    
    border: 1px solid #ff656c;
    text-align: left;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 15px;

.styled-select select {
   background: transparent;
   width: 268px;
   padding: 5px;
   font-size: 16px;
   line-height: 1;
   border: 0;
   border-radius: 0;
   height: 34px;
   -webkit-appearance: none;
   }
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #ff656c;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}


</style>


</head>
<body>

<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="#news">Menu</a></li>
  <li><a href="#news">Contact</a></li>
  <li><a href="#contact">About</a></li>
  <li><a href="#about">Logout</a></li>
</ul>


<br>
<br>
<div class="login-block">
<form action="displayItems" method="get">
Select items by category:<br>
<select id="item" name="item" onchange="getSelItem()"><option>Select Category</option>
		
		<option name="drinks">Drinks</option>
		<option name="app">Appetizers</option>
		<option name="main">Main Course</option>
		<option name="desserts">Desserts</option>
</select>
</form>
	
	<br>
	<br>
	<form action="displayItems" method="GET" commandName ="item">
	Get Items from :
	<input type="text" id="category" name="category"></input>
	<br>
	<button type="submit" name="action" commandName ="item">Get Items</button>
	</form>
    
<script>
	 	function getSelItem(){
	 		
	 		var x = document.getElementById("item").value;
	 		document.getElementById("category").value = x;
	 		
	 	}
	 	
</script>


<br>
<table border="1">
	<th>ID</th>
	<th>Category</th>
	<th>Name</th>
	<th>Image</th>
	<th>Calories</th>
	<th>Unit Price</th>
	
	<c:forEach items="${itemList}" var="item">
		<tr>
			
			<td>${item.id}</td>
			<td>${item.category}</td>
			<td>${item.name}</td>
			<td></td>			
			<td>${item.calories}</td>
			<td>${item.unit_price}</td>
			
			
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
</div>
</body>
</html>