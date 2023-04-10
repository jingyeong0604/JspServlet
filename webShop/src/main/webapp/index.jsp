<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3;auth/login.jsp">
<title>Insert title here</title>
</head>
<body>
<h1>JSP?Servlet Project@@@@@@@@@@</h1>
<!-- application, session, request영역 연습 -->
<p>application: ${mydata1} :</p>
<p>session : ${mydata2}</p>
<p>request : ${mydata3}</p>
<p>가까운곳 request>session>application : ${myname}</p> <!-- 좁은 영역을 먼저 사용함. -->
<p>applicationScope : ${applicationScope.myname}</p> <!-- 좁은 영역을 먼저 사용함. -->
<p>sessionScope : ${sessionScope.myname}</p> <!-- 좁은 영역을 먼저 사용함. -->
<p>requestScope : ${requestScope.myname}</p> <!-- 좁은 영역을 먼저 사용함. -->

<h2>getInitParameter TEST</h2>
<p><%=application.getInitParameter("DB_userid") %></p>

<ul>
<li><a href="first">첫번째 서블릿 요청(a tag가 있으므로 get)</a>

<li>
<form action="first" method="get">
	<input type="submit" value="get요청">
</form>
</li>
<li>


<form action="first" method="post">
	<input type="submit" value="post요청">
</form>
</li>
<li>
	<form action="param" method="get">
		<input type="text" name="useri d"><br>
		<input type="number" name="salary"><br>
		남자:<input type="radio" name="gender" value="Male"><br>
		여자:<input type="radio" name="gender" value="Female"><br>
		hobby:
		게임<input type="checkbox" name="hobby" value="게임"><br>
		족구<input type="checkbox" name="hobby" value="족구"><br>
		농구<input type="checkbox" name="hobby" value="농구"><br>
		독서<input type="checkbox" name="hobby" value="독서"><br>
		<input type="submit" value="get요청">
	</form>
</li>
</ul>
<a href="auth/login.jsp">로그인하기(상대경로)</a>
<a href="<%=request.getContextPath() %>/auth/loginCheck.do">로그인하기(절대경로)</a><!-- jsp로 직접안감!!!!!!!! -->
<h2>직원관리</h2>
<a href="<%=request.getContextPath() %>/emp/emplist.do">직원조회</a>
<a href="<%=request.getContextPath() %>/emp/empinsert.do">직원등록</a><!-- get방식 -->
</body>
</html>