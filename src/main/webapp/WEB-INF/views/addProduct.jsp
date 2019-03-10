<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<title><spring:message code="products.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="components/langSelector.jsp"/>
					<h1><spring:message code="products.page.title"/></h1>
					<p><spring:message code="addProduct.page.description"/></p>
					<jsp:include page="components/navigation.jsp"/>
				</div>
			</div>
		</section>
		<section class="container">
			<form:form method="post" modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
				<form:errors path="*" cssClass="alert alert-danger col-lg-8" element="div"/>
				<c:set var="messages"> {
					'valueMissing':'<spring:message code="invalid.field.text.empty"/>',
					'rangeUnderflow':'<spring:message code="invalid.field.number.negative"/>',
					'badInput':'<spring:message code="invalid.field.number.NaN"/>',
					}
				</c:set>
				<fieldset>
					<legend class="col-lg-8 border-bottom pl-0 mb-4">
						<spring:message code="addProduct.form.legend"/>
					</legend>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="productId">
							<spring:message code="addProduct.form.productId.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="productId" id="productId" type="text" class="form-control"
							            required="true" oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="productId" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="name">
							<spring:message code="addProduct.form.name.label"/>
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
						<label class="col-form-label col-lg-2 text-right" for="description">
							<spring:message code="addProduct.form.desc.label"/>
						</label>
						<div class="col-lg-3">
							<form:textarea path="description" id="description" rows="3" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="unitPrice">
							<spring:message code="addProduct.form.price.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="unitPrice" id="unitPrice" type="number" class="form-control"
							            required="true" value="0" min="0" oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="unitPrice" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="manufacturer">
							<spring:message code="addProduct.form.brand.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="manufacturer" id="manufacturer" type="text" class="form-control"
							            required="true" oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="manufacturer" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="category">
							<spring:message code="addProduct.form.category.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="category" id="category" type="text" class="form-control"
							            required="required" oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="category" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right" for="unitsInStock">
							<spring:message code="addProduct.form.unitsInStock.label"/>
						</label>
						<div class="col-lg-3">
							<form:input path="unitsInStock" id="unitsInStock" type="number" class="form-control"
							            min="0" oninput="onInput(this)"
							            oninvalid="onInvalid(this, ${messages})"/>
						</div>
						<div class="col-lg-3 px-0">
							<form:errors path="unitsInStock" cssClass="text-danger"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-lg-2 text-right">
							<spring:message code="addProduct.form.condition.label"/>
						</label>
						<div class="col-lg-3">
							<div class="form-check form-check-inline">
								<form:radiobutton path="condition" id="conditionNew" value="New"
								                  cssClass="form-check-input" checked="checked"/>
								<spring:message code="addProduct.form.condition.new.label"/>
							</div>
							<div class="form-check form-check-inline">
								<form:radiobutton path="condition" id="conditionOld" value="Old"
								                  cssClass="form-check-input"/>
								<spring:message code="addProduct.form.condition.old.label"/>
							</div>
							<div class="form-check form-check-inline">
								<form:radiobutton path="condition" id="conditionRef" value="Refurbished"
								                  cssClass="form-check-input"/>
								<spring:message code="addProduct.form.condition.ref.label"/>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right">
							<spring:message code="addProduct.form.productImg.label"/>
						</label>
						<div class="col-lg-3">
							<jsp:include page="components/inputFileUpload.jsp">
								<jsp:param name="path" value="productImage"/>
								<jsp:param name="fileType" value="image/jpeg,image/gif,image/png"/>
								<jsp:param name="buttonName" value="addProduct.form.productImg.button"/>
								<jsp:param name="placeholder" value="addProduct.form.productImg.placeholder"/>
							</jsp:include>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-lg-2 text-right">
							<spring:message code="addProduct.form.productGuide.label"/>
						</label>
						<div class="col-lg-3">
							<jsp:include page="components/inputFileUpload.jsp">
								<jsp:param name="path" value="productGuide"/>
								<jsp:param name="fileType" value="application/pdf"/>
								<jsp:param name="buttonName" value="addProduct.form.productGuide.button"/>
								<jsp:param name="placeholder" value="addProduct.form.productGuide.placeholder"/>
							</jsp:include>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-lg-2 col-lg-3">
							<input type="submit" id="btnAdd" class="btn btn-primary"
							       value="<spring:message code="addProduct.form.submit.button"/>"/>
							<jsp:include page="components/buttonBack.jsp">
								<jsp:param name="backUrl" value="/market/products"/>
							</jsp:include>
						</div>
					</div>
				</fieldset>
			</form:form>
		</section>
		<jsp:include page="scripts/scriptFormValidation.jsp"/>
		<jsp:include page="scripts/scriptFileUpload.jsp"/>
	</body>
</html>