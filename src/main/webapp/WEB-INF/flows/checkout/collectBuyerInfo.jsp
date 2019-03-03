<%--@elvariable id="flowExecutionKey" type="java.lang.String"--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="messages"> {
	'valueMissing':'<spring:message code="invalid.field.text.empty"/>',
	}
</c:set>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="../../views/components/head.jsp"/>
		<title><spring:message code="buyerInfo.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="../../views/components/langSelector.jsp"/>
					<h1><spring:message code="buyerInfo.page.title"/></h1>
					<p><spring:message code="buyerInfo.page.description"/></p>
				</div>
			</div>
		</section>
		<section class="container">
			<%--@elvariable id="order.buyer" type="my.spring.project.springmvc.domain.Buyer"--%>
			<form:form modelAttribute="order.buyer" cssClass="form-horizontal">
				<fieldset>
					<legend class="col-lg-8 border-bottom pl-0 mb-4">
						<spring:message code="buyerInfo.form.legend"/>
					</legend>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="name">
							<spring:message code="buyerInfo.form.name.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="name" id="name" type="text"
							            class="form-control" required="required"
							            oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="doorNo">
							<spring:message code="address.form.doorNo.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="billingAddress.doorNo" id="doorNo"
							            type="text" class="form-control" required="required"
							            oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="streetName">
							<spring:message code="address.form.streetName.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="billingAddress.streetName" id="streetName"
							            type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="areaName">
							<spring:message code="address.form.areaName.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="billingAddress.areaName" id="areaName"
							            type="text" class="form-control" required="required"
							            oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="state">
							<spring:message code="address.form.state.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="billingAddress.state" id="state"
							            type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="country">
							<spring:message code="address.form.country.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="billingAddress.country" id="country"
							            type="text" class="form-control" required="required"
							            oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="zipCode">
							<spring:message code="address.form.zipCode.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="billingAddress.zipCode" id="zipCode"
							            type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="phoneNumber">
							<spring:message code="buyerInfo.form.phoneNumber.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="phoneNumber" id="phoneNumber"
							            type="text" class="form-control"/>
						</div>
					</div>
					<input type="hidden" name="_flowExecutionKey" value="<c:out value="${flowExecutionKey}"/>">
					<div class="form-group row">
						<div class="offset-lg-2 col-lg-3" style="font-size:0px;">
							<jsp:include page="../../views/components/buttonCheckoutAdd.jsp">
								<jsp:param name="event" value="_eventId_buyerInfoCollected"/>
							</jsp:include>
							<jsp:include page="../../views/components/buttonCancelOrder.jsp"/>
						</div>
					</div>
				</fieldset>
			</form:form>
		</section>
		<jsp:include page="../../views/scripts/scriptFormValidation.jsp"/>
	</body>
</html>