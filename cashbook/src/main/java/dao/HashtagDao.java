package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HashtagDao {
	//1.전체랭크
	public List<Map<String,Object>> selectTagRankList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql =" SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) rank"
					+ "	FROM"
					+ "	( SELECT tag,COUNT(*) cnt"
					+ "	FROM hashtag"
					+ "	GROUP BY tag) t;";
			
			stmt = conn.prepareStatement(sql);
			rs =stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tag", rs.getString("tag"));
				map.put("cnt", rs.getString("cnt"));
				map.put("rank", rs.getString("rank"));
				list.add(map);
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
		return list;
	}
	//2.수입/지출별 랭크
	public List<Map<String, Object>> selectKindRankList(String kind){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql ="	SELECT kind, t.tag Tag,t.cnt cnt,RANK() over(ORDER BY t.cnt DESC) rank"
					+ "	FROM"
					+ "	(SELECT c.kind kind, tag, COUNT(*) cnt"
					+ "	FROM hashtag t INNER JOIN cashbook c"
					+ "	ON t.cashbook_no = c.cashbook_no"
					+ "	WHERE c.kind = ?"
					+ "	GROUP BY t.tag) t";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, kind);
			rs =stmt.executeQuery();
			
		 while(rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("kind", rs.getString("kind"));
			map.put("tag", rs.getString("Tag"));
			map.put("cnt", rs.getString("cnt"));
			map.put("rank", rs.getString("rank"));
			list.add(map);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//3.날짜별 랭크
	public List<Map<String, Object>> selectDateRankList(String cashDate){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql ="SELECT cash_date cashDate,t.tag tag, t.cnt cnt, RANK() over(ORDER BY t.cnt DESC) rank"
					+ "	FROM"
					+ "	(SELECT c.cash_date, tag, COUNT(*) cnt"
					+ "	FROM hashtag t INNER JOIN cashbook c"
					+ "	ON t.cashbook_no = c.cashbook_no"
					+ "	WHERE c.cash_date =?"
					+ "	GROUP BY t.tag) t";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cashDate);
			rs =stmt.executeQuery();
			
			 while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashDate", rs.getString("cashDate"));
				map.put("tag", rs.getString("tag"));
				map.put("cnt", rs.getString("cnt"));
				map.put("rank", rs.getString("rank"));
				list.add(map);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
