<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
<h2>가계부 상세보기</h2>
	<div>
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController" class="btn btn-outline-info btn-blockbtn-sm ">목록으로 돌아가기</a> 		
	</div>
	<table class="table-bordered">
		<tr>
			<td>cashbookNo</td>
			<td><%=request.getAttribute("cashbookNo") %></td>
		</tr>
		<tr>
			<td>cashDate</td>
			<td><%=request.getAttribute("cashDate") %></td>
		</tr>
		<tr>
			<td>kind</td>
			<td><%=request.getAttribute("kind") %></td>
		</tr>
		<tr>
			<td>cash</td>
			<td><%=request.getAttribute("cash") %></td>
		</tr>
		<tr>
			<td>memo</td>
			<td><%=request.getAttribute("memo") %></td>
		</tr>
		<tr>
			<td>createDate</td>
			<td><%=request.getAttribute("createDate") %></td>
		</tr>
		<tr>
			<td>updateDate</td>
			<td><%=request.getAttribute("updateDate") %></td>
		</tr>
	</table>
	<div>
		<a href="<%=request.getContextPath()%>/UpdateCashBookController?cashbookNo=<%=request.getAttribute("cashbookNo") %>" class="btn btn-outline-success">수정</a>
		<a href="<%=request.getContextPath()%>/DeleteCashBookController?cashbookNo=<%=request.getAttribute("cashbookNo") %>" class="btn btn-outline-danger">삭제</a>
	</div>
</div>
</body>
</html>