<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
   <spring:url value="/users" var="userActionUrl" />
   <form:form method="post" modelAttribute="userForm" action="${userActionUrl}">
   		<div>
		<form:input path="userName" type="text" /> <!-- bind to user.name-->
		<form:errors style="color:red" path="userName" />
		</div>
		
		<div>
		<input type="submit" style="margin-left:2%"value="submit" >
		</div>
	</form:form>
</body>
</html>