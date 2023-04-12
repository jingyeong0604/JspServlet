<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
</head>
<body>

<h1>JavaBean이용하기</h1>
<%
/*String a= request.getParameter("email");
String b = request.getParameter("manager_name");
String c = request.getParameter("pass");*/

//AdminVO vo = new AdminVO(a, b, c);이거 대신에 userBean 사용할 수 있음!
%>
<!--default 생성자를 이용! AdminVO에 default생성자가 있어야함. 
useBean: 객체 생성!!!!!!
setProperTy: setter수행, "set"+"Email"=>setEmail)_
-->
<jsp:useBean id="vo" class="com.shinhan.vo.AdminVO">
</jsp:useBean>
<jsp:setProperty property="email" name="vo"/><!-- 이름은 변수 이름 -->
<jsp:setProperty property="manager_name" name="vo" /><!-- 이름은 변수 이름 -->
<jsp:setProperty property="pass" name="vo" /><!-- 이름은 변수 이름 -->


<jsp:useBean id="vo2" class="com.shinhan.vo.AdminVO"></jsp:useBean>
<jsp:setProperty property="*" name="vo2" /><!-- 이름은 변수 이름 -->



email : <jsp:getProperty property="pass" name="vo" /><br>
name : <jsp:getProperty property="manager_name" name="vo" /><br>
pass : <jsp:getProperty property="pass" name="vo" /><br>



<%=vo %>
</body>
</html>