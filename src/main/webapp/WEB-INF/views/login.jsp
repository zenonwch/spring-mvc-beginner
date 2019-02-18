<%--@elvariable id="error" type="my.spring.project.springmvc.controller.LoginController"--%>
<%--@elvariable id="accessDenied" type="my.spring.project.springmvc.controller.LoginController"--%>
<%--@elvariable id="logout" type="my.spring.project.springmvc.controller.LoginController"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
	<head>
		<title><spring:message code="welcome.page.title"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include page="head.jsp"/>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="langSelector.jsp"/>
					<h1><spring:message code="welcome.page.greeting"/></h1>
					<p><spring:message code="welcome.page.tagline"/></p>
				</div>
			</div>
		</section>
		<section class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-4">
					<div class="card">
						<div class="card-header">
							<h5 class="mb-0"><spring:message code="login.form.title"/></h5>
						</div>
						<div class="card-body">
							<c:url var="loginUrl" value="/login"/>
							<form action="<c:out value="${loginUrl}"/>" method="post" class="form-horizontal">
								<c:if test="${error}">
									<div class="alert alert-danger text-center">
										<span><spring:message code="login.form.login.invalid"
										                      htmlEscape="false"/></span>
									</div>
								</c:if>
								<c:if test="${logout}">
									<div class="alert alert-success text-center">
										<span><spring:message code="login.form.logout.success"
										                      htmlEscape="false"/></span>
									</div>
								</c:if>

								<c:if test="${accessDenied}">
									<div class="alert alert-danger text-center">
										<span><spring:message code="login.form.login.notAuthorized"
										                      htmlEscape="false"/></span>
									</div>
								</c:if>

								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="user-name-prepend">
											<i class="fa fa-user"></i>
										</span>
									</div>
									<input type="text" class="form-control" id="username" name="username"
									       aria-describedby="user-name-prepend" required
									       placeholder="<spring:message code="login.form.username.placeholder"/>"
									       aria-label="<spring:message code="login.form.username.placeholder"/>">
								</div>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="user-pass-prepend">
										 <i class="fa fa-lock"></i>
										</span>
									</div>
									<input type="password" class="form-control" id="password" name="password"
									       aria-describedby="user-pass-prepend" required
									       placeholder="<spring:message code="login.form.password.placeholder"/>"
									       aria-label="<spring:message code="login.form.password.placeholder"/>">
								</div>
								<div class="form-actions">
									<input type="submit" class="btn btn-block btn-primary"
									       value="<spring:message code="login.form.login.button"/>">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
</html>