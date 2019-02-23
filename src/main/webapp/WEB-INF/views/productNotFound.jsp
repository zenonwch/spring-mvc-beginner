<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="exception" type="java.lang.String"--%>
<%--@elvariable id="url" type="java.lang.String"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title><spring:message code="product.notFound.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<h1 class="alert alert-danger">
						<c:out value="${message}"/>
					</h1>
				</div>
			</div>
		</section>
		<section>
			<div class="container">
				<p><c:out value="${url}"/></p>
				<p><c:out value="${exception}"/></p>
			</div>
			<div class="container">
				<jsp:include page="components/buttonBack.jsp">
					<jsp:param name="backUrl" value="/market/products"/>
				</jsp:include>
			</div>
		</section>
	</body>
</html>