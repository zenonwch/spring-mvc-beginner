<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<title><spring:message code="customers.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="components/langSelector.jsp"/>
					<h1><spring:message code="customers.page.title"/></h1>
					<p><spring:message code="customers.page.description"/></p>
					<jsp:include page="components/navigation.jsp">
						<jsp:param name="page" value="customers"/>
					</jsp:include>
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