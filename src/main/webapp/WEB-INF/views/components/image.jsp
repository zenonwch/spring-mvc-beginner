<%--suppress JSUnusedGlobalSymbols --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="noImage" value="/img/NoImage.png"/>

<img class="img-fluid pt-3"
     style="<c:out value="${param.style}"/>"
     src="<c:url value="/img/${param.imageFileName}"/>"
     onerror="this.onerror=null; this.src='<c:url value="/img/${noImage}"/>'"
     alt="<c:out value="${param.imageName}"/>"/>