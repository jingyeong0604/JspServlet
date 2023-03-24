<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//jsp = 자바로 작성한 서버프ㅗ그램
//jsp = java+html
request.setCharacterEncoding("utf-8");
String username = request.getParameter("username");
String useremail =request.getParameter("useremail");
String userid = request.getParameter("userid");
String pwd = request.getParameter("pwd");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Form연습하기</h1>
<table border ="1">
	<tr>
		<td>아이디</td>
		<td><%=userid %></td>
		</tr>
		<tr>
		<td>패스워드</td>
		<td><%=pwd%></td>
		
	</tr>
		<tr>
		<td>이메일</td>
		<td><%=useremail %></td>
		
	</tr>
		<tr>
		<td>이름</td>
		<td><%=username %></td>
		
	</tr>
</table>
</body>
</html>