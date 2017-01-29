<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
	<title>Welcome ${user.userName }</title>
</head>
<body>
	<spring:url value="/users/add" var="loginUrl" />
	<c:choose>
	
	<c:when test="${empty user }">
		
		The user is not available. <br>
		Login here : <a href='${loginUrl}'> login here </a>
	</c:when>
	<c:when test="${ userAdded  == true }">
		 Hi ${user.userName}, <br>
		 Sorry ! your record was not found. <br>
	     But not to worry, you have been registered.
		
	</c:when>
	<c:otherwise>
	
		Welcome ${user.userName} <br>
		You last logged in at ${user.lastUsageTime}
	</c:otherwise>
	</c:choose>
    
</body>
</html>