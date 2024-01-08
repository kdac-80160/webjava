<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:useBean id="pb" class="beans.AddPlayerBean"></jsp:useBean>
<jsp:setProperty property="*" name="pb"/>
<body>
<h4>${pb.validateAndAddPlayer()}</h4>
<a href='add_player_form.jsp'>Add Another Player</a>
</body>
</html>