<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입</h2>
	<form action="<%=request.getContextPath()%>/InsertMemberController" method="post">
		<div>
			아이디 : <input type="text" name="memberId" placeholder="아이디를 입력해주세요."> <br>
		</div>
		<div>
			비밀번호 : <input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요."> <br>
		</div>
		<button type="submit">회원가입</button>
	</form>
</body>
</html>