<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<a href="<spring:url value="/market/products" />" class="btn btn-primary btn-large">
	<span>
		<i class="far fa-hand-pointer rotate-270-flip-horizontal"></i>
		<spring:message code="checkoutFlow.products.button"/>
	</span>
</a>