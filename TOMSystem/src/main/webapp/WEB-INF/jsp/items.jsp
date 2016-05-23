<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
</head>

<script type="text/javascript">
	 $(function(){
		    var $select = $(".1-100");
		    for (var i=1;i<=100;i++){
		        $select.append($('<option></option>').val(i).html(i))
		    }
		});
	
	function addToCart() {		
		//alert(document.getElementsByName(addToCart.caller.arguments[0].target.id)[0].value);
		//alert(addToCart.caller.arguments[0].target.id);
		//alert(addToCart.caller.arguments[0].target.id.innerHTML);
		//alert("Clicked "+c);
		var quantity=document.getElementsByName(addToCart.caller.arguments[0].target.id)[0].value;
		var id = addToCart.caller.arguments[0].target.id + "";
		$.ajax({
			type : 'POST',
			url : "/TOMSystem/addCart",
			data : {item_id:id,item_quantity:quantity}
		});
		setTimeout(5000);
		location.reload();
	}
	
	function removeThisFromCart() {
		alert(removeThisFromCart.caller.arguments[0].target.id);
		//alert("Clicked "+c);
		var id = removeThisFromCart.caller.arguments[0].target.id + "";
		$.ajax({
			type : 'POST',
			url : "/TOMSystem/removeFromCart",
			data : id
		});
		setTimeout(5000);
		location.reload();
	}
	
</script>

<body>
	<!-- header-bot -->
	<div class="header-bot">
		<div class="container">
			<div class="col-md-3 header-left">
				<h1>
					<a href="index.html"><img
						src="https://cdn.rawgit.com/aniketkhaire/static-resources/master/images/logo3.jpg"></a>
				</h1>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

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

	<div class="page-head">
		<div class="container">
			<h3>Welcome to Smart Shop</h3>
		</div>
	</div>
	<div class="checkout">
		<div class="container">
			<!-- //header-bot -->
			<form method="POST" action="" command="item">
				<!--My Tab comes HERE-->
				<div class="col-sm-12 col-md-12 col-lg-12 mb-60">
					<br />
					<div class="horizontal-tab">
						<ul class="nav nav-tabs">
							<li class="active col-sm-3 col-md-3 col-lg-3"><a
								href="#drinks" data-toggle="tab" aria-expanded="true">Drinks</a></li>
							<li class="col-sm-3 col-md-3 col-lg-3"><a href="#appetizer"
								data-toggle="tab" aria-expanded="false">Appetizer</a></li>
							<li class=" col-sm-3 col-md-3 col-lg-3 "><a
								href="#maincourse" data-toggle="tab" aria-expanded="false">Main
									Course</a></li>
							<li class=" col-sm-3 col-md-3 col-lg-3 "><a href="#dessert"
								data-toggle="tab" aria-expanded="false">Dessert</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active in" id="drinks">
								<div class="row">
									<div class="col-md-12">
										<div
											class="table-responsive checkout-right animated wow slideInUp"
											data-wow-delay=".5s">
											<table class="timetable_sub">
												<thead>
													<tr>
														<th>Add to Cart</th>
														<th>Item</th>
														<th>Quantity</th>
														<th>Item Name</th>
														<th>Calories</th>
														<th>Price</th>
													</tr>
												</thead>

												<c:forEach items="${drinksList}" var="item">
													<!-- For every item-->
													<tr class=${item.id} >
														<td name="id" value=${item.id } class="invert-closeb">
															<div class="rem">
																<div class="entry value-plus label-success"
																	id=${item.id
																	} onclick="addToCart()"></div>
															</div>
														</td>
														<td class="invert-image"><img
															src="${item.picture}" width="100px"
															alt=" " class="img-responsive" /></td>


														<td class="invert"><select class="1-100"
															name="${item.id}"></select></td>
														<td class="invert">${item.name}</td>
														<td class="invert">${item.calories}</td>
														<td class="invert">${item.unit_price}</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="appetizer">
								<div class="row">
									<div class="col-md-12">
										<!--Table for appetizer comes HERE-->
										<div
											class="table-responsive checkout-right animated wow slideInUp"
											data-wow-delay=".5s">
											<table class="timetable_sub">
												<thead>
													<tr>
														<th>Add to Cart</th>
														<th>Item</th>
														<th>Quantity</th>
														<th>Item Name</th>
														<th>Calories</th>
														<th>Price</th>
													</tr>
												</thead>

												<c:forEach items="${appetizerList}" var="item">
													<!-- For every item-->
													<tr class=${item.id} >
														<td name="id" value=${item.id } class="invert-closeb">
															<div class="rem">
																<div class="entry value-plus label-success"
																	id=${item.id } onclick="addToCart()"></div>
															</div>
														</td>
														<td class="invert-image"><img
															src="${item.picture}" width="100px"
															alt=" " class="img-responsive" /></td>
														<td class="invert"><select class="1-100"
															name="${item.id}"></select></td>
														<td class="invert">${item.name}</td>
														<td class="invert">${item.calories}</td>
														<td class="invert">${item.unit_price}</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="maincourse">
								<div class="row">
									<div class="col-md-12">
										<!--Table for main course comes HERE-->
										<div
											class="table-responsive checkout-right animated wow slideInUp"
											data-wow-delay=".5s">
											<table class="timetable_sub">
												<thead>
													<tr>
														<th>Add to Cart</th>
														<th>Item</th>
														<th>Quantity</th>
														<th>Item Name</th>
														<th>Calories</th>
														<th>Price</th>
													</tr>
												</thead>

												<c:forEach items="${maincourseList}" var="item">
													<!-- For every item-->
													<tr class=${item.id} >
														<td name="id" value=${item.id } class="invert-closeb">
															<div class="rem">
																<div class="entry value-plus label-success"
																	id=${item.id
																	} onclick="addToCart()"></div>
															</div>
														</td>
														<td class="invert-image"><img
															src="${item.picture}"
															alt=" " width="100px" class="img-responsive" /></td>
														<td class="invert"><select class="1-100"
															name="${item.id}"></select></td>
														<td class="invert">${item.name}</td>
														<td class="invert">${item.calories}</td>
														<td class="invert">${item.unit_price}</td>
													</tr>
												</c:forEach>



											</table>
										</div>
									</div>
								</div>
							</div>

							<div class="tab-pane fade" id="dessert">
								<div class="row">
									<div class="col-md-12">
										<!--  Table for deserts comes HERE-->
										<div
											class="table-responsive checkout-right animated wow slideInUp"
											data-wow-delay=".5s">
											<table class="timetable_sub">
												<thead>
													<tr>
														<th>Add to Cart</th>
														<th>Item</th>
														<th>Quantity</th>
														<th>Item Name</th>
														<th>Calories</th>
														<th>Price</th>
													</tr>
												</thead>

												<c:forEach items="${dessertList}" var="item">
													<!-- For every item-->
													<tr class=${item.id} >
														<td name="id" value=${item.id } class="invert-closeb">
															<div class="rem">
																<div class="entry value-plus label-success"
																	id=${item.id
																	} onclick="addToCart()"></div>
															</div>
														</td>
														<td class="invert-image"><img src="${item.picture}"
															alt=" " width="100px" class="img-responsive" /></td>
														<td class="invert"><select class="1-100"
															name="${item.id}"></select></td>
														<td class="invert">${item.name}</td>
														<td class="invert">${item.calories}</td>
														<td class="invert">${item.unit_price}</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="checkout">
		<div class="container">
			<h2>My Shopping Cart</h2>
			<br />
			<form method="POST" action="proceed" command="item">

				<div class="table-responsive checkout-right animated wow slideInUp"
					data-wow-delay=".5s">
					<table class="timetable_sub">
						<thead>
							<tr>
								<th>Remove</th>
								<th>Item</th>
								<th>Item Name</th>
								<th>Quantity</th>

								<th>Price</th>
							</tr>
						</thead>


						<c:forEach items="${cart}" var="item">
							<!-- For every item-->
							<tr class=${item.itemId} >
								<td name="id" value=${item.itemId } class="invert-closeb">
									<div class="rem">
										<div class="enrty value-plus label-danger"
											id=${item.itemId
											} onclick="removeThisFromCart()"></div>
									</div>
								</td>

								<td class="invert-image"><img src="${item.picture}" width="100px" alt=" "
									class="img-responsive" /></td>
								<td class="invert">${item.itemName}</td>
								<td class="invert">${item.quantity}</td>
								<td class="invert">${item.price}</td>
							</tr>
						</c:forEach>
					</table>

				</div>

				<div class="checkout-right-basket animated wow slideInRight"
					data-wow-delay=".5s">
					<a href="checkout"><span class="glyphicon glyphicon-menu-right"
						aria-hidden="true"></span>Continue to checkout</a>

				</div>
			</form>
			<br>
		</div>
	</div>


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