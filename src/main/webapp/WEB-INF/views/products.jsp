<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
					<p>All the available products in our store</p>
				</div>
			</div>
		</section>
		<section class="container">
			<div class="row">
				<c:forEach items="${products}" var="product">
					<div class="col-sm-6 col-md-3" style="padding-bottom: 15px;">
						<div class="card">
							<div class="card-body p-3">
								<h3 class="card-title text-center"><c:out value="${product.name}"/></h3>
								<p class="card-text"><c:out value="${product.description}"/></p>
								<p class="card-text text-center">$<c:out value="${product.unitPrice}"/></p>
								<p class="card-text">Available ${product.unitsInStock} units in stock</p>
								<div class="row justify-content-center">
									<a href="<spring:url value="/market/product?id=${product.productId}"/>"
									   class="btn btn-primary">
										<span><i class="fas fa-info-circle"></i> Details</span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
	</body>
</html>