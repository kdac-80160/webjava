<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="ecb" class='com.sunbeam.beans.EditCandBean'></jsp:useBean>
<jsp:setProperty property="candId" name="ecb" param="id"/>
${ecb.findCandidate()}
	<div>
	<form action='updatecand.jsp' method='post'>
	<input type="hidden" name="candId" value="${ecb.candidate.id}"/>
	Name : <input type="text" name="name" value="${ecb.candidate.name}"/> <br/> <br/>
	Party : <input type="text" name='party' value='${ecb.candidate.party}'/> <br/> <br/>
	Votes : <input type="text" name='votes' value='${ecb.candidate.votes}' readonly/> <br/> <br/>
	<input type="submit" value="Submit"/>
	</form>
	</div>
</body>
</html>