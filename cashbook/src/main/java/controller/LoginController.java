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


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	//session : 브라우저에서 동일한 접속자가 사용할수 있는 공간 
	
	//로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionMemberId =(String)session.getAttribute("sessionMemberId");
		if(sessionMemberId != null) {
			//이미 로그인 되어있는 상태
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
		}
		
		//로그인 되어있는 멤버면 리다이렉트
		request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
	}
	
	//로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되어있는 멤버면 리다이렉트
		//아이디 & 비번 값 받기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
	
		//객체생성
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		//모델호출
		MemberDao memberDao = new MemberDao();
		String returnMemberId = memberDao.selectMemberByLogin(member); //로그인 dao메소드 호출
		if(returnMemberId == null) {
			//로그인 실패시 로그인 폼을 재요청
			System.out.println("로그인 실패(LoginController-post)");
			request.getSession().setAttribute("msg", "사용자 정보가 없습니다. 다시 로그인 해주세요.!");
			response.sendRedirect(request.getContextPath()+"/LoginController"); //get방식
			return; //종료
		}
		//로그인성공 / 다음 요청부터는 세션에 저장되어 있는지 확인 (없으면 null->연결X)
		HttpSession session = request.getSession(); ///현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴
		session.setAttribute("sessionMemberId", returnMemberId); //세션에 가져온 정보 저장
		//로그인 성공하면 메인화면으로 이동
		System.out.println("로그인 성공(LoginController-post)");
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController?msg=LoginSuccess");
		
	}

}
