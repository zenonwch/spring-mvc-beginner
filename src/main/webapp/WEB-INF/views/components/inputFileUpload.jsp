<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:message code="${param.placeholder}" scope="page" var="placholder"/>
<spring:message code="${param.buttonName}" scope="page" var="buttonName"/>

<div class="custom-file">
	<form:input path="${param.path}" id="${param.path}" type="file"
	            cssClass="custom-file-input" accept="${param.fileType}"
	            onchange="setFileInputPlaceholder('${param.path}')"/>
	<label id="<c:out value="${param.path}"/>Label" for="<c:out value="${param.path}"/>" class="custom-file-label"
	       data-browse="<c:out value="${buttonName}"/>"><c:out value="${placholder}"/></label>
</div>