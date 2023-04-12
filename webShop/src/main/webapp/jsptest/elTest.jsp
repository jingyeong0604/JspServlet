<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.shinhan.vo.AdminVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL(Expression lanaguage)</h1>
<!-- action tag: jsp:으로 시작하는 tag(jsp가 기본적으로 제공하는 tag) -->
<!-- scope:page, request, session, application -->
<!-- EL이 내장하는 객체 scope:pageScope, requestScopre, sessionScopre, applicationScope -->
<jsp:useBean id="vo1" class="com.shinhan.vo.AdminVO" 
			scope="request"></jsp:useBean>
<jsp:setProperty property="email" name="vo1" value="ww@naver.com"/>

<jsp:useBean id="alist" class="java.util.ArrayList"></jsp:useBean>

<%-- <jsp:forward page="forwardTest.jsp"></jsp:forward> --%>

<pre>
1.숫자, 연산자: ${100 + 200}
2. 문자: ${"Hello"}
3.empty..fal: ${empty "Hello" }<!-- Hello가 비어있는지! -->
3.empty..true: ${empty "" }
3.empty..f: ${empty vol }
3.empty..t: ${empty alist }
3.empty..t: ${empty null }

4.비교 : ${100>200 }
5.문자+숫자 : ${ "100"+10}
6.eq : ${"Hello"=="Hello"}
6.eq : ${"Hello"eq"Hello"}
7.나머지: ${100%3}
7.나머지: ${100 mod 3}

9.내장객체 param: ${param.param1 }
9.내장객체 param: ${param.param2 }
<%
String p1 = request.getParameter("param1");
String p2 = request.getParameter("param2");
%>

11.request, session, application영역에 저장:
<%
application.setAttribute("score1",100);
session.setAttribute("score2",200);
request.setAttribute("score3",300);

application.setAttribute("scopeTest","application");
session.setAttribute("scopeTest","session");
request.setAttribute("scopeTest","request");
%>
12.request> session> application영역에서 읽기:
score1:${score1}
score2:${score2}
score3:${score3}
scoreTest: ${scopeTest }
pageScope: ${pageScope.scopeTest }
requestScope: ${requestScope.scopeTest }
sessionScope: ${sessionScope.scopeTest }
applicationScope: ${applicationScope.scopeTest }

13.pageContext이용해서 내장객체의 기능을 이용하기:
Expression문법 :<%=request.getContextPath() %>
EL이용 : ${pageContext.request.contextPath}

14.객체의 속성 사용하기:
<%
AdminVO adminVO = new AdminVO("wlsrud677@naver.com","jin","1234");
pageContext.setAttribute("admin", adminVO);
%>
request.getAttribute("admin"): ${admin}
request.getAttribute("admin").getEmail(): ${admin.email}

15.Collection test
<%
//이 부분은 서블릿으로 처리한다.
List<AdminVO> adminList =new ArrayList<>();
adminList.add(new AdminVO("aa@google.com","영선","1234"));
adminList.add(new AdminVO("bin@google.com","은빈","1234"));
request.setAttribute("adminList",adminList);

HashMap<String,Object> map1 = new HashMap<>();
map1.put("바나나",5);
map1.put("사과",25);
map1.put("orange",10);
map1.put("admins",adminList);
request.setAttribute("fruits",map1);
%>
${adminList[0].email }
${adminList[0].manager_name }
${adminList[0].pass }

${adminList[1].email }
${adminList[1].manager_name }
${adminList[1].pass }

map의 키가 영문이 아닌경우: ${fruits["바나나"]}
map의 키가 영문인 경우는 도트 가능: ${fruits.orange}
map의 객체가 저장된 경우: ${fruits.admins}

map의 객체의 순서에 접근: ${fruits.admins[0].email}

</pre>
</body>
</html>