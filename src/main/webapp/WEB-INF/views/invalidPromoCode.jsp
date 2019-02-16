<%--@elvariable id="message" type="my.spring.project.springmvc.controller.ExceptionsHandler"--%>
<%--@elvariable id="exception" type="my.spring.project.springmvc.controller.ExceptionsHandler"--%>
<%--@elvariable id="url" type="my.spring.project.springmvc.controller.ExceptionsHandler"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
		<title><spring:message code="invalidPromoCode.page.title"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include page="head.jsp"/>
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
				<a href="<spring:url value="/market/products"/>" class="btn btn-primary btn-large float-right">
					<span>
						<i class="far fa-hand-pointer rotate-270-flip-horizontal"></i>
						<spring:message code="product.card.back.button"/>
					</span>
				</a>
			</div>
		</section>
	</body>
</html>