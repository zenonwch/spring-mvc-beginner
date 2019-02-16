<%--suppress JSUnusedGlobalSymbols --%>
<%--@elvariable id="noImage" type="my.spring.project.springmvc.controller.NoImageAdvice"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
	<head>
		<title><spring:message code="products.page.title"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include page="head.jsp"/>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="langSelector.jsp"/>
					<h1><spring:message code="products.page.title"/></h1>
					<p><spring:message code="products.page.description"/></p>
				</div>
			</div>
		</section>
		<section class="container">
			<div class="row">
				<c:forEach items="${products}" var="product">
					<div class="col-sm-6 col-md-3" style="padding-bottom: 15px;">
						<div class="card d-block">
							<div class="container">
								<div class="row" style="height: 200px;">
									<div class="col text-center align-self-center">
											<%--suppress JSUnusedGlobalSymbols --%>
										<img class="img-fluid pt-3"
										     style="max-height: 200px;"
										     src="<c:url value="/img/${product.productId}.png"/>"
										     onerror="this.onerror=null; this.src='<c:url value="/img/${noImage}"/>'"
										     alt="<c:out value="${product.name}"/>"/>
									</div>
								</div>
							</div>
							<div class="card-body p-3">
								<h3 class="card-title text-center"><c:out value="${product.name}"/></h3>
								<p class="card-text"><c:out value="${product.description}"/></p>
								<p class="card-text text-center">
									<spring:message code="products.product.price.label"
									                arguments="${product.unitPrice}"/>
								</p>
								<p class="card-text">
									<spring:message code="products.product.unitsInStock.label"
									                arguments="${product.unitsInStock}"/>
								</p>
								<div class="row justify-content-center">
									<a href="<spring:url value="/market/product?id=${product.productId}"/>"
									   class="btn btn-primary">
										<span>
											<i class="fas fa-info-circle pr-2"></i><!--
											--><spring:message code="products.product.details.button"/>
										</span>
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