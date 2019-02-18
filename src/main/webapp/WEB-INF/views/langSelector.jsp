<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="float-right" style="margin-top: -3rem;">
	<c:url var="newUrl" value="?">
		<c:forEach items="${param}" var="entry">
			<c:if test="${entry.key != 'language'}">
				<c:param name="${entry.key}" value="${entry.value}"/>
			</c:if>
		</c:forEach>
	</c:url>
	<c:if test="${newUrl != '?'}">
		<c:set var="newUrl" value="${newUrl}&"/>
	</c:if>

	<a href="<c:out value="${newUrl}"/>language=en">
		<img src="<c:url value="/resources/icons/blank.gif"/>" class="flag flag-gb"
		     alt="EN" title="<spring:message code="lang.en.tooltip"/>"/></a>
	<a href="<c:out value="${newUrl}"/>language=ru">
		<img src="<c:url value="/resources/icons/blank.gif"/>" class="flag flag-ru"
		     alt="RU" title="<spring:message code="lang.ru.tooltip"/>"/></a>
	<a href="<c:out value="${newUrl}"/>language=be">
		<img src="<c:url value="/resources/icons/blank.gif"/>" class="flag flag-by"
		     alt="BY" title="<spring:message code="lang.be.tooltip"/>"/></a>
	<sec:authorize access="isAuthenticated()">
		<a href="<spring:url value="/logout"/>"><spring:message code="logout.button"/></a>
	</sec:authorize>
</div>