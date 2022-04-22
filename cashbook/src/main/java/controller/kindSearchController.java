package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;


@WebServlet("/kindSearchController")
public class kindSearchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");//인코딩
		String kind = request.getParameter("kind"); //종류값 가져오기
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String,Object>> list = hashtagDao.selectKindRankList(kind);
		
		request.setAttribute("list", list);
		request.setAttribute("kind", kind);
		request.getRequestDispatcher("/WEB-INF/view/kindTagList.jsp").forward(request, response);
	}

}
