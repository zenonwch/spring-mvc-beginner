<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<a href="<spring:url value="${param.backUrl}"/>" class="btn btn-light btn-large border text-secondary">
	<span>
		<i class="far fa-hand-pointer rotate-270-flip-horizontal"></i>
		<spring:message code="back.button"/>
	</span>
</a>