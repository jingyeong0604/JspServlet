<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>첫번째 페이지입니다...........</h1>
<!--주소창은 a이고 b를 요청하는것. -->
<!--<jsp:forward page="b.jsp"></jsp:forward>
첫번쨰 요청 -> 두번째가 보임 : 포워드!
-->
<%--<%
별로 좋은 코드는 아님. ->jsp에서의 java 사용은 추천되지 않음.
RequestDispatcher rd = request.getRequestDispatcher("b.jsp");
//rd.forward(request, response);
rd.include(request, response);
%>--%>
<jsp:include page="b.jsp"></jsp:include>
</body>
</html>