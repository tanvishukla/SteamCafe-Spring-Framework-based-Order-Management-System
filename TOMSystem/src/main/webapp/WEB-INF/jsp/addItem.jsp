<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Add Item</title>
</head>
<body>
 
    <form method="POST" action="addItem" enctype="multipart/form-data" command="item">
    
    	Name: <input type="text" name="item_name"><br />
    	Category: 
    	<select name="category">
                <option value="Drinks">Drinks</option>
                <option value="Appetizer">Appetizer</option>
                <option value="Main Course">Main Course</option>
                <option value="Desserts">Desserts</option>
        </select>
    	<br />
    	
    	Price: <input type="number" name="item_price"><br />
    	Calories: <input type="number" name="item_calories"><br />
    	Preparation Time: 
    	<select name="item_prep_time">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
        </select>
		<br />
        Image: <input type="file" name="image"><br /> 
        
        <input type="submit" value="Upload"> Add Item
    </form>
    
    <br>
<table border="1">	
	<th>Name</th>
	<th>Calories</th>
	<th>Unit Price</th>
	<th>Preparation Time</th>
	<th>Cateory</th>
	<th>Image Name</th>
	
	<c:forEach items="${itemList}" var="item">
		<tr>
			<td>${item.name}</td>
			<td>${item.calories}</td>
			<td>${item.unit_price}</td>
			<td>${item.prep_time}</td>
			<td>${item.category}</td>
			<td>${item.picture}</td>
		</tr>
	</c:forEach>
</table>     
</body>
</html>