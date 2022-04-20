package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashBookDao;
import vo.CashBook;

@WebServlet("/CashBookOneController")
public class CashBookOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.선택한 가계부번호
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo")); //번호가져오기
		System.out.println(cashbookNo + "cashbooNo(OneController)");
		
		//모델(비즈니스 로직 호출)
		CashBookDao cashBookDao = new CashBookDao();
		CashBook cashBook = new CashBook();
		cashBook = cashBookDao.selectCashBookOne(cashbookNo);
		
		//속성값 가져오기
		request.setAttribute("cashbookNo", cashBook.getCashbookNo());
		request.setAttribute("cashDate", cashBook.getCashDate());
		request.setAttribute("kind", cashBook.getKind());
		request.setAttribute("cash", cashBook.getCash());
		request.setAttribute("memo", cashBook.getMemo());
		request.setAttribute("createDate", cashBook.getCreateDate());
		request.setAttribute("updateDate", cashBook.getUpdateDate());
		
		request.getRequestDispatcher("/WEB-INF/view/CashBookOne.jsp").forward(request, response);
	}

}
