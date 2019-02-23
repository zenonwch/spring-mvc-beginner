<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<title><spring:message code="invalidPromoCode.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container text-center">
					<h1 class="alert alert-danger">
						<spring:message code="invalidPromoCode.page.title"/>
					</h1>
				</div>
			</div>
		</section>
		<section>
			<div class="container">
				<jsp:include page="components/buttonBack.jsp">
					<jsp:param name="backUrl" value="/market/products"/>
				</jsp:include>
			</div>
		</section>
	</body>
</html>