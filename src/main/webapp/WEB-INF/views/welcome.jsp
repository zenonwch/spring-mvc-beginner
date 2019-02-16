<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
	<head>
		<title><spring:message code="welcome.page.title"/></title>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<jsp:include page="head.jsp"/>
	</head>
	<body>
		<div class="jumbotron">
			<div class="container">
				<jsp:include page="langSelector.jsp"/>
				<h1><c:out value="${greeting}"/></h1>
				<p><c:out value="${tagline}"/></p>
			</div>
		</div>
	</body>
</html>