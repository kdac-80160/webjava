<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h3>Hello ${lb.user.firstName} ${lb.user.lastName}</h3>
		<hr />
		<jsp:useBean id="vb" class="com.sunbeam.beans.VoteBean"></jsp:useBean>
		<jsp:setProperty property="*" name="vb" />
		<jsp:setProperty property="userId" name="vb" value="${lb.user.id}" />
		${vb.registerVote()}
		<h4>${vb.message}</h4>
		<a href="logout.jsp">Logout</a>
	</div>
</body>
</html>