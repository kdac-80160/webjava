<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="dcb" class="com.sunbeam.beans.DeleteCandBean"></jsp:useBean>
	<jsp:setProperty property="candId" name="dcb" param="id"/>
	${dcb.delCandidate()}
	<jsp:forward page="results.jsp">
	<jsp:param value="${dcb.message}" name="dcbMessage"/>
	</jsp:forward>
</body>
</html>