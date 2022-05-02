package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//아이디, 비밀번호 값을 가져와서 저장
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");

		//페이지를 요청
		request.getRequestDispatcher("/WEB-INF/view/InsertMemberForm.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //인코딩
		//값 가져오기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
	
		//디버깅
		System.out.println("memberId(insertMember) :" + memberId);
		System.out.println("memberPw(insertMember) :" + memberPw);

		
		//객체생성
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		MemberDao memberDao = new MemberDao();
		memberDao.insertMember(member);
		
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}

}
