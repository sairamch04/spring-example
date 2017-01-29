<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<body>
		<spring:url value="/users/add" var="loginUrl" />
		Hi, Welcome to Demo. Please <a href="${loginUrl }">Login here </a>
	</body>
	
</html>