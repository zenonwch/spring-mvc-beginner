<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular.min.js"></script>
		<script src="<c:url value="/resources/js/controllers.js"/>"></script>
		<title><spring:message code="cart.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="components/langSelector.jsp"/>
					<h1><spring:message code="cart.page.title"/></h1>
					<p><spring:message code="cart.page.description"/></p>
				</div>
			</div>
		</section>
		<section class="container" ng-app="cartApp">
			<div ng-controller="cartController" ng-init="initCartId('<c:out value="${cartId}"/>')">
				<div>
					<a class="btn btn-danger float-right mb-3" ng-click="clearCart()">
						<span class="text-white">
							<i class="far fa-times-circle mr-2"></i><!--
							--><spring:message code="cart.button.clear"/>
						</span>
					</a>
				</div>
				<table class="table table-hover">
					<tr>
						<th><spring:message code="cart.table.header.product"/></th>
						<th><spring:message code="cart.table.header.unitPrice"/></th>
						<th><spring:message code="cart.table.header.quantity"/></th>
						<th><spring:message code="cart.table.header.totalPrice"/></th>
						<th class="text-center" style="width: 105px;">
							<spring:message code="cart.table.header.action"/>
						</th>
					</tr>
					<tr ng-repeat="item in cart.cartItems">
						<td>{{item.productId}} - {{item.name}}</td>
						<td>{{item.unitPrice}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.totalPrice}}</td>
						<td class="p-2 text-center">
							<a href="#" class="badge badge-danger" ng-click="removeFromCart(item.productId)">
								<div class="m-1">
								<span class="text-white">
									<i class="far fa-trash-alt"></i> <!--
									--><spring:message code="cart.button.removeItem"/>
								</span>
								</div>
							</a>
						</td>
					</tr>
					<tr>
						<th></th>
						<th></th>
						<th><spring:message code="cart.table.grandTotal"/></th>
						<th>{{cart.grandTotal}}</th>
						<th></th>
					</tr>
				</table>
				<a href="#" class="btn btn-success pull-right mr-3">
					<span>
						<i class="fas fa-shopping-cart fa-flip-horizontal"></i> <!--
						--><spring:message code="cart.button.checkout"/>
					</span>
				</a><!--
				--><a href="<spring:url value="/market/products"/>"
				      class="btn btn-light btn-large border text-secondary">
					<span>
						<i class="far fa-hand-pointer rotate-270-flip-horizontal"></i> <!--
						--><spring:message code="cart.button.continue"/>
					</span>
			</a>
			</div>
		</section>
	</body>
</html>