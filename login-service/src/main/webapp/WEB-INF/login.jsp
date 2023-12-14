<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="/css/login.css">

</head>
<body>

	<div class="login-container">
		<div id=logoutmessage>
			<c:if test="${param.logout!=null}">
				<i style="color: red;">You are signed out! login again</i>
			</c:if>
		</div>
		<h2>LOGIN</h2>
		<c:if test="${param.error!=null}">
			<i style="color: red; margin-left: 5%; font-size: 14px;">Invalid
				Username and Password !</i>
		</c:if>

		<form:form action="login">
			<table>
				<tr>
					<td><input type="text" name="username" placeholder="username"
						required><br></td>
				</tr>
				<tr>
					<td><input type="password" name="password"
						placeholder="Password" required><br></td>
				</tr>
				<tr>
					<td><button type="submit">Submit</button></td>
				</tr>
				<tr>
				</tr>
			</table>
		</form:form>
	</div>
	<div id="footer"><jsp:include page="footer.jsp" /></div>
</body>
</html>