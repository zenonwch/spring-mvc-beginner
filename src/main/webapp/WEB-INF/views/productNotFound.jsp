<%--@elvariable id="message" type="my.spring.project.springmvc.controller.ExceptionsHandler"--%>
<%--@elvariable id="exception" type="my.spring.project.springmvc.controller.ExceptionsHandler"--%>
<%--@elvariable id="url" type="my.spring.project.springmvc.controller.ExceptionsHandler"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
	<head>
		<title><spring:message code="product.notFound.page.title"/></title>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<jsp:include page="head.jsp"/>
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
				<a href="<spring:url value="/market/products"/>" class="btn btn-primary btn-large">
					<span>
						<i class="far fa-hand-pointer rotate-270-flip-horizontal"></i>
						<spring:message code="product.card.back.button"/>
					</span>
				</a>
			</div>
		</section>
	</body>
</html>