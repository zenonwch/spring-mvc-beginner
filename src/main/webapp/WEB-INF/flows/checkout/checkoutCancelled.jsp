<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="../../views/components/head.jsp"/>
		<title><spring:message code="checkoutCancelled.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="../../views/components/langSelector.jsp"/>
					<h1 class="alert alert-danger"><spring:message code="checkoutCancelled.page.header"/></h1>
					<p><spring:message code="checkoutCancelled.page.message"/></p>
				</div>
			</div>
		</section>
		<section>
			<div class="container">
				<p>
					<jsp:include page="../../views/components/buttonProducts.jsp"/>
				</p>
			</div>
		</section>
	</body>
</html>