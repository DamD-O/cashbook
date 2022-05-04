<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container-lg p-3 my-3 border border-dark">
	<h1>insertCashBook</h1>
	<form action="<%=request.getContextPath()%>/InsertCashbookController" method="post">
	<table class="table-bordered">
		<tr>
			<td>날짜</td>
			<td><input type="text" name="cashDate" class="form-control" value="<%=(String)request.getAttribute("cashDate") %>" readonly="readonly"></td>
		</tr>
		<tr>
			<td>kind</td>
			<td>
				<div class="form-check-inline">
				<input type="radio" name="kind" value="수입" class="form-check-input">수입
				<input type="radio" name="kind" value="지출" class="form-check-input">지출
				</div>
			</td>	
		</tr>
		<tr>
			<td>cash</td>
			<td><input type="number" name="cash" class="form-control"></td>
		</tr>
		<tr>
			<td>memo</td>
			<td>
				<textarea rows="4" cols="50" name="memo" class="form-control" ></textarea>
			</td>
		</tr>
		<tr>
			<td>memberId</td>
			<td>
				<input type="text" name="memberId" class="form-control">
			</td>
		</tr>
	</table>
	<button type="submit" class="btn btn-outline-success">입력</button>
	</form>
</div>
</body>
</html>