<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<title><spring:message code="products.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="components/langSelector.jsp"/>
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
										<jsp:include page="components/image.jsp">
											<jsp:param name="imageFileName"
											           value="${fn:escapeXml(product.productId)}.png"/>
											<jsp:param name="imageName"
											           value="${fn:escapeXml(product.name)}"/>
											<jsp:param name="style" value="max-height: 200px;"/>
										</jsp:include>
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