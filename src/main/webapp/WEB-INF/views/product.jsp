<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular.min.js"></script>
		<script src="<c:url value="/resources/js/controllers.js"/>"></script>
		<title><c:out value="${product.name}"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="components/langSelector.jsp"/>
					<h1><spring:message code="products.page.title"/></h1>
				</div>
			</div>
		</section>
		<section class="container" ng-app="cartApp">
			<div class="row">
				<div class="col-md-10">
					<div class="card">
						<div class="row no-gutters">
							<div class="col-md-5 text-center align-self-center">
								<jsp:include page="components/image.jsp">
									<jsp:param name="imageFileName"
									           value="${fn:escapeXml(product.productId)}.png"/>
									<jsp:param name="imageName"
									           value="${fn:escapeXml(product.name)}"/>
									<jsp:param name="style" value="max-height: 400px;"/>
								</jsp:include>
							</div>
							<div class="col">
								<div class="card-block p-3">
									<h3 class="card-title text-center"><c:out value="${product.name}"/></h3>
									<p class="card-text"><c:out value="${product.description}"/></p>
									<p class="card-text">
										<strong><spring:message code="product.card.productId.label"/> :</strong>
										<span class="badge badge-warning text-white"> <!--
									--><c:out value="${product.productId}"/></span>
									</p>
									<p class="card-text">
										<strong>
											<spring:message code="product.card.brand.label"/> :
										</strong> <c:out value="${product.manufacturer}"/>
									</p>
									<p class="card-text">
										<strong>
											<spring:message code="product.card.category.label"/> :
										</strong> <c:out value="${product.category}"/>
									</p>
									<p class="card-text">
										<strong>
											<spring:message code="product.card.unitsInStock.label"/> :
										</strong> <c:out value="${product.unitsInStock}"/>
									</p>
									<h4 class="pb-3">
										<c:out value="${product.unitPrice}"/> <!--
								--><spring:message code="product.card.currencyCode"/>
									</h4>
									<div class="row justify-content-center">
										<a href="#" class="btn btn-warning btn-large mr-3"
										   ng-controller="cartController"
										   ng-click="addToCart('<c:out value="${product.productId}"/>')">
											<span class="text-white">
												<i class="fas fa-shopping-cart fa-flip-horizontal"></i> <!--
												--><spring:message code="product.card.orderNow.button"/>
											</span>
										</a><!--
									--><a href="<spring:url value="/cart"/>" class="btn btn-secondary btn-large mr-3">
											<span>
												<i class="far fa-hand-pointer fa-rotate-90"></i> <!--
												--><spring:message code="product.card.viewCart.button"/>
											</span>
									</a><!--
									--><a href="<spring:url value="/market/products"/>"
										  class="btn btn-light btn-large border text-secondary">
											<span>
												<i class="far fa-hand-pointer rotate-270-flip-horizontal"></i> <!--
												--><spring:message code="back.button"/>
											</span>
									</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
</html>