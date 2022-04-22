package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;


@WebServlet("/DateSearchController")
public class DateSearchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//인코딩
		String cashDate = request.getParameter("cashDate");
		request.setAttribute("cashDate", cashDate); //날짜값
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String,Object>> list = hashtagDao.selectDateRankList(cashDate);
		
		request.setAttribute("list", list);
		request.setAttribute("cashDate", cashDate);
		request.getRequestDispatcher("/WEB-INF/view/dateTagList.jsp").forward(request, response);
	}

}
