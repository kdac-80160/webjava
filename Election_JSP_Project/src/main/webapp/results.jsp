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
	<jsp:useBean id="rb" class="com.sunbeam.beans.ResultsBean"></jsp:useBean>
	${rb.getCandidates()}
	<c:forEach var="c" items="${rb.list}">
		<div style="border: 1px solid black; width: 400px; align-items: center; border-color: black;">
			Candidate : ${c.name} <br/>
			Party : ${c.party} <br/>
			Votes : ${c.votes} <br/>
			Action : <a href='editcand.jsp?id=${c.id}'>Edit</a> | <a href='deletecand.jsp?id=${c.id}'>Delete</a>	
		</div>
	</c:forEach>
	<br/>
	<a href="logout.jsp">Logout</a>	
	${param.ucbMessage}
	${param.dcbMessage}
</body>
</html>