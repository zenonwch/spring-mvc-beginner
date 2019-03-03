<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<button id="back" class="btn btn-secondary mr-2" name="<c:out value="${param.event}"/>">
	<spring:message code="checkoutFlow.back.button"/>
</button>