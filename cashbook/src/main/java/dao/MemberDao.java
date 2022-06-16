//dao : db연결 기능, 쿼리 수행
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.*;
public class MemberDao {
	//회원가입(insert)
	public int insertMember(Member member) {
		//db자원
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		//아이디,비밀번호 삽입하는 쿼리
		String sql = "INSERT INTO member VALUES(?,PASSWORD(?),NOW())";
		
		//db연결 
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://3.36.57.93:3306/cashbook","root","mariadb1234");
			
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //실행
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate(); //insert
		
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
		
	}
	//회원정보 수정(update)
	
	//회원 탈퇴(delete)
	
	//회원정보(select one)
	public Member selectMemberOne(String memberId){
	    Member member = null;
	    //DB자원 준비
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql="select member_id memberId, member_name memberName, member_age memberAge,update_date updateDate from member where member_id=?";
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://3.36.57.93:3306/cashbook","root","mariadb1234");
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, memberId);
            rs =stmt.executeQuery();
            
            if(rs.next()) {
                member = new Member();
                member.setMemberId(rs.getString("memberId"));
                member.setMemberName(rs.getString("memberName"));
                member.setMemberAge(rs.getInt("memberAge"));
                member.setUpdateDate(rs.getString("updateDate"));
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
        return member;
        
	}
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
			conn = DriverManager.getConnection("jdbc:mariadb://3.36.57.93:3306/cashbook","root","mariadb1234");
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
