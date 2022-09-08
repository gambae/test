package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.commentVO;
import com.VO.freeboardVO;
import com.VO.memberVO;
import com.VO.reservationVO;

// 게시판 글의 기능을 담당하는 클래스
public class freeboardDAO {
	
	ArrayList<freeboardVO> list = new ArrayList<freeboardVO>();
	memberVO vo = null;
	freeboardVO freeboard_vo = null;
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
	
	// 게시글 작성 메소드
	public int write(String id, String title, String content) {
		try {
			connection();
			
			// 조회수는 0으로 기본값 설정
			String sql = "insert into freeboard values(freeboard_seq.nextval,?,?,?,sysdate,0)";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,title);
			psmt.setString(2,id);
			psmt.setString(3,content);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	// 게시글 총 개수를 반환해주는 메소드
	public int postTotal() {
		
		int total = 0;
		
		try {
			connection();

			String sql = "select * from freeboard";
			
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			
			while (rs.next()) {
				total++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			close();
		}
		return total;
	}
	
	// 마지막 게시글의 post_id를 알려주는 메소드
	public int lastPostId() {
		int lastPostID = 0;
		try {
			connection();
			
			// post_id를 내림차순으로 정렬
			String sql = "select * from freeboard order by post_id desc";
			
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			
			if (rs.next()) {
				lastPostID = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			close();
		}
		return lastPostID;
	}
	
	// viewPage에 맞는 게시글 목록을 담은 리스트를 반환하는 메소드
	// ★이 부분 어려워서 이해안되시면 저한테 물어보세용★
	public ArrayList<freeboardVO> postSelect(int viewPage) {
		
		// 보여줄 게시글 범위의 마지막 post_id
		// 총 게시글의 12개라 가정했을때 viewPage가 1이면 postRange는 12
		// viewPage가 2이면 postRange는 7
		int postRange = lastPostId()-(viewPage-1)*5;
		
		try {
			connection();
			
			// 늦게 작성한 글, 즉 post_id가 큰 글이 게시판 위에 보여져야하므로 post_id를 내림차순으로 정령
			String sql = "select * from freeboard where post_id between ? and ? order by post_id desc";
			
			psmt = conn.prepareStatement(sql);
			
			// 5개 기준이기 때문에 postRange가 12라면
			// 8,9,10,11,12번 글이 리스트에 담긴다 
			psmt.setInt(1,postRange-4);
			psmt.setInt(2,postRange);
			
			rs = psmt.executeQuery();

			while (rs.next()) {
				String post_id = rs.getString(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				String post_date = rs.getString(5);
				int views = rs.getInt(6);
				
				freeboard_vo = new freeboardVO(post_id,title,writer,content,post_date,views);
				list.add(freeboard_vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			close();
		}
		return list;
	}
	
	// 게시글 수정 메소드
	public int postUpdate(String post_id, String update_title, String update_content) {
		try {
			connection();
			
			String sql = "update freeboard set title=?,content=?,post_date=sysdate where post_id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,update_title);
			psmt.setString(2,update_content);
			psmt.setString(3,post_id);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	// 게시글 삭제 메소드
	public int postDelete(String post_id) {	
		try {
			connection();
			
			String sql = "delete freeboard where post_id = ?";
			
			psmt = conn.prepareStatement(sql);

			psmt.setString(1,post_id);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	// 조회수 증가 메소드
	public void viewsUpdate(int views,String post_id) {
		try {
			connection();
			
			String sql = "update freeboard set views=? where post_id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1,views);
			psmt.setString(2,post_id);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	
	
	
	
	
	
}
