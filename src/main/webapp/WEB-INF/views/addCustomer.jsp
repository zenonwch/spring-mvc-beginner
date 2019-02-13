<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
					<p><spring:message code="addCustomer.page.description"/></p>
				</div>
			</div>
		</section>
		<section class="container">
			<form:form method="post" modelAttribute="newCustomer" class="form-horizontal">
				<fieldset>
					<legend class="col-lg-6 border-bottom pl-0 mb-4">
						<spring:message code="addCustomer.form.legend"/>
					</legend>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="customerId">
							<spring:message code="addCustomer.form.customerId.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="customerId" id="customerId" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="name">
							<spring:message code="addCustomer.form.name.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="name" id="name" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="address">
							<spring:message code="addCustomer.form.address.label"/>
						</label>
						<div class="col-lg-3">
							<form:textarea path="address" id="address" rows="3" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="ordersMade">
							<spring:message code="addCustomer.form.ordersMade.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="noOfOrdersMade" id="ordersMade" type="number" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-lg-2 col-lg-3">
							<input type="submit" id="btnAdd" class="btn btn-primary"
							       value="<spring:message code="addCustomer.form.submit.button"/>"/>
						</div>
					</div>
				</fieldset>
			</form:form>
		</section>
	</body>
</html>