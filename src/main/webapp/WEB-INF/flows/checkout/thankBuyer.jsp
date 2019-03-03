<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
		<title><spring:message code="orderSuccess.page.title"/></title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<jsp:include page="../../views/components/langSelector.jsp"/>
					<h1 class="alert alert-success"><spring:message code="orderSuccess.page.header"/></h1>
					<p><spring:message code="orderSuccess.page.message.1"
					                   arguments="${shippingDate}" argumentSeparator="%%%"/></p>
					<p><spring:message code="orderSuccess.page.message.2"
					                   arguments="${order.orderId}"/></p>
				</div>
			</div>
		</section>
		<section>
			<div class="container">
				<p>
					<jsp:include page="../../views/components/buttonProducts.jsp"/>
				</p>
			</div>
		</section>
	</body>
</html>