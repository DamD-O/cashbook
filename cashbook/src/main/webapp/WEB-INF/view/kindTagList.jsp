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
		<a href="<%=request.getContextPath()%>/TagController" class="btn btn-outline-info btn-blockbtn-sm ">목록으로 돌아가기</a> 		
	</div>
	<table class="table table-bordered small">
		<tr>
			<th>kind</th>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>	
		</tr>
		<%
			for(Map<String,Object> map : list){
		%>
			<tr>
				<td><%=map.get("kind") %></td>
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