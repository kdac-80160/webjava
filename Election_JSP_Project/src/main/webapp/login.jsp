<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="lb" class="com.sunbeam.beans.LoginBean" scope="session" />
	<jsp:setProperty property="*" name="lb" />
	${lb.authenticate()}

	<c:choose>

		<c:when test="${empty lb.user}">
			<h4>Wrong credentials, please try again..</h4>
			<br />
			<a href='index.jsp'>Login</a>
		</c:when>

		<c:when test="${lb.user.role == 'admin'}">
			<c:redirect url="results.jsp"></c:redirect>
		</c:when>

		<c:when test="${lb.user.role == 'voter'}">
			<c:redirect url="candlist.jsp"></c:redirect>
		</c:when>

		<c:otherwise>
			<c:redirect url="index.jsp"></c:redirect>
		</c:otherwise>

	</c:choose>
</body>
</html>