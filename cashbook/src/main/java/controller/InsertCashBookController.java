package controller;

import java.io.IOException;
import java.util.*;
import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashBookDao;
import vo.CashBook;

@WebServlet("/InsertCashbookController")
public class InsertCashBookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		String cashDate = y+"-"+m+"-"+d;
		request.setAttribute("cashDate", cashDate);
		request.getRequestDispatcher("/WEB-INF/view/insertCashBookForm.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //인코딩
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		
		System.out.println(cashDate + "InsertController(Post)-cashDate");
		System.out.println(kind + "InsertController(Post)-kind");
		System.out.println(cash + "InsertController(Post)-cash");
		System.out.println(memo + "InsertController(Post)-memo");
		
		CashBook cashbook = new CashBook();
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		
		//hashtag
		List<String> hashtag = new ArrayList<>();
		String  memo2=memo.replace("#", " #");
		String[] arr = memo.split(" ");
		for(String s : arr) {
			if(s.startsWith("#")) {
				String temp = s.replace("#", "");
				if(temp.length()>0) {
					hashtag.add(temp);
				}
			}
		}
		System.out.println(hashtag.size()+ ": hashtag.size()");
		for(String h : hashtag) {
			System.out.println(h + ": hashtag");
		}
		CashBookDao cashbookDao = new CashBookDao();
		cashbookDao.insertCashBook(cashbook, hashtag);
		
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}
	
}
