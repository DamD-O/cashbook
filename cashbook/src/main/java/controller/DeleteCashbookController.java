package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashBookDao;

@WebServlet("/DeleteCashbookController")
public class DeleteCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo")); // 삭제할 번호
	      
	      // CashbookDao.deleteCashbook()
	      CashBookDao cashbookDao = new CashBookDao();
	      cashbookDao.deleteCashBook(cashbookNo);
	      
	      System.out.println(cashbookNo + "delete-cashbookNo");
	      //페이지 이동
	      response.sendRedirect(request.getContextPath() + "/CashbookListByMonthController");
	  }
}

