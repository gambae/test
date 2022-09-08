package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.commentVO;
import com.VO.freeboardVO;
import com.VO.memberVO;

// 댓글에 관련된 기능을 수행하는 클래스
public class commentDAO {
	
	ArrayList<commentVO> list = new ArrayList<commentVO>();
	memberVO vo = null;
	commentVO comment_vo = null;
	int cnt = 0;
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	public void connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String db_url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "gjai_5_4_0822" ;
			String db_pw = "smhrd4";
			
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	// 댓글 작성 메소드
	public int commentWrite(String post_id,String content,String comment_writer) {
		try {
			connection();
			
			String sql = "insert into comments values(comments_seq.nextval,?,?,?,sysdate)";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,post_id);
			psmt.setString(2,content);
			psmt.setString(3,comment_writer);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	// 해당 게시글에 작성된 댓글들을 반환하는 메소드
	public ArrayList<commentVO> commentSelect(String post_id2) {
		try {
			connection();
			
			// 가장 나중에 작성된 댓글이 먼저 보여져야하므로 작성날짜를 기준으로 내림차순 정렬
			String sql = "select * from comments where post_id = ? order by comment_date desc";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,post_id2);
			
			rs = psmt.executeQuery();

			while (rs.next()) {
				String comment_id = rs.getString(1);
				String post_id = rs.getString(2);
				String content = rs.getString(3);
				String comment_writer = rs.getString(4);
				String comment_date = rs.getString(5);

				comment_vo = new commentVO(comment_id,post_id,content,comment_writer,comment_date);
				list.add(comment_vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			close();
		}
		return list;
	}
	
	// 해당 글의 댓글의 개수를 반환하는 메소드
	public int commentNum(String post_id) {		
		int cnt = 0;
		
		try {
			connection();

			String sql = "select * from comments where post_id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,post_id);
			
			rs = psmt.executeQuery();

			while (rs.next()) {
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			close();
		}
		return cnt;
	}
	
	// 댓글 삭제 메소드
	public int commentDelete(String comment_id) {
		try {
			connection();
			
			String sql = "delete comments where comment_id=?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,comment_id);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	// 댓글 수정 메소드
	public int commentUpdate(String comment_id, String update_content) {
		try {
			connection();
			
			String sql = "update comments set content=? where comment_id=?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,update_content);
			psmt.setString(2,comment_id);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	
	
}
