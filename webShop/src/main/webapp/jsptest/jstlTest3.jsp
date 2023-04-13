<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!--  jstlController에서 읽어옴. -->
<!-- jstl을 쓰겠다고 선언! 일반적으로 c를 줌. c안에 들어있는 것은 jstl을 사용한 것임.     -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSTL연습</h1>
<p>myName request> session> application 이 순서대로 myName을 찾음!! : ${myName} </p>
<p>myName : ${requestScope.myName} </p>
<p>myName : ${sessionScope.myName} </p>
<p>myName : ${applicationScope.myName} </p>
<ul><!-- getAttribute를하기 위해 el문법인 $를 써야함! -->
<!-- admins 여러건(adminList) admin(admin) 한건을 의미함. for(AdminVo admin: adminList) -->
	<c:set var="sum" value="0"/>
	<c:forEach items="${admins}" var="admin" varStatus="status">
	<c:set var = "sum" value="{sum+1}"/>
	<li style="color:${status.first?'red':'blue'};">${status.first}:${status.last}: ${sum}:${status.index}: ${status.count}: ${admin}-----${admin.manager_name }</li>
	</c:forEach>
</ul>
<hr>
<h2>score평가</h2>
<c:if test="${score>=90 }">
	<p>합격입니다.</p>
</c:if>
<c:if test="${score<90 }">
	<p>불합격입니다.</p>
</c:if>
<hr>c:out연습</h2>
<c:out value="오늘은 <목요일>입니다."/>
오늘은 <목요일>입니다.


<h2>숫자, 날짜 format</h2>
<c:set value="10000000" var="price"/>
1.price : ${price }
2.price : 
<fmt:formatNumber value="${price}" groupingUsed="true" type="currency" ></fmt:formatNumber>
3.오늘: ${today }<br>
<fmt:formatDate value="${today }" pattern="yyyy-MM-dd hh:mm:ss"/><br>
<fmt:formatDate value="${today }" dateStyle="full"/><br>
<h2>JSTL에서 문자열함수 사용하기</h2>
<p>myName: ${myName }</p>
<%-- <p>myName: ${fn:substring(myName,0,3) }</p>
 --%>
</body>
</html>
<%-- <c:choose> <!-- choose에서는 조건에 맞으면 수행하고 다음 when을 수행하지 않는다. -->
	<c:when test="${score>=90}">
		<p>A학점</p>
	</c:when>
	<c:when test="${score>=80}">
		<p>B학점</p>
	</c:when>
	<c:otherwise>
		<p>c학점</p>		
	</c:otherwise>
</c:choose>
 --%>










</body>
</html>