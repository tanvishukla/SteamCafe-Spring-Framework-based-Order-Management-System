<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Smart Shop a E-commerce Online Shopping Portal</title>
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

<!-- for selecting date range -->	
	<script>
  $(function() {
    $( "#from" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function( selectedDate ) {
        $( "#to" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( "#to" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function( selectedDate ) {
        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
  });
  
  function ViewDetails(ele) {		
		//alert(document.getElementsByName(addToCart.caller.arguments[0].target.id)[0].value);
		//alert(addToCart.caller.arguments[0].target.id);
		//alert(addToCart.caller.arguments[0].target.id.innerHTML);
		//alert("Clicked "+c);
		//var quantity=document.getElementsByName(ViewDetails.caller.arguments[0].target.id)[0].value;
		//var id = ViewDetails.caller.arguments[0].target.id + "";
		$.ajax({
			type : 'POST',
			url : "/ViewIndividualDetails",
			data : ele.id
		});
		setTimeout(5000);
		//location.reload();
	}
  </script>
  
  
</head>

<body>
<div>                           
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
									href="addItem">Add Item <span class="sr-only">(current)</span></a></li>

								<li class="active menu__item "><a class="menu__link"
									href="removeItem">Remove Item <span class="sr-only">(current)</span></a></li>

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
			<h3>Welcome to Smart Shop</h3>
		</div>
	</div>
	<!-- //banner -->
	<!-- check out -->
	<div class="checkout">
		<div class="container">
		                                
						 
						</div>
						<form method="POST" action="DateSortedReports" command="item">
						<label for="from" name="fromtag">From</label>
						<input type="text" id="from" name="from">
						<label for="to" name="totag">to</label>
						<input type="text" id="to" name="to">
                        
                       
         
			
				
				
				<!-- Date select comes here 
				<div class="grid_3 grid_5 wow fadeInRight animated" data-wow-delay=".5s">
					<div class="input-group">
						<span> Pick-up Date: <input name="date" type="text" id="datepicker" readonly="readonly"> </span>
					</div>
				</div> -->
				
				<div class="grid_3 grid_5 wow fadeInRight animated" data-wow-delay=".5s">
					<div class="input-group">
						<input type="submit" value="View Orders in range" class="form-control  label-success" ">
					</div>
				</div> 
					
			</form>
			<!--Table for appetizer comes HERE-->
										<div
											class="table-responsive checkout-right animated wow slideInUp"
											data-wow-delay=".5s">
											<table class="timetable_sub">
												<thead>
													<tr>
														<th>Invoice_id</th>
														<th>OrderTime</th>
														<th>StartTime</th>
														<th>EndTime</th>
														<th>PickupTime</th>
														<th>Status</th>
														<th>Customer_Email</th>
														
														</tr>
												</thead>
												<c:forEach items="${InvoicesList}" var="invoice">
												<tr>
														
														<td class="invert">${invoice.invoice_id}</td>
														<td class="invert">${invoice.orderDate}</td>
														<td class="invert">${invoice.startTime}</td>
														<td class="invert">${invoice.endTime}</td>
														<td class="invert">${invoice.pickupTime}</td>
														<td class="invert">${invoice.status}</td>
														<td class="invert">${invoice.email}</td>
														
														
														

												</tr>
												</c:forEach>
															</div>
         </body>               