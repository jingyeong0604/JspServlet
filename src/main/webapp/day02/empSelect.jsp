<%@page import="java.util.List"%>
<%@page import="aproject.vo.EmpVO"%>
<%@page import="aproject.model.EmpService"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EmpService eService = new EmpService();
	List<EmpVO> emplist = eService.selectAll();
	
%>
<!DOCTYPE html>
<html>
<script>
	
	$("thead tr th").click(function(e) {
		var trNum = $(this).closest("th").prevAll().length;
		//$(this).find(" ~ th").css("background-color", "white");
		//$(this).css("background-color", "orange");
		var a = $("tbody tr").each(function(index, item) {
			var col = $(item).find("td:nth-child(" + (trNum + 1) + ")");
			console.dir(col);
			//되돌리기 (기존선택을 clear)
			$(item).find("td").css("background-color", "white");
			//신규선택의 색깔 바꾸기
			$(col).css("background-color", "orange");
		});
	});
	
	
	//짝수 row선택

		$("#btn1").click(function(){
			$("tr:nth-child(2n)").css("background-color", "lightgray");
			$("tr:nth-child(2n+1)").css("background-color", "lightgray");
			
			
		});
		
	
	

	//이름 S로 시작하는 직원

		$('#btn2').click(function(){	
			$('select[id^="S"]').val("${defaultListRowCnt}");		
		});
		$('#btn2').click(function2(){
			$('select[id^="S"]').val("${defaultListRowCnt}");		
		}); 
		$('#btn2').click(function2(){
		$('select[id^="S"]').val("${defaultListRowCnt}");		
		
		});
	
</script>
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
<script>
$(function(){
	$("#btn1").click(function(){
		$("tr:nth-child(2n)").css("background-color", "lightgray");
    	$("tr:nth-child(2n+1)").css("background-color", "white");
	});
	//body > div > table > tbody > tr:nth-child(1) > td:nth-child(2)
	$("#btn2").click(function(){
		$("tr > td:nth-child(2):contains('S')").css("color", "red");
	});
	$("#btn3").click(function(){
		$("tr td:contains('S')").css("color", "red");
    	
	});
	$("#btn4").on("click", function(){
		var selector = "tr > td:nth-child(5)";
		
		$(selector).each(function(index, item){
			var sal = parseInt($(item).html()) + 1;
			if(sal >= 5000) {
				$(item).css("background-color", "lightgreen")
			}
		}); 
		
	});
	
	var str="";
	var arr=["AD_PRES","AD_VP","IT_PROG","FI_ACCOUNT","PU_MAN","PU_CLERK"];
	$.each(arr, function(index, item){
		consol.log(item);
		str += "<option>"+item"</option>";
	});
	
	$("#jobs").html(str);//html():jQuery함수, js의 innerHTML과 비슷한역할
	console.log(str);
	
	
	$("select").change(function(){
		var jobid = $(this).val();
		//init();
		$("tr td").css("color","black");
		$("tr td:contains('" + jobid + "')").css("color", "red");
		});
});
</script>
</head>

<body>
<div id="container" >
<h1 >직원 목록</h1>

<button 
onclick="location.href='emp_insert.html'"
type="button" class="btn btn-success">직원등록</button>
<a type="button" class="btn btn-success" href="emp_insert.html">직원등록</a> <!-- a버튼을 사용한 이유는 href를 사용하기 위해서 -->
<hr>
<button id="btn1">짝수row선택</button>
<button id="btn2">이름 S로 시작하는 직원</button>
	<button id="btn3">S문자가 포함</button>
	<button id="btn4">급여5000이상</button>
	<button id="btn5">직원 번호가 홀수인 사람 선택</button>
	<!-- 동적으로 만들기 -->
	<select id="jobs">
	
	</select>
	<hr>
	
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