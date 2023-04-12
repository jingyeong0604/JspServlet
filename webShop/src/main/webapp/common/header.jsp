<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath}" scope="application"/>
<%
String company="신한금융소프트아카데미";
%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	<script>
	$(function() {
		$("#btnLogout").on("click", function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/auth/logout.do",
				success : function(responseData) {
					alert(responseData+ "로그아웃되었습니다. ");
					location.href="<%=request.getContextPath()%>/auth/loginCheck.do";
				},
				error : function(message) {
					alert(message);
					console.log(message);
				}
			});
		});

		//#container > table > tbody > tr:nth-child(1) > td:nth-child(2)
	});
</script>
<div style="border:5px dotted green;">
	<div>로그인한 사용자: ${loginUser.manager_name==null?"guest":loginUser.manager_name}</div>
	<div>세션에서 로그인한 사용자 읽기(EL): ${sessionScope.loginUser}</div>
	<%-- <div>
		세션에서 로그인한 사용자 읽기(ScriptLet):
		<%=session.getAttribute("loginUser")%></div>
		 --%>
	<c:if test="${loginUser!=null }">
		<input type="button" value="로그아웃" id="btnLogout">
	</c:if>
	<c:if test="${loginUser==null }">
		<button onclick="location.href='${path}/auth/loginCheck.do'">로그인</button>
	</c:if>


	<img src="../images/umbrella.jpg" width="200" height="150">
</div>