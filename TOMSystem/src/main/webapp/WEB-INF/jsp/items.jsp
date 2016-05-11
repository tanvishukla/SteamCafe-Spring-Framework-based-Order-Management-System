<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
	<head>
		<title>Smart Shop a Ecommerce Online Shopping Portal</title>
		<!-- for-mobile-apps -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
			function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!-- //for-mobile-apps -->
		<link
			href="https://cdn.rawgit.com/aniketkhaire/static-resources/master/css/bootstrap.css"
			rel="stylesheet" type="text/css" media="all" />
		<link href="https://cdn.rawgit.com/aniketkhaire/static-resources/master/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<!-- js -->
		<script type="text/javascript" src="https://cdn.rawgit.com/aniketkhaire/static-resources/master/js/jquery-2.1.4.min.js"></script>
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
<body>
	<!-- header-bot -->
	<div class="header-bot">
		<div class="container">
			<div class="col-md-3 header-left">
				<h1>
					<a href="index.html"><img src="https://cdn.rawgit.com/aniketkhaire/static-resources/master/images/logo3.jpg"></a>
				</h1>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- //header-bot -->
	<form method="POST" action="" command="item">
	<!--My Tab comes HERE-->
	<div class="col-sm-12 col-md-12 col-lg-12 mb-60">
		<br />
		<div class="horizontal-tab">
			<ul class="nav nav-tabs">
				<li class="active col-sm-3 col-md-3 col-lg-3"><a href="#drinks"
					data-toggle="tab" aria-expanded="true">Drinks</a></li>
				<li class="col-sm-3 col-md-3 col-lg-3"><a href="#appetizer"
					data-toggle="tab" aria-expanded="false">Appetizer</a></li>
				<li class=" col-sm-3 col-md-3 col-lg-3 "><a href="#maincourse"
					data-toggle="tab" aria-expanded="false">Main Course</a></li>
				<li class=" col-sm-3 col-md-3 col-lg-3 "><a href="#dessert"
					data-toggle="tab" aria-expanded="false">Dessert</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade" id="drinks">
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive checkout-right animated wow slideInUp" data-wow-delay=".5s">
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
														<div class="entry value-minus label-danger" id=${item.id} onclick="removeThis()"></div>
													</div>
												</td>
												<td class="invert-image"><img src="./images/${item.picture}.png" alt=" " class="img-responsive" /></td>
												<td class="invert">
													<div class="quantity">
														<div class="quantity-select">
															<div class="entry value-minus">&nbsp;</div>
															<div class="entry value">
																<span>1</span>
															</div>
															<div class="entry value-plus active">&nbsp;</div>
														</div>
													</div>
												</td>
												<td class="invert">${item.name}</td>
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
							<div class="table-responsive checkout-right animated wow slideInUp" data-wow-delay=".5s">
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
											<tr class="rem1" id=${item.id} onclick="addToCart()">
												<td class="invert-closeb">
													<div class="rem">
														<div class="close1"></div>
													</div>
												</td>
												<td class="invert-image"><img src="${item.picture}"	alt=" " class="img-responsive" /></td>
												<td class="invert">
													<div class="quantity">
														<div class="quantity-select">
															<div class="entry value-minus">&nbsp;</div>
															<div class="entry value">
																<span>1</span>
															</div>
															<div class="entry value-plus active">&nbsp;</div>
														</div>
													</div>
												</td>
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
							<div class="table-responsive checkout-right animated wow slideInUp" data-wow-delay=".5s">
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
											<tr class="rem1" id=${item.id} onclick="addToCart()">
												<td class="invert-closeb">
													<div class="rem">
														<div class="close1"></div>
													</div>
												</td>
												<td class="invert-image"><img src="${item.picture}"	alt=" " class="img-responsive" /></td>
												<td class="invert">
													<div class="quantity">
														<div class="quantity-select">
															<div class="entry value-minus">&nbsp;</div>
															<div class="entry value">
																<span>1</span>
															</div>
															<div class="entry value-plus active">&nbsp;</div>
														</div>
													</div>
												</td>
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
							<div class="table-responsive checkout-right animated wow slideInUp" data-wow-delay=".5s">
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
											<tr class="rem1" id=${item.id} onclick="addToCart()">
												<td class="invert-closeb">
													<div class="rem">
														<div class="close1"></div>
													</div>
												</td>
												<td class="invert-image"><img src="${item.picture}"	alt=" " class="img-responsive" /></td>
												<td class="invert">
													<div class="quantity">
														<div class="quantity-select">
															<div class="entry value-minus">&nbsp;</div>
															<div class="entry value">
																<span>1</span>
															</div>
															<div class="entry value-plus active">&nbsp;</div>
														</div>
													</div>
												</td>
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
	

	<!--My Tab ends HERE-->


	<!-- check out -->
	<div class="checkout">
		<div class="container">
			<br />
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

		</div>
	</div>
	</form>
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
</body>
</html>