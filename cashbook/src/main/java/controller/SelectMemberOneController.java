package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.Member;

@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //아이디, 비밀번호 값을 가져와서 저장
        String memberId = request.getParameter("memberId");
        System.out.println(memberId + "memberId(SelectMemberOneController)");
        
        MemberDao memberDao = new MemberDao();
        Member member = new Member();
        member = memberDao.selectMemberOne(memberId);
        
        //속성값 가져오기
        request.setAttribute("memberId", member.getMemberId());
        request.setAttribute("memberName", member.getMemberName());
        request.setAttribute("memberAge", member.getMemberAge());
        request.setAttribute("updateDate", member.getUpdateDate());
        System.out.println(member + "member(SelectMemberOneController)");
        
        //페이지를 요청
        request.getRequestDispatcher("/WEB-INF/view/MemberOne.jsp").forward(request, response);
    }
}
