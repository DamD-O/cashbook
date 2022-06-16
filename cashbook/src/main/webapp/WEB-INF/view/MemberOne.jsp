<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
<h2>회원 상세보기</h2>
<div>
        <a href="<%=request.getContextPath()%>/CashBookListByMonthController" class="btn btn-outline-info btn-blockbtn-sm ">목록으로 돌아가기</a>    
    </div>
    <br>
    <table class="table-bordered">
        <tr>
            <td>memberId</td>
            <td><%=request.getAttribute("memberId") %></td>
        </tr>
        <tr>
            <td>memberName</td>
            <td><%=request.getAttribute("memberName") %></td>
        </tr>
        <tr>
            <td>memberAge</td>
            <td><%=request.getAttribute("memberAge") %></td>
        </tr>
        <tr>
            <td>updateDate</td>
            <td><%=request.getAttribute("updateDate") %></td>
        </tr>
    </table>
    <br>
    <div>
        <a href="<%=request.getContextPath()%>/UpdateMemberController?memberId=<%=request.getAttribute("memberId")%>" class="btn btn-outline-success">수정</a>
        <a href="<%=request.getContextPath()%>/DeleteMemberController?memberId=<%=request.getAttribute("memberId")%>" class="btn btn-outline-danger">삭제</a>
    </div>
</div>
</body>
</html>