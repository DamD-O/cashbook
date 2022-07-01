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
<h2>updateCashBook</h2>
<form action="<%=request.getContextPath()%>/DeleteCashBookController" method="post">
    <div>
        <a href="<%=request.getContextPath()%>/CashBookListByMonthController" class="btn btn-outline-info btn-blockbtn-sm ">목록으로 돌아가기</a>       
    </div>
    <table class="table-bordered">
        <tr>
            <td>cashbookNo</td>
            <td>
                <input type="number" name="cashbookNo" readonly="readonly" value="<%=(String)request.getAttribute("cashbookNo")%>" class="form-control">
            </td>
        </tr>
        <tr>
            <td>날짜</td>
            <td><input type="text" name="cashDate" class="form-control" value="<%=(String)request.getAttribute("cashDate") %>" readonly="readonly"></td>
        </tr>
        <tr>
            <td>kind</td>
            <td>
                <div class="form-check-inline">
                    <input type="radio" name="kind" value="<%=(String)request.getAttribute("kind") %>" class="form-check-input" readonly="readonly">
                </div>
            </td>   
        </tr>
        <tr>
            <td>cash</td>
            <td><input type="number" name="cash" class="form-control" value="<%=request.getAttribute("cash")%>" readonly="readonly"></td>
        </tr>
        <tr>
            <td>memo</td>
            <td>
                <textarea rows="4" cols="50" name="memo" class="form-control" value="<%=request.getAttribute("memo")%>"readonly="readonly"></textarea>
            </td>
        </tr>
        <tr>
            <td>updateDate</td>
            <td><%=request.getAttribute("updateDate")%></td>
        </tr>
        <tr>
            <td>memberPw</td>
            <td>
                <input type="password" name="memberPw"  class="form-control">
            </td>
        </tr>
    </table>
    <button type="submit" class="btn btn-outline-success">삭제</button>
</form>

</div>
</body>
</html>