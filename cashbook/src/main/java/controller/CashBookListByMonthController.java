package controller;

import java.io.IOException;
import java.util.*;
import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CashBookListByMonthController")
public class CashBookListByMonthController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1) 월별 가계부 리스트 요청 분석
		Calendar today = Calendar.getInstance();
		int y =today.get(Calendar.YEAR);
		int m =today.get(Calendar.MONTH) + 1; //0 - 1월, 1 - 2월 ...11-12월
		
		//년도 값 넘어오면
		if(request.getParameter("y") != null) {
			y = Integer.parseInt(request.getParameter("y"));
		}
		//월 값 넘어오면
		if(request.getParameter("m") != null) {
			m = Integer.parseInt(request.getParameter("m"));
		}
		if(m==0) {
			m = 12;
			y=y-1;
		}
		if(m==13) {
			m = 1;
			y=y+1;
		}
		
		System.out.println(y + "년");
		System.out.println(m + "월");
		
		//2) 모델값(월별 가계부 리스트)을 반환하는 비지니스로직(모델) 호출
		CashbookDao cashbookDao = new CashbookDao();
		List<Map<String, Object>> list = cashbookDao.selectCashBookListByMonth(y, m);
		request.setAttribute("list", list);
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		
		//3) 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashBookListByMonth.jsp").forward(request, response);
		
	}

}
