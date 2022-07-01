package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashBookDao;
import vo.CashBook;

@WebServlet("/DeleteCashBookController")
public class DeleteCashBookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//번호 가져오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String memberPw = request.getParameter("memberPw");
		System.out.println(cashbookNo + "cashbooNo(DeleteCashBookController)");
		System.out.println(memberPw + "memberPw(DeleteCashBookController)");
		
		//모델(비즈니스 로직 호출)
        CashBookDao cashbookDao = new CashBookDao(); //dao 객체 생성
        cashbookDao.deleteCashBook(cashbookNo); //삭제메소드호출
        System.out.println(cashbookNo + "delete.cashbookNo");
        
        //객체생성
        CashBook cashBook = new CashBook();
        //속성값 가져오기
        request.setAttribute("cashbookNo", cashBook.getCashbookNo());
        request.setAttribute("cashDate", cashBook.getCashDate());
        request.setAttribute("kind", cashBook.getKind());
        request.setAttribute("cash", cashBook.getCash());
        request.setAttribute("memo", cashBook.getMemo());
        request.setAttribute("updateDate", cashBook.getUpdateDate());
	     
	     //페이지 이동
	     request.getRequestDispatcher("/WEB-INF/view/deleteCashBookForm.jsp").forward(request, response);
	}
}

