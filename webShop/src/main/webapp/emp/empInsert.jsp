<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
    
<%
//자바코드작성 service코드에 삽입된다.
String subject="jsp코드";

//int age=Integer.parseInt(request.getParameter("age"));


%>
<%!
//자바코드 작성...선언부...별도의 메서드로 만들어진다.
int score=100;
public int add(int a, int b){
	return a+b;
	
}
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원등록1</title>
<%@ include file="../common/commonfiles.jsp" %>

​
</head>
<body>
<%@ include file="../common/header.jsp" %>
<h1>직원정보 입력</h1>
<%--JSP주석 --%>
<!-- service의 out.write() -->
subject: <%=subject %><br>
score: <%=score %><br>
add함수: <%=add(100,200) %><br>
<!-- http://localhost:9999/ -->
<form method="post" action="<%=request.getContextPath()%>/emp/empinsert.do"  class="mb-3">
<table >
  <tr  class="form-floating">
    <td><label for="employee_id">직원번호</label></td>
    <td><input class="form-control"  type="number" 
    name="employee_id" id="employee_id" 
    required="required"
    placeholder="직원번호는sequence처리함"
    autofocus="autofocus"
    value="999"
   disabled="disabled"
    ></td>
  </tr>
  <tr>
    <td>firstName</td>
    <td><input type="text" name="first_name" maxlength="20" ></td>
  </tr>
  <tr>
    <td>LastName</td>
    <td><input type="text" name="last_name" required="required"></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input type="email" name="email" required="required"></td>
  </tr>
  <tr>
    <td>phone</td>
    <td><input type="tel" name="phone_number" 
    value="010-7896-8963"
    pattern="010-[0-9]{4}-[0-9]{4}"></td>
  </tr>
  <tr>
    <td>salary</td>
    <td><input type="number" name="salary" ></td>
  </tr>
  <tr>
    <td>부서</td>
    <td>
	    <select name="department_id">
			<c:forEach items="${deptList}" var="dept">
				<option value="${dept.department_id}">${dept.department_name}</option>
			</c:forEach>
		</select>
	</td>
  </tr>
  <tr>
    <td>메니져</td>
	<td>
		<select name="manager_id">
			<c:forEach items="${managerList}" var="manager">
			
				<option value="${manager.employee_id}">${manager.first_name}----${manager.last_name}</option>
			</c:forEach>
		</select>
	</td>
  </tr>
  <tr>
    <td>커미션</td>
    <td><input type="text" name="commission_pct"  value="0.2"></td>
  </tr>
  <tr>
    <td>입사일</td>
    <td><input type="date" name="hire_date" 
    value="2021-01-10"
    required="required"></td>
  </tr>
  <tr>
    <td>직급</td>
    <td>
		<select name="job_id">
			<c:forEach items="${jobList}" var="job">
				<option value="${job.job_id}">${job.job_title}</option>
			</c:forEach>
		</select>
		</td>
  </tr>
  <tr style="text-align: center;">
    <td colspan="2">
       <input type="submit" value="직원등록">
    </td>
  </tr>
</table>
</form>
<div id="here">여기</div>
<button id="btn1" onClick="call()">버튼</button>
​
<script>
  function call(){
	  //DOM(Document Object Model)
	  document.getElementById("here").innerHTML = new Date();
	  document.getElementById("employee_id").value = 888;
  }
   
  
</script>
</body>
</html>