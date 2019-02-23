<%--@elvariable id="greeting" type="java.lang.String"--%>
<%--@elvariable id="tagline" type="java.lang.String"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="welcome.page.title"/></title>
	</head>
	<body>
		<div class="jumbotron">
			<div class="container">
				<jsp:include page="components/langSelector.jsp"/>
				<h1><c:out value="${greeting}"/></h1>
				<p><c:out value="${tagline}"/></p>
			</div>
		</div>
	</body>
</html>