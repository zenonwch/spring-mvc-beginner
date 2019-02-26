<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
	<div class="row align-items-center float-right" style="margin-top: -3rem;">
		<input type="image" src="<c:url value="/resources/icons/blank.gif"/>"
		       class="flag flag-gb" alt="EN" title="<spring:message code="lang.en.tooltip"/>"
		       onclick="changeLocale('en')">
		<input type="image" src="<c:url value="/resources/icons/blank.gif"/>"
		       class="flag flag-ru" alt="RU" title="<spring:message code="lang.ru.tooltip"/>"
		       onclick="changeLocale('ru')">
		<input type="image" src="<c:url value="/resources/icons/blank.gif"/>"
		       class="flag flag-by" alt="BY" title="<spring:message code="lang.be.tooltip"/>"
		       onclick="changeLocale('be')">
		<sec:authorize access="isAuthenticated()">
			<input type="button" class="btn btn-login btn-sm ml-2 px-3"
			       value="<spring:message code="logout.button"/>"
			       onclick="location.href='<spring:url value="/logout"/>'"/>
		</sec:authorize>
	</div>
</div>
<jsp:include page="../scripts/scriptChangeLocale.jsp"/>