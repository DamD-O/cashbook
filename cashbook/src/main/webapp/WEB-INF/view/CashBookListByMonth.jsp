<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashBookListByMonth</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container-lg p-3 my-3 border border-dark">
	<%
		List<Map<String, Object>> list =(List<Map<String,Object>>)request.getAttribute("list");
		int y = (Integer)request.getAttribute("y");
		int m = (Integer)request.getAttribute("m");
		
		int startBlank = (Integer)request.getAttribute("startBlank");
		int endDay = (Integer)request.getAttribute("endDay");
		int endBlank = (Integer)request.getAttribute("endBlank");
		int totalBlank = (Integer)request.getAttribute("totalBlank");
		
		System.out.println(list.size() + ": list.size(CashBookListByMonth.jsp)");
		System.out.println(y + ": y(CashBookListByMonth.jsp)");
		System.out.println(m + ": m(CashBookListByMonth.jsp)");
		System.out.println(startBlank + ": startBlank(CashBookListByMonth.jsp)");
		System.out.println(endDay + ": endDay(CashBookListByMonth.jsp)");
		System.out.println(endBlank + ": endBlank(CashBookListByMonth.jsp)");
		System.out.println(totalBlank + ": totalBlank(CashBookListByMonth.jsp)");
	%>
	<h2><%=y%>년 <%=m%>월</h2>
	<div>
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController?y=<%=y%>&m=<%=m-1%>" class="btn btn-outline-info btn-sm">이전 달</a>
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController?y=<%=y%>&m=<%=m+1%>" class="btn btn-outline-info btn-sm">다음 달</a>
	</div>
	<br>
	<table class="table table-bordered small">
		<thead>
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					//i-startBlank:날짜
					for(int i=1; i <= totalBlank; i=i+1){
						if((i-startBlank)>0 && (i-startBlank)<=endDay){
							String c="";
							if(i%7 ==0){
								c = "text-primary";
								
							}else if(i %7 ==1){
								c = "text-danger";
							}
				%>
							<td class="<%=c%>"><%=i-startBlank%>
								<a href="<%=request.getContextPath()%>/InsertCashbookController?y=<%=y %>&m=<%=m %>&d=<%=i-startBlank%>" class="btn btn-outline-success btn-sm">입력</a>
								<div>
									<%
										//해당 날짜의 cashbook 목록 출력
										for(Map map :list){
											if((Integer)map.get("day") == (i-startBlank)){
									%>
											<div>
											[<%=map.get("kind") %>] 
											 <%=map.get("cash") %>원
											 <%=map.get("memo")%>...
											</div>
									<%
											}
										}
									%>
								</div>
								
							</td>
				<%	
							}else{		
				%>
							<td>&nbsp;</td>
				<%		
						}
						if(i < totalBlank && i%7 == 0){
				%>
						</tr><tr> <!-- 새로운 행 추가 -->
				<%
				
						}
					}
				%>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
<!-- 
	1.  1일날짜의 요일값 firstDay -> startBlank(요일,앞 공백) 일요일 0, 월 1...토 6
	2. 이번달 마지막 날짜 endDay
	
	3.endBlank -> totalBlank
	4.td의 개수 1~ totalBlank
	+
	5.가계부 list
	6.오늘날짜 today
 -->