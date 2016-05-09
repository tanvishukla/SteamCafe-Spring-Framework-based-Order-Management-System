<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Management</title>
<!-- <script src="./CSS/SignUp.css"></script>  -->

</head>
<body>
<h1>SignUp</h1>

<div>
<!--  <form method="post" action="Signup">
-->
<form action="Signup" method="POST" commandName="user">

<span class="fontawesome-user"></span>
<input  type="text" path="name" id="name" name="name" required placeholder="Name" autocomplete="off" />

<span class="fontawesome-envelope-alt"></span>
<input type="text" path="email" id="email" name="email" required placeholder="Email" autocomplete="off" />

<span class="fontawesome-lock"></span>
<input type="password" path="password" name="password" id="password" required placeholder="Password" autocomplete="off"> 

<input type="submit" name="action" value="SignUp"/>
<p style="color: #000; text-align: center;"> ${userAlreadyExistError}</p>
</form>
</body>
</html>