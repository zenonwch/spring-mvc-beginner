<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
	<head>
		<title>Products</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
		      integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
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
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px;">
					<div class="card">
						<div class="card-body p-3">
							<h3 class="card-title text-center"><c:out value="${product.name}"/></h3>
							<p class="card-text"><c:out value="${product.description}"/></p>
							<p class="card-text text-center"><c:out value="${product.unitPrice}"/> USD</p>
							<p class="card-text">Available ${product.unitsInStock} units in stock</p>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
</html>