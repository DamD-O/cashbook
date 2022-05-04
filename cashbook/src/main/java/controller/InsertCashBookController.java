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
import vo.Member;

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
		//값 가져오기
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		String memberId = request.getParameter("memberId");
		
		//디버깅
		System.out.println(cashDate + "InsertController(Post)-cashDate");
		System.out.println(kind + "InsertController(Post)-kind");
		System.out.println(cash + "InsertController(Post)-cash");
		System.out.println(memo + "InsertController(Post)-memo");
		System.out.println(memberId + "InsertController(Post)-memberId");
		
		//객체생성 후 cashbook값 저장
		CashBook cashbook = new CashBook();
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		
		//객체생성 후 member값 저장
		Member member = new Member();
		member.setMemberId(memberId);
		
		
		
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
		cashbookDao.insertCashBook(cashbook, hashtag, member);
		
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}
	
}
