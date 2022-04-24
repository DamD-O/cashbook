package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate(); //session 갱신메서드 :기존세션을 지우고 새로운 세션공간 부여
		/*HttpSession session = request.getSession();
		session.invalidate();*/
		//로그아웃 후 로그인 페이지로 이동
		response.sendRedirect(request.getContextPath()+"/LoginController");
	
	}

}
