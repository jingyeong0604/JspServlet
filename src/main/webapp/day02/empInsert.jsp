<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- 아래 코드는 자바임 <% %> -->
 <%
    String empid = request.getParameter("employee_id");
    String fname = request.getParameter("first_name");
    String lname = request.getParameter("last_name");
 	String email = request.getParameter("email");
 	String phone = request.getParameter("phone_number");
 	String salary = request.getParameter("salary");
 	String deptid = request.getParameter("department_id");
 	String comm = request.getParameter("commision_pct");
 	String hire_date = request.getParameter("hire_date");
 	
 	System.out.print("hire_date: "+hire_date);
 	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
p:{border: 1px solid green;}
</style>
</head>
<body>
<h1>신규직원을 DB에 저장합니다.</h1>
<p>employee_id:<%=empid %></p>
<p>fname:<%=fname %></p>
<p>employee_id:<%=empid %></p>
<p>lname:<%=lname %></p>
<p>email:<%=email %></p>
<p>phone:<%=phone %></p>
<p>deptid:<%=deptid %></p>
<p>comm:<%=comm %></p>

</body>
</html>