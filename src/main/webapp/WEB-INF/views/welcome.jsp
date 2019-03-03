<%--@elvariable id="greeting" type="java.lang.String"--%>
<%--@elvariable id="tagline" type="java.lang.String"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="<spring:message code="app.language"/>">
	<head>
		<jsp:include page="components/head.jsp"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="welcome.page.title"/></title>
	</head>
	<body>
		<div class="d-flex flex-column vh-100">
			<div class="jumbotron m-0">
				<div class="container">
					<jsp:include page="components/langSelector.jsp"/>
					<h1><c:out value="${greeting}"/></h1>
					<p><c:out value="${tagline}"/></p>
				</div>
			</div>
			<div class="container d-flex flex-grow-1 flex-column">
				<div class="row h-100 justify-content-center">
					<div class="alert alert-success col-6 h-25 align-self-center" style="min-height: 150px;">
						<div class="row h-100 align-items-center text-center">
							<div class="col">
								<h2>
									<spring:message code="welcome.page.timer" arguments="<span id='sec'>5</span>"/>
								</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script>
        let sec = document.getElementById("sec");

        setInterval(function () {
            if (sec.innerText === '1') {
                window.location.href = '<spring:url value="/market/products"/>';
            } else {
                sec.innerText = --sec.innerText;
            }
        }, 1000);
	</script>
</html>