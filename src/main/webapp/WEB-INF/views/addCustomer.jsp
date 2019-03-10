<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
					<p><spring:message code="addCustomer.page.description"/></p>
					<jsp:include page="components/navigation.jsp"/>
				</div>
			</div>
		</section>
		<section class="container">
			<form:form method="post" modelAttribute="newCustomer" cssClass="form-horizontal" acceptCharset="UTF-8"
			           servletRelativeAction="">
				<form:errors path="*" cssClass="alert alert-danger col-lg-8" element="div"/>
				<c:set var="messages"> {
					'valueMissing':'<spring:message code="invalid.field.text.empty"/>',
					'rangeUnderflow':'<spring:message code="invalid.field.number.negative"/>',
					'badInput':'<spring:message code="invalid.field.number.NaN"/>',
					}
				</c:set>
				<fieldset>
					<legend class="col-lg-8 border-bottom pl-0 mb-4">
						<spring:message code="addCustomer.form.legend"/>
					</legend>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="customerId">
							<spring:message code="addCustomer.form.customerId.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="customerId" id="customerId" type="text" class="form-control"
							            required="true" oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="customerId" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="name">
							<spring:message code="addCustomer.form.name.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="name" id="name" type="text" class="form-control"
							            required="true" oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="name" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="address">
							<spring:message code="addCustomer.form.address.label"/>
						</label>
						<div class="col-lg-3">
							<form:textarea path="address" id="address" rows="3" class="form-control"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="address" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="ordersMade">
							<spring:message code="addCustomer.form.ordersMade.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="noOfOrdersMade" id="ordersMade" type="number"
							            class="form-control" required="true" min="0"
							            oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="noOfOrdersMade" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-lg-2 col-lg-3">
							<input type="submit" id="btnAdd" class="btn btn-primary"
							       value="<spring:message code="addCustomer.form.submit.button"/>"/>
							<jsp:include page="components/buttonBack.jsp">
								<jsp:param name="backUrl" value="/customers"/>
							</jsp:include>
						</div>
					</div>
				</fieldset>
			</form:form>
		</section>
		<jsp:include page="scripts/scriptFormValidation.jsp"/>
	</body>
</html>