<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="float-right" style="margin-top: -3rem;">
	<a href="?language=en">
		<img src="<c:url value="/resources/icons/blank.gif"/>" class="flag flag-gb"
		     alt="EN" title="<spring:message code="lang.en.tooltip"/>"/></a>
	<a href="?language=ru">
		<img src="<c:url value="/resources/icons/blank.gif"/>" class="flag flag-ru"
		     alt="RU" title="<spring:message code="lang.ru.tooltip"/>"/></a>
	<a href="?language=be">
		<img src="<c:url value="/resources/icons/blank.gif"/>" class="flag flag-by"
		     alt="BY" title="<spring:message code="lang.be.tooltip"/>"/></a>
</div>