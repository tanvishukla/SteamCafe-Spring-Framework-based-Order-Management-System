<!DOCTYPE html>
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<meta charset="UTF-8">

<title>Takeout Order Management System</title>
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
    width: 320px;
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

</style>
</head>

<body>
<div class="logo"></div></br>
</br>
<div class="login-block">
    <h1>Login</h1>
    <form action="authUser" method="POST" commandName ="user">
    <input type="text" value="" name="email" placeholder="Username" id="email" />
    <input type="password" value="" name="password" placeholder="Password" id="password" />
    <button type="submit" name="action" >Login</button>
    </form>
    <br>
    
    <form action="SignupPage" method="post">

	<button type="submit" name="action" >SignUp</button>

    <p>${email}</p>
<p>${VerifiedUser}</p>
</div>





</body>

</html>