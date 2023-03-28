<%@page import="java.util.List"%>
<%@page import="aproject.vo.EmpVO"%>
<%@page import="aproject.model.EmpService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EmpService eService = new EmpService();
	List<EmpVO> emplist = eService.selectAll();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="cssgroup.css" rel="stylesheet" type="text/css">
<style>
	#container{

		border: 1px #FF9CBB;
		width: 940px;
		margin:0 auto; /*중간정렬*/
		border-top: 1px #FF9CBB;
		border-bottom:1px #FF9CBB;
		
	}
	tbody{
		border-top-left-radius: 15px;
		}
	
	h1{
		padding: 20px;
		text-align: center;	
	}
	
	thead tr{
		background-color:#FFDCE1;
		padding: 10px;
		text-align: center;	
	}
	tbody tr{
		text-align:center;
		background-color: #FFF0F5;
	}
	tr:hover{
		background-color:#FF9CBB;
		cursor: pointer;
	}
</style>
</head>

<body>
<div id="container" >
<h1 >직원 목록</h1>

<button 
onclick="location.href='emp_insert.html'"
type="button" class="btn btn-success">직원등록</button>
<a type="button" class="btn btn-success" href="emp_insert.html">직원등록</a> <!-- a버튼을 사용한 이유는 href를 사용하기 위해서 -->
<table  class="table table-hover">
 <thead>
 	<tr>
 	 <th>직원번호</th>
 	 <th>이름</th>
 	 <th>성</th>
 	 <th>이메일</th>
 	 <th>급여</th>
 	 <th>입사일</th>
 	 <th>전화번호</th>
 	 <th>직책</th>
 	 <th>매니져</th>
 	 <th>커미션</th>
 	 <th>부서번호</th>
 	</tr>
 </thead>
 <tbody>
  <%for(EmpVO emp:emplist){ %>
  <tr>
   <td><%=emp.getEmployee_id() %></td>
   <td><%=emp.getFirst_name() %></td>
   <td><%=emp.getLast_name() %></td>
   <td><%=emp.getEmail() %></td>
   <td><%=emp.getSalary() %></td>
   <td><%=emp.getHire_date() %></td>
   <td><%=emp.getPhone_number() %></td>
   <td><%=emp.getJob_id() %></td>
   <td><%=emp.getManager_id() %></td>
   <td><%=emp.getCommission_pct() %></td>
   <td><%=emp.getDepartment_id() %></td>
  </tr>
  <%} %>
 </tbody>

</table>
</div>
</body>
</html>