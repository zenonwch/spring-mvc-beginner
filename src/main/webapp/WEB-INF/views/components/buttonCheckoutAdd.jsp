<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<button type="submit" id="btnAdd" class="btn btn-primary mr-2" name="<c:out value="${param.event}"/>">
	<spring:message code="checkoutFlow.add.button"/>
</button>