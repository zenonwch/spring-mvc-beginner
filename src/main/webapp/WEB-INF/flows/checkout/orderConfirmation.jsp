<%--@elvariable id="order" type="my.spring.project.springmvc.domain.Order"--%>
<%--@elvariable id="flowExecutionKey" type="java.lang.String"--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:message code="app.language" scope="page" var="locale"/>
<c:choose>
	<c:when test="${'be' == locale || 'ru' == locale}">
		<c:set var="pattern" value="d MMMM yyyy"/>
	</c:when>
	<c:otherwise>
		<c:set var="pattern" value="MMMM d, yyyy"/>
	</c:otherwise>
</c:choose>
<%--suppress JspUnescapedEl --%>
<javatime:format value="${order.shippingDetail.shippingDate}"
                 pattern="${fn:escapeXml(pattern)}" var="shippingDate"/>

<html lang="<c:out value="${locale}"/>">
	<head>
		<jsp:include page="../../views/components/head.jsp"/>
		<title><spring:message code="orderConfirmation.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="../../views/components/langSelector.jsp"/>
					<h1><spring:message code="orderConfirmation.page.header"/></h1>
					<p><spring:message code="orderConfirmation.page.description"/></p>
				</div>
			</div>
		</section>
		<div class="container">
			<div class="row">
				<form:form modelAttribute="order" cssClass="form-horizontal">
					<input type="hidden" name="_flowExecutionKey" value="<c:out value="${flowExecutionKey}"/>">
					<div class="card">
						<div class="card-body bg-light">
							<div class="text-center">
								<h1><spring:message code="orderConfirmation.receipt.title"/></h1>
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<address>
										<strong>
											<spring:message code="orderConfirmation.receipt.shippingAddress"/>
										</strong><br>
										<c:out value="${order.shippingDetail.name}"/><br>
										<c:out value="${order.shippingDetail.shippingAddress.doorNo}, ${order.shippingDetail.shippingAddress.streetName}"/><br>
										<c:out value="${order.shippingDetail.shippingAddress.areaName}, ${order.shippingDetail.shippingAddress.state}"/><br>
										<c:out value="${order.shippingDetail.shippingAddress.country}, ${order.shippingDetail.shippingAddress.zipCode}"/><br>
									</address>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6 text-right">
									<p><em>
										<spring:message code="orderConfirmation.receipt.shippingDate"/>: <!--
										--><c:out value="${shippingDate}"/>
									</em></p>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<address>
										<strong><spring:message
												code="orderConfirmation.receipt.billingAddress"/></strong><br>
										<c:out value="${order.buyer.name}"/><br>
										<c:out value="${order.buyer.billingAddress.doorNo}, ${order.buyer.billingAddress.streetName}"/><br>
										<c:out value="${order.buyer.billingAddress.areaName}, ${order.buyer.billingAddress.state}"/><br>
										<c:out value="${order.buyer.billingAddress.country}, ${order.buyer.billingAddress.zipCode}"/><br>
										<abbr title="<spring:message code="orderConfirmation.receipt.phone.abbr.title"/>" class="initialism"><spring:message
												code="orderConfirmation.receipt.phone.abbr"/></abbr>: <!--
									--><c:out value="${order.buyer.phoneNumber}"/>
									</address>
								</div>
							</div>
							<div class="row">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>
												<spring:message code="orderConfirmation.table.header.product"/>
											</th>
											<th>#</th>
											<th class="text-center">
												<spring:message code="orderConfirmation.table.header.price"/>
											</th>
											<th class="text-center">
												<spring:message code="orderConfirmation.table.header.total"/>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="cartItem" items="${order.cart.cartItems}">
											<tr>
												<td class="col-md-9"><em><c:out value="${cartItem.product.name}"/></em>
												</td>
												<td class="col-md-1"
												    style="text-align: center;">${cartItem.quantity}</td>
												<td class="col-md-1 text-center">$<c:out
														value="${cartItem.product.unitPrice}"/></td>
												<td class="col-md-1 text-center">$<c:out
														value="${cartItem.totalPrice}"/></td>
											</tr>
										</c:forEach>
										<tr>
											<td colspan="3" class="text-right">
												<h4><strong><spring:message
														code="orderConfirmation.table.header.grandTotal"/>: </strong>
												</h4>
											</td>
											<td class="text-center text-danger">
												<h4><strong>$<c:out value="${order.cart.grandTotal}"/></strong></h4>
											</td>
										</tr>
									</tbody>
								</table>
								<div class="container">
									<jsp:include page="../../views/components/buttonCheckoutBack.jsp">
										<jsp:param name="event" value="_eventId_backToCollectShippingDetail"/>
									</jsp:include>
									<jsp:include page="../../views/components/buttonConfirmOrder.jsp"/>
									<jsp:include page="../../views/components/buttonCancelOrder.jsp"/>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</body>
</html>