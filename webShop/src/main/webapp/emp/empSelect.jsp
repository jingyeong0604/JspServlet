
<%@page import="com.shinhan.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- java code를 없앰 why????????????? --> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%--jsp:include page="${path}/common/commonfiles.jsp }"></jsp:include> --%>
<%@ include file="../common/commonfiles.jsp" %>


<style>
  /* #container{
       
      width: 80%;
      margin: 0 auto;
  }
  h1{
    border : 1px solid gray;
    text-align: center;
    width:100%;
    
  }
  thead tr, thead td{
	 background-color: lightblue;
	 padding: 10px;
  }
  table, tr, td{
     border: 1px solid gray;
     border-collapse: collapse;
     padding: 0.5em;
  }
  .aa:hover, 	::selection {
    background-color: orange;
  } */
  .orange{
    background-color: orange;
  }
  .white{
    background-color: white;
  }
</style>
<script
  src="https://code.jquery.com/jquery-3.6.4.min.js" ></script>
  <script>
  
  $(function(){
	  //button이 많기 때문에 class로 바꾸자! ->.으로 표현
	  $(".btnDel").on("click",function(){
		  //location.href는 get방식 attr: 속성의 값을 읽어줌 
		  //data-del이 무엇을 의미하는지?
				  
		 location.href="empDelete.do?empid="+ $(this).attr("data-del");
		 
	  });
  });
  
  
   $(function(){ 
	   $("thead tr th").click(function(e){
		   //내가 클릭한 th가 몇번째인가?
		   var trNum = $(this).closest("th").prevAll().length;
		   console.log(trNum);
		   var a = $("tbody tr").each(function(index, item){
			   var col = $(item).find("td:nth-child(" + (trNum+1) + ")");
			   console.dir(col);
			   //되돌리기(기존선택을 clear)
			   $(item).find("td").css("background-color", "white");
			   //신규선택의 색깔 바꾸기
			   $(col).css("background-color", "orange");
			   
		   });
		   
	   });
   });
  </script>
  <script>
    function init(){
    	$("tr").css("background-color", "white")
    	$("tr").css("color", "black");
    }
    $(function(){
    	
    	$("#btn1").click(function(){
    		$("tr:nth-child(2n)").css("background-color", "lightgray");
        	$("tr:nth-child(2n+1)").css("background-color", "white");
    	});
    	//body > div > table > tbody > tr:nth-child(1) > td:nth-child(2)
    	$("#btn2").click(function(){
    		init();
    		$("tr > td:nth-child(2):contains('S')").css("color", "red");
    	});
    	$("#btn3").click(function(){
    		init();
    		$("tr td:contains('S')").css("color", "red");
        	
    	});
    	$("#btn4").on("click", function(){
    		init();
    		var selector = "tr > td:nth-child(5)";
    		
    		$(selector).each(function(index, item){
    			var sal = parseInt($(item).html()) ;
    			if(sal >= 5000) {
    				$(item).css("background-color", "lightgreen")
    			}
    		});
    	});
    	
    	$("#btn5").click(function(){
    		var arr = $("tr>td:nth-child(1)");
    		$.each(arr, function(index, item){
    			if(parseInt($(item).text())%2==1){
    				console.log($(item).text());
    				$(item).parent().css("background-color", "green");
    			}
    		});
    	});
    	
    	
    	var str="";
    	var arr = ["IT_PROG", "AD_VP","AD_PRES","ST_MAN","ST_CLERK"];
    	$.each(arr, function(index, item){
    		console.log(item);
    		//str += `<option>${item}</option>`;
    		str += "<option>" + item + "</option>";
    	});
    	
    	$("#jobs").html(str);  //html() : jQuery함수 , innerHTML는 자바스크립트 속성 
    	console.log(str);
    	
    	$("select").change(function(){
    		var jobid = $(this).val();
    		//init();
    		$("tr td").css("color","black");
    		$("tr td:contains('" + jobid + "')").css("color", "red");
    	});

    });
  </script>
  <script>
  $(function(){
	  $("#btnLogout").on("click",function(){
		  alert("로그아웃버튼");
		  $.ajax({
	 
	  url:"../auth/logout.do",
	  success:function(){
		  alert("success!");
	  },
	  error:function(message){
	  	alert(message);
	  }
		  });
  });
	  
  });
  </script>
</head>
<body>
<div class="container mt-3">
	<h1>직원목록</h1>
	 <form method="post"  action="${path }/downloadTest/result.jsp" >
	 <input type=hidden  name="param1" value="p1.png" /> <br>
	 <input type=hidden  name="param2" value="p2.png" /> <br>
   	 <input type ="submit" value="이미지 다운로드">	 
	 </form>
	<!-- include 디렉티브는 소스를 합쳐서 컴파일한다. empinsert에서 header에서 쓴것을 쓸 수 있음. -->
  <!-- include 지시사 : 합쳐서 컴파일 -->
  <%@ include file="../common/header.jsp" %>   
<!-- include action tag이용: 컴파일하고 합침. -->
	<%--  <h2><%=company %></h2>--> --%> 
	<button 
	onclick="location.href='${path}/emp/empinsert.do'"
	type="button" class="btn btn-success">직원등록</button>
	<!--  <a type="button" class="btn btn-success" href="empinsert.do">직원등록</a>-->
	<button type="button" class="btn btn-primary" 
	data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">Modal 이용 직원등록</button>
	<%@ include file="empInsertModal.jsp" %>
	
	<hr>
	<button id="btn1">짝수row선택</button>
	<button id="btn2">이름 S로 시작하는 직원</button>
	<button id="btn3">S문자가 포함</button>
	<button id="btn4">급여5000이상</button>
	<button id="btn5">직원 번호가 홀수인 사람 선택</button>
	<select id="jobs">
	   
	</select>
	<hr>
		<form method="post" action="${path }/downloadTest/result.jsp">
			<!-- 이름이 가는 것이다. -->
			<input type=hidden name="param1" value="watch.jpg" /> <br> 
			<input
				type=hidden name="param2" value="umbrella.jpg" /> <br> 
				<input
				type="submit" value="이미지 다운로드">
		</form>
		<table class="table table-hover">
	  <thead>
	   <tr>
	   	 <th>순서</th>
	     <th>직원번호</th>
	     <th>이름</th>
	     <th>성</th>
	     <th>이메일</th>
	     <th>급여</th>
	     <th>누적 급여</th>
	     <th>입사일</th>
	     <th>전화번호</th>
	     <th>직책</th>
	     <th>메니져</th>
	     <th>커미션</th>
	     <th>부서</th>
	 
	   </tr>
	 </thead>
	 <tbody> 
	 <!-- for(EmpVO emp:empAll) -->
<!-- 	 c태그는 실행되고 없어지는 태그임 -->
	 	<c:set var="totalSalary" value="0"/>
	   <c:forEach items="${empAll}" var="emp" varStatus="status">
	   <c:set var="totalSalary" value="${totalSalary+ emp.salary} "/>
	   <tr>
	     <td style="background-color: ${status.first||status.last?'#E2A9F3':'#F8E0EC'};">${status.count }</td>
	     <td><a href="empDetail.do?empid=${emp.employee_id}">${emp.employee_id}</a></td>
	     <td><a href="empDetail.do?empid=${emp.employee_id}">${emp.first_name}</a></td>
	     
	     <td>${emp.last_name}</td>
	     

<%-- <td>${emp.email}
 ${fn:substring(emp.email,0,3)}**
 ${fn:indexOf(emp.email,"@")}**
 ${fn:indexOf(emp.email,"@")>=0?fn:substring(emp.email,0,3):emp.email}
 ${fn:substring(emp.email,0,${fn:emp.email.indexOf("@")>0?emp.email.indexOf("@"):emp.email.length()})}
 </td> --%>
	     <td>${emp.salary}</td>
	    
	     <td>${totalSalary}</td>
	     <td>${emp.hire_date}</td>
	     <td>${emp.phone_number}</td>
	     <td>${emp.job_id}</td>
	     <td>${emp.manager_id}</td>
	 
	     <td>${emp.commission_pct}
	     	<format:formatNumber type="percent" value="${emp.commission_pct}"/>
	     </td>
	     <td>${emp.department_id}</td>
	     <td><button class="btnDel" data-del="${emp.employee_id}">삭제</button></td>
	   	 
	   </tr>
	  	  </c:forEach>
	</tbody> 
	 
	</table>
</div>	
 
</body>
</html>