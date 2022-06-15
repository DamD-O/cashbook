package controller;

import java.io.IOException;
import java.util.*;
import dao.*;
import vo.Stats;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CashBookListByMonthController")
public class CashBookListByMonthController extends HttpServlet {
	private StatsDao statsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionMemberId =(String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			//이미 로그인 되있는 상태
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
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
		
		/*
		 1.startBlank
		 2.endDay
		 3.endBlank
		 4.totalBlank
		 
		 */
		
		//시작시 필요한 공백 td개수수하는 알고리즘 -> startBlank
		//firstDay 오늘날짜 먼저구하기 -> 날짜만 1일로 변경
		Calendar firstDay =Calendar.getInstance(); //ex)2022.04.19
		//선택한 임의의 날짜
		firstDay.set(Calendar.YEAR, y); 
		firstDay.set(Calendar.MONTH, m-1); //자바달력 API는 1월을 0, 2월을 1 ...12월을 11로 설정되어있음
		firstDay.set(Calendar.DATE, 1); //ex)2022.04.01
		int DayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK); //요일
		//DayOfWeek는 일요일이 1, 월요일 2, ...토요일 6
		//startBlank 일요일 0, 월 1...토 6
		
		//1.
		int startBlank = DayOfWeek - 1; //요일, 1일의 요일을 구한다(일 0/월 1 ...)
		
		//마지막 날짜 자바  API이용
		//2.
		int endDay =firstDay.getActualMaximum(Calendar.DATE); //firstDay달의 제일 큰 숫자 -> 마지막 날짜
		
		//3. startBlank와 endDay를 합한 결과에 endBlank를 더해서 7의 배수가 되도록 
		int endBlank =0;
		if((startBlank + endDay)%7 != 0) {
			//7에서 startBlank + endDay의 7로나눈 나머지를 빼기
			endBlank =7-((startBlank + endDay)%7);
		}
		//4.
		int totalBlank = startBlank + endBlank +endDay; 
		////////////////////////////////////////////////////////////////////
		
		//stats
		this.statsDao = new StatsDao();
		Stats stats = statsDao.selectStatsOneByNow();
		int totalCount = statsDao.selectStatsTotalCount();
		
		
		//2) 모델값(월별 가계부 리스트)을 반환하는 비지니스로직(모델) 호출
		CashBookDao cashbookDao = new CashBookDao();
		List<Map<String, Object>> list = cashbookDao.selectCashBookListByMonth(y, m);
		/*
		 달력출력에 필요한 모델값(1~4.) + DB에서 반환된 모델값(list,출력년도y, 출력월m) + 오늘날짜(today)
		 */
		//값 가져와서 저장(달력 공백)
		request.setAttribute("startBlank", startBlank);
		request.setAttribute("endDay", endDay);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("totalBlank", totalBlank);
		//달력 정보
		request.setAttribute("list", list);
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		
		
		//stats
		request.setAttribute("stats", stats);
		request.setAttribute("totalCount", totalCount);
		
		//3) 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashBookListByMonth.jsp").forward(request, response);
		
	}

}
