<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
					<p>Add products</p>
				</div>
			</div>
		</section>
		<section class="container">
			<form:form method="post" modelAttribute="newProduct" class="form-horizontal">
				<fieldset>
					<legend class="col-lg-6 border-bottom pl-0 mb-4">Add new product</legend>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="productId">Product Id</label>
						<div class="col-lg-3">
							<form:input path="productId" id="productId" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="name">Name</label>
						<div class="col-lg-3">
							<form:input path="name" id="name" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="description">Description</label>
						<div class="col-lg-3">
							<form:textarea path="description" id="description" rows="3" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="unitPrice">Unit Price</label>
						<div class="col-lg-3">
							<form:input path="unitPrice" id="unitPrice" type="number" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="manufacturer">Manufacturer</label>
						<div class="col-lg-3">
							<form:input path="manufacturer" id="manufacturer" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="category">Category</label>
						<div class="col-lg-3">
							<form:input path="category" id="category" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="unitsInStock">Units in Stock</label>
						<div class="col-lg-3">
							<form:input path="unitsInStock" id="unitsInStock" type="number" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-lg-2 text-right">Condition</label>
						<div class="col-lg-3">
							<div class="form-check form-check-inline">
								<form:radiobutton path="condition" id="conditionNew" value="New"
								                  cssClass="form-check-input"/>New
							</div>
							<div class="form-check form-check-inline">
								<form:radiobutton path="condition" id="conditionOld" value="Old"
								                  cssClass="form-check-input"/>Old
							</div>
							<div class="form-check form-check-inline">
								<form:radiobutton path="condition" id="conditionRef" value="Refurbished"
								                  cssClass="form-check-input"/>Refurbished
							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-lg-2 col-lg-3">
							<input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
						</div>
					</div>
				</fieldset>
			</form:form>
		</section>
	</body>
</html>