<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
<head>
<title>Smart Shop a E- commerce Online Shopping Portal</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
			function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- //for-mobile-apps -->
<link
	href="https://cdn.rawgit.com/aniketkhaire/static-resources/master/css/bootstrap.css"
	rel="stylesheet" type="text/css" media="all" />
<link
	href="https://cdn.rawgit.com/aniketkhaire/static-resources/master/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script type="text/javascript"
	src="https://cdn.rawgit.com/aniketkhaire/static-resources/master/js/jquery-2.1.4.min.js"></script>
<!-- //js -->
<!-- cart -->
<script
	src="https://cdn.rawgit.com/aniketkhaire/static-resources/master/js/simpleCart.min.js"></script>
<!-- cart -->
<!-- for bootstrap working -->
<script type="text/javascript"
	src="https://cdn.rawgit.com/aniketkhaire/static-resources/master/js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link
	href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,900,900italic,700italic'
	rel='stylesheet' type='text/css'>
<script
	src="https://cdn.rawgit.com/aniketkhaire/static-resources/master/js/jquery.easing.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
</head>
<script type="text/javascript">
function callMyFunction() {
	alert("Your order has been placed");
}
$(function() {
	$("#datepicker").datepicker({
		defaultDate : "+1w",
		changeMonth : true,
		numberOfMonths : 1,
		minDate : 0,
		maxDate : "+1m"
	});
});

</script>

<body>
	<!-- header-bot -->
	<div class="header-bot">
		<div class="container">
			<div class="col-md-3 header-left">
				<h1>
					<a href="/TOMSystem"><img
						src="https://cdn.rawgit.com/aniketkhaire/static-resources/master/images/logo3.jpg"></a>
				</h1>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- //header-bot -->
	<!-- banner -->
	<div class="ban-top">
		<div class="container">
			<div class="top_nav_left">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
						</div>
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse menu--shylock"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav menu__list">
								<li class="active menu__item "><a class="menu__link"
									href="items">View Items<span class="sr-only">(current)</span></a></li>
							
								<li class="active menu__item "><a class="menu__link"
									href="invoices">View Orders <span class="sr-only">(current)</span></a></li>

								<li class="active menu__item "><a class="menu__link"
									href="logout">Logout <span class="sr-only">(current)</span></a></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- //banner-top -->
	<!-- banner -->
	<div class="page-head">
		<div class="container">
			<h3>Welcome to Smart Shop</h3>
		</div>
	</div>
	<!-- //banner -->
	<!-- check out -->
	<div class="checkout">
		<div class="container">
			
			<!-- When user selects earliest pickup time-->
			<form method="POST" action="earliestPickUp">
				<div class="grid_3 grid_5 wow fadeInRight animated" data-wow-delay=".5s">
					<h2>Your order will be available by: ${pickup_time}</h2>
					<div class="input-group">
						<input type="submit" value="Confirm" class="form-control  label-success">
					</div>
				</div>
			</form>

			<!-- When user selects his own time -->
			<form method="POST" action="confirmOrder" command="item">
				<h2>Want it later? Your your custom pick-up time.</h2>
				
				<!-- Time select comes here -->				
				<div class="col-md-3 col-sm-3 col-lg-3 horizontal-tab">					
					<div class="input-group">
						<span class="input-group-addon" id="hours">HH</span> 
						<input type="number" min="6" max="21" class="form-control" value="6" name="hours">
					</div>
					
					<div class="input-group">
						<span class="input-group-addon" id="minutes">MM</span> 
						<input type="number" min="0" max="60" class="form-control" value="00" name="minutes">
					</div>			
				</div>
				
				<!-- Date select comes here -->
				<div class="grid_3 grid_5 wow fadeInRight animated" data-wow-delay=".5s">
					<div class="input-group">
						<span> Pick-up Date: <input name="date" type="text" id="datepicker" readonly="readonly"> </span>
					</div>
				</div>
				
				<div class="grid_3 grid_5 wow fadeInRight animated" data-wow-delay=".5s">
					<div class="input-group">
						<input type="submit" value="Confirm" class="form-control  label-success" ">
					</div>
				</div>
		
		<h2>${message}</h2>
	<div class="checkout-right-basket animated wow slideInRight"
					data-wow-delay=".5s">
					<a href="items"><span class="glyphicon glyphicon-menu-left"
						aria-hidden="true"></span>Back to orders</a>

				</div>			
		
			</form>
		</div>
		
	</div>
	
	
	
	
	<!-- //check out -->
	<!-- //product-nav -->
	<div class="coupons">
		<div class="container">
			<div class="coupons-grids text-center">
				<div class="col-md-3 coupons-gd">
					<h3>Buy your product in a simple way</h3>
				</div>
				<div class="col-md-3 coupons-gd">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					<h4>LOGIN TO YOUR ACCOUNT</h4>
					<p>Easy and hassle free login</p>
				</div>
				<div class="col-md-3 coupons-gd">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					<h4>SELECT YOUR ITEM</h4>
					<p>Select from wide range of items. I am lov'in it.</p>
				</div>
				<div class="col-md-3 coupons-gd">
					<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>
					<h4>MAKE PAYMENT</h4>
					<p>Place order and get to know the earliest pick-up time.</p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<