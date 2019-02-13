<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="span" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
	<head>
		<title><spring:message code="customers.page.title"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
		      integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
		      crossorigin="anonymous">
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<h1><spring:message code="customers.page.title"/></h1>
					<p><spring:message code="customers.page.description"/></p>
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
								<p class="card-text">
									<spring:message code="addCustomer.form.address.label"/>
									: <c:out value="${customer.address}"/>
								</p>
								<p class="card-text">
									<spring:message code="addCustomer.form.ordersMade.label"/>
									: <c:out value="${customer.noOfOrdersMade}"/>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
	</body>
</html>