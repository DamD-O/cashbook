<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//error 메세지
	String msg = (String)session.getAttribute("msg");
	if(msg == null){
		msg = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border ">
    <h3>Login</h3>
    <div id="msg" style="color: red"><%=msg%></div>
	<form action="<%=request.getContextPath()%>/LoginController" method="post" class="probootstrap-form">
		<div class="row mb-5">
	         <div class="col-md">
	            <div class="form-group">
	               <label for="probootstrap-date-departure">ID</label>
	               <div class="probootstrap-date-wrap">
	                  <input type="text" id="id" class="form-control" placeholder="아이디를 입력해주세요" name="memberId">
	               </div>
	                <label for="probootstrap-date-arrival">PASSWORD</label>
                   <div class="probootstrap-date-wrap">
                      <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" name="memberPw">
                   </div>
	            </div> 
	               <button type="submit" class="btn btn-outline-secondary">로그인</button>
	               
	               <a href="<%=request.getContextPath()%>/InsertMemberController">회원가입</a>
	         </div> 
	      </div>       
	</form>
	<form action="<%=request.getContextPath()%>/LoginController" method="post" class="probootstrap-form">
	   <input type="text" value="test" name="memberId" hidden="hideen">
       <input type="text" value="1234" name="memberPw" hidden="hideen">
       <button type="submit"  class="btn btn-outline-secondary">자동 로그인</button>
	</form>
</div>	
</body>
</html>