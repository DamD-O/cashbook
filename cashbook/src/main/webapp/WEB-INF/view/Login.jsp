<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//error 메세지
	String msg = (String)session.getAttribute("msg");
	if(msg == null){
		msg = "";
	}
	session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<h2>로그인</h2>
	<form action="<%=request.getContextPath()%>/LoginController" method="post">
		<table>
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">로그인</button>
		<div id="msg" style="color: red"><%=msg%></div>
	</form>
</div>	
</body>
</html>