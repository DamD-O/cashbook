package dao;

import java.sql.*;
import java.util.*;

import vo.CashBook;

public class CashBookDao {
	//1.가계부 목록(달력)
	public List<Map<String, Object>> selectCashBookListByMonth(int y, int m){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//db자원
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		/*
		 "SELECT "
				+ "	cashbook_no cashbookNo"
				+ "	,DAY(cash_date) day"
				+ "	,kind"
				+ "	,cash"
				+ "	,(LEFT(memo, 5) memo"
				+ "	FROM cashbook"
				+ "	WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "	ORDER BY DAY(cash_date) ASC"; 
		 */
		//쿼리문
		String sql ="SELECT "
				+ "	cashbook_no cashbookNo"
				+ "	,DAY(cash_date) day"
				+ "	,kind"
				+ "	,cash"
				+ "	,LEFT(memo, 5) memo"
				+ "	FROM cashbook"
				+ "	WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "	ORDER BY DAY(cash_date) ASC, kind ASC";
		//db연결
		try {
	         Class.forName("org.mariadb.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, y);
	         stmt.setInt(2, m);
	         rs = stmt.executeQuery();
	         //값 저장
	         while(rs.next()) {
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("cashbookNo", rs.getInt("cashbookNo"));
	            map.put("day", rs.getInt("day"));
	            map.put("kind", rs.getString("kind"));
	            map.put("cash", rs.getInt("cash"));
	            map.put("memo", rs.getString("memo"));
	            list.add(map);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	   }
	//2.가계부 입력
	public void insertCashBook(CashBook cashbook, List<String> hashtag) {
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//db연결
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동커밋 해제
			
			String sql = "INSERT INTO cashbook(cash_date, kind,cash,memo,update_date,create_date) values(?,?,?,?,NOW(),NOW())";
			
			//insert + select 방금생성된 행의 키값 ex)select 방금입력한 cashbook_no from cashbook;
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getCashDate());
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.executeUpdate(); //insert
			rs = stmt.getGeneratedKeys(); //select 방금입력한 cashbook_no from cashbook
			int cashbookNo = 0;
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			//hashtag를 저장하는 코드
			PreparedStatement stmt2 = null;
			for(String h : hashtag) {
				String sql2 = "INSERT INTO hashtag(cashbook_no, tag, create_date) VALUES(?, ?, NOW())";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, cashbookNo);
				stmt2.setString(2, h);
				stmt2.executeUpdate();
			}
			//트랜잭션(commit,rollback)
			conn.commit(); //예외가 없을때 커밋
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
		}
	//3.상세보기
	public CashBook selectCashBookOne(int cashbookNo) {
		CashBook cashBook = null;
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");//드라이버 로딩
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");

			//쿼리문
			String sql ="select cashbook_no cashbookNo,cash_date cashDate, kind, cash, memo,create_date createDate, update_date updateDate from cashbook where cashbook_no =?";
			stmt =conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			rs =stmt.executeQuery();
			
			if(rs.next()) {
				cashBook = new CashBook();
				cashBook.setCashbookNo(rs.getInt("cashbookNo"));
				cashBook.setCashDate(rs.getString("cashDate"));
				cashBook.setKind(rs.getString("kind"));
				cashBook.setCash(rs.getInt("cash"));
				cashBook.setMemo(rs.getString("memo"));
				cashBook.setCreateDate(rs.getString("createDate"));
				cashBook.setUpdateDate(rs.getString("updateDate"));
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
		return cashBook;
	}
	
	//4.삭제
	public void deleteCashBook(int cashbookNo) {
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 오토커밋 해제
			
			//삭제
			String sql = "DELETE FROM cashbook WHERE cashbook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			int row  = stmt.executeUpdate(); //delete
			//디버깅
			if(row == 1) {
				System.out.println("삭제성공");
			}else {
				System.out.println("삭제실패");
			}
			
			//해시태그 삭제
			String sql2 = "DELETE FROM hashtag WHERE cashbook_no = ?";
			stmt2=conn.prepareStatement(sql2);
			stmt2.setInt(1, cashbookNo);
			stmt2.executeUpdate();
			
			conn.commit(); //예외가 없을때 커밋
		}catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				stmt2.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
