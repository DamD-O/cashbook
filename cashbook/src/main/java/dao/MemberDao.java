//dao : db연결 기능, 쿼리 수행
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import vo.*;
public class MemberDao {
	//회원가입(insert)
	
	//회원정보 수정(update)
	
	//회원 탈퇴(delete)
	
	//회원정보(select one)
	
	//로그인(성공하면 입력한 아이디와 비밀번호가 존재하면 아이디값 리턴, 없으면 null리턴->로그인 실패)
	public String selectMemberByLogin(Member member) {
		String memberId = null;
		
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs =stmt.executeQuery();
			
			while(rs.next()) {
				memberId = rs.getString("memberId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberId;
		
	}
}
