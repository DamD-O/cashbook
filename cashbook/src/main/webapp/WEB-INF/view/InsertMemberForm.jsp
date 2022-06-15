<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border">
   <h3>회원가입</h3>
   <form action="<%=request.getContextPath()%>/InsertMemberController" method="post" class="probootstrap-form">
       <div class="row mb-5">
            <div class="col-md">
               <div class="form-group">
                  <label for="probootstrap-date-departure">ID</label>
                  <div class="probootstrap-date-wrap">
                     <input type="text" name="memberId" placeholder="아이디를 입력해주세요." class="form-control"> <br>
                  </div>
                   <label for="probootstrap-date-arrival">PASSWORD</label>
                  <div class="probootstrap-date-wrap">
                     <input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요." class="form-control"> <br>
                  </div>
               </div> 
                  <button type="submit" class="btn btn-outline-secondary">회원가입</button>
            </div> 
         </div>       
   </form>
</div>  
</body>
</html>