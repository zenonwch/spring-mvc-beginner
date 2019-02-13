<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
	<head>
		<title>Welcome</title>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="shortcut icon" href="<c:url value="/resources/favicon.ico" />" type="image/x-icon">
		<link rel="icon" href="<c:url value="/resources/favicon.ico" />" type="image/x-icon">
	</head>
	<body>
		<div class="jumbotron">
			<h1><c:out value="${greeting}"/></h1>
			<p><c:out value="${tagline}"/></p>
		</div>
	</body>
</html>