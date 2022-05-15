<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<%
	List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
%>
<div class="container-lg p-3 my-3 border border-dark">
	<h2>tag rank</h2>
	<div>
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController" class="btn btn-outline-info btn-blockbtn-sm ">목록으로 돌아가기</a> 		
	</div>
	<div>
		<h4 class="text-dark">수입 or 지출 검색</h4>
		<form action="<%=request.getContextPath()%>/kindSearchController" method="get" class="form-inline">
		<table class="table table-bordered">
		<tr>
			<td>종류</td>
			<td>
       			<div><input type="radio" name="kind" value="수입" class="form-check-input">수입</div>
       			<div><input type="radio" name="kind" value="지출" class="form-check-input">지출</div>
			</td>
			<td><button type="submit" class="btn btn-primary">검색</button></td>
		</tr>	
		</table>
		</form>
	</div>
	
	<div>
		<h4 class="text-dark">년도별 검색</h4>
		<form action="<%=request.getContextPath()%>/DateSearchController" method="get" class="form-inline">
		<table class="table table-bordered">
		<tr>
			<td>년도</td>
			<td><input type="date" name="cashDate" class="form-control"></td>
			<td><button type="submit" class="btn btn-primary">검색</button></td>
		</tr>	
		</table>
		</form>
	</div>
	<table class="table table-bordered small">
		<tr>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>
		</tr>
		<%
			for(Map<String,Object> map : list){
		%>
			<tr>
				<td><%=map.get("rank") %></td>
				<td><%=map.get("tag") %></td>
				<td><%=map.get("cnt") %></td>
			</tr>
		<%		
			}
		%>
	</table>
</div>
</body>
</html>