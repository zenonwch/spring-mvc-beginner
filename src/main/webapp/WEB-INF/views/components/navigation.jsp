<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<ul class="nav float-right">
	<li class="nav-item">
		<a class="nav-link text-secondary" href="<spring:url value="/market/products"/>"><spring:message code="navigation.link.products"/></a>
	</li>
	<li class="nav-item">
		<a class="nav-link text-secondary" href="<spring:url value="/customers"/>"><spring:message code="navigation.link.customers"/></a>
	</li>
	<li class="nav-item">
		<a class="nav-link text-secondary" href="<spring:url value="/cart"/>"><spring:message code="navigation.link.cart"/></a>
	</li>
	<c:if test="${param.page != null}">
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle text-secondary" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
				<spring:message code="navigation.link.add"/>
			</a>
			<div class="dropdown-menu">
				<c:if test="${param.page == 'products'}">
					<a class="dropdown-item text-secondary" href="<spring:url value="/market/products/add"/>"><spring:message code="navigation.link.add.product"/></a>
				</c:if>
				<c:if test="${param.page == 'customers'}">
					<a class="dropdown-item text-secondary" href="<spring:url value="/customers/add"/>"><spring:message code="navigation.link.add.customer"/></a>
				</c:if>
			</div>
		</li>
	</c:if>
</ul>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap.native/2.0.25/bootstrap-native-v4.min.js"></script>