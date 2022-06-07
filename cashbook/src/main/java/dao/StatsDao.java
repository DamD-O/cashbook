package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import vo.Stats;

public class StatsDao {
	public void insertStats() {
		// insert into stats(day, cnt) values(CURDATE(),1) ;
		// db자원
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into stats(day, cnt) values(CURDATE(),1)";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://3.36.57.93:3306/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			
			int row = stmt.executeUpdate(); // 입력된 행

			if (row == 1) {
				System.out.println("1행 입력 성공");
			} else {
				System.out.println("1행 입력 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db자원 종료
			try {
				
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 오늘 접속자
	public Stats selectStatsOneByNow() {// -> Listener, Controller
		// db자원
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Stats stats = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://3.36.57.93:3306/cashbook","root","mariadb1234");
			// SELECT day,cnt FROM stats WHERE DAY = CURDATE();
			String sql = "SELECT day,cnt FROM stats WHERE DAY = CURDATE()";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if(rs.next()) {
				stats = new Stats();
				stats.setDay(rs.getString("day"));
				stats.setCnt(rs.getInt("cnt"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db자원 종료
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stats;

	}
	//업데이트
	public void updateStatsByNow() { // ->Listener
		// update stats set cnt= cnt+1 WHERE DAY = CURDATE();
		// db자원
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://3.36.57.93:3306/cashbook","root","mariadb1234");
			String sql = "update stats set cnt = cnt+1 WHERE DAY = CURDATE()";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			int row = stmt.executeUpdate(); // 입력된 행
			
			//디버깅
			if (row == 1) {
				System.out.println("방문자 update 성공");
			} else {
				System.out.println("방문자 update 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db자원 종료
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 전체 접속자
	public int selectStatsTotalCount() { // ->Controller
		// select sum(cnt) from stats
		// db자원
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCnt = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://3.36.57.93:3306/cashbook","root","mariadb1234");
			String sql = "select sum(cnt) totalCnt from stats";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			 
			if(rs.next()) {
				totalCnt = rs.getInt("totalCnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// db자원 종료
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalCnt;
	}

}
