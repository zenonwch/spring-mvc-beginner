<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
	<head>
		<title>Products</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
		      integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
		      crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
		      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
		      crossorigin="anonymous">
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<h1>Products</h1>
				</div>
			</div>
		</section>
		<section class="container">
			<div class="row">
				<div class="col-md-5">
					<div class="card">
						<div class="card-body p-3">
							<h3 class="card-title text-center"><c:out value="${product.name}"/></h3>
							<p class="card-text"><c:out value="${product.description}"/></p>
							<p class="card-text">
								<strong>Item code :</strong>
								<span class="badge badge-warning text-white"> <c:out
										value="${product.productId}"/></span>
							</p>
							<p class="card-text">
								<strong>Manufacturer :</strong> <c:out value="${product.manufacturer}"/>
							</p>
							<p class="card-text">
								<strong>Category :</strong> <c:out value="${product.category}"/>
							</p>
							<p class="card-text">
								<strong>Available units in stock :</strong> <c:out value="${product.unitsInStock}"/>
							</p>
							<h4><c:out value="${product.unitPrice}"/> USD</h4>
							<p>
								<a href="#" class="btn btn-warning btn-large">
									<span class="text-white"><i class="fas fa-shopping-cart fa-flip-horizontal"></i> Order Now</span>
								</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
</html>