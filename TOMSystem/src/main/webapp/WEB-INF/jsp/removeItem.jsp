<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Smart Shop a E-commerce Online Shopping Portal</title>
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
</head>

<script type="text/javascript">
	function removeThis() {
		var id = removeThis.caller.arguments[0].target.id + "";
		$.ajax({
			type : 'POST',
			url : "/TOMSystem/removeItem",
			data : id
		});
		setTimeout(2000);
		location.reload();
	}

	function addThis() {
		var id = addThis.caller.arguments[0].target.id + "";
		$.ajax({
			type : 'POST',
			url : "/TOMSystem/addThisItem",
			data : id
		});
		setTimeout(2000);
		location.reload();
	}
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
									href=addItem>Add Item <span class="sr-only">(current)</span></a></li>

								<li class="active menu__item "><a class="menu__link"
									href=removeItem>Remove Item <span class="sr-only">(current)</span></a></li>

								<li class="active menu__item "><a class="menu__link"
									href="reset">Reset System Orders <span class="sr-only">(current)</span></a></li>

								<li class="active menu__item "><a class="menu__link"
									href="getReports">Get Order Reports<span class="sr-only">(current)</span></a></li>
								<li class="active menu__item "><a class="menu__link"
									href="logout">Logout<span class="sr-only">(current)</span></a></li>

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
			<h3>Welcome Admin</h3>
		</div>
	</div>
	<!-- //banner -->
	<!-- check out -->
	<div class="checkout">
		<div class="container">
			<h2>Available items...</h2>
			<br />
			<form method="POST" action="removeItem" command="item">

				<div class="table-responsive checkout-right animated wow slideInUp"
					data-wow-delay=".5s">
					<table class="timetable_sub">
						<thead>
							<tr>
								<th>Remove</th>
								<th>Item</th>
								<th>Item Name</th>
								<th>Price</th>
							</tr>
						</thead>

						<c:forEach items="${itemList}" var="item">
							<!-- For every item-->
							<tr class=${item.id } style="height: 10px;">
								<td name="id" value=${item.id } class="invert-closeb">
									<div class="rem">
										<div class="entry value-minus label-danger" id = ${item.id} onclick="removeThis()"></div>
						   			</div>
								</td>
								<td class="invert-image"><img
									src="${item.picture}" align="middle" width="100px" alt=" "
									class="img-responsive" /></td>
								<td class="invert">${item.name}</td>
								<td class="invert">${item.unit_price}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</form>
		</div>
	</div>

	<div class="checkout">
		<div class="container">
			<h2>Unavailable items...</h2>
			<br />
			<form method="POST" action="removeItem" command="item">

				<div class="table-responsive checkout-right animated wow slideInUp"
					data-wow-delay=".5s">
					<table class="timetable_sub">
						<thead>
							<tr>
								<th>Add</th>
								<th>Item</th>
								<th>Item Name</th>
								<th>Price</th>
							</tr>
						</thead>

						<c:forEach items="${unavailableItemList}" var="item">
							<!-- For every item-->
							<tr class=${item.id} >
								<td name="id" value=${item.id } class="invert-closeb">
									<div class="rem">
										<div class="enrty value-plus label-success" id=${item.id
											} onclick="addThis()"></div>
									</div>
								</td>
								<td class="invert-image"><img
									src="${item.picture}"
									width="100px" alt=" " class="img-responsive" /></td>
								<td class="invert">${item.name}</td>
								<td class="invert">${item.unit_price}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</form>
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
</body>
</html>