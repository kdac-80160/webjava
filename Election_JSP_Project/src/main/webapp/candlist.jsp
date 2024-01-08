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
	<h3>Hello ${lb.user.firstName} ${lb.user.lastName}</h3>
	<hr />
	<jsp:useBean id="clb" class="com.sunbeam.beans.CandListBean"></jsp:useBean>
	<jsp:setProperty property="candId" name="clb" value="${lb.user.id}" />
	${clb.getCandidates()}
	<form action='vote.jsp' method="post">
	<c:forEach var="c" items="${clb.list}">
		<div>
			<input type='radio' name='candId' value="${c.id}" /> ${c.name} - ${c.party}
		</div>
	</c:forEach>
	<input type="submit" value="Submit"/>
	</form>
</body>
</html>