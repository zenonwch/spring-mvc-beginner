<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
	<head>
		<title>Customers</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
		      integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
		      crossorigin="anonymous">
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<h1>Customers</h1>
					<p>Our customers</p>
				</div>
			</div>
		</section>
		<section class="container">
			<div class="row">
				<c:forEach items="${customers}" var="customer">
					<div class="col-sm-6 col-md-3" style="padding-bottom: 15px;">
						<div class="card">
							<div class="card-body p-3">
								<h3 class="card-title text-center"><c:out value="${customer.name}"/></h3>
								<p class="card-text">Address: <c:out value="${customer.address}"/></p>
								<p class="card-text">Orders made: <c:out value="${customer.noOfOrdersMade}"/></p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
	</body>
</html>