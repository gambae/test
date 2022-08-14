package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import com.VO.memberVO;
import com.VO.reservationVO;

// 예약에 관련된 기능만 수행하는 클래스
public class reservationDAO {
	
	ArrayList<reservationVO> list = new ArrayList<reservationVO>();
	memberVO vo = null;
	reservationVO rsv_vo = null;
	int cnt = 0;
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;

	public void connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String db_url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(db_url, db_id, db_pw);
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
		
		// 현재 예약한 사람의 수를 알려주는 메소드, 호출할 때 조회할 반을 매개변수로 넘겨준다
		public int ReservationCount(String cls) {
			try {
				// ★★메소드를 호출하면 전역변수 cnt에 전에 조회한 cnt가 누적되므로 호출할때마다 0으로 초기화
				cnt = 0;
				
				connection();
				
				// 매개변수로 받은 반에 해당하는 사람들을 조회
				String sql = "select * from lecture_reservation where rsv_class=?";
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,cls);
				
				rs = psmt.executeQuery();
				
				// 한 행이 검색될때마다 즉,매개변수로받은 반으로 예약되어있는 행이있으면
				// cnt를 1씩증가
				while (rs.next()) {
					cnt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}  finally {
				close();
			}
			// 매개변수로 받은 반의 행의 수, 즉 사람들의 수를 리턴
			return cnt;
		}
		
		
		// 중복체크 메소드 (매개변수로 받은 id가 lecture_reservation 테이블에 있는지 검사)
		public int repetitionCheck(String id) {
			try {
				// ReservationCount 메소드를 통해 cnt값이 증가되어있을수도 있기 때문에 0으로 초기화
				cnt = 0;
				
				connection();
				
				// 입력받은 id가 lecture_reservation에 있는지 검사
				String sql = "select * from lecture_reservation where rsv_id=?";
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,id);
				
				rs = psmt.executeQuery();
				
				// 테이블에 이미 예약되어있으면 cnt값 1증가
				if (rs.next()) {
					cnt++;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}  finally {
				close();
			}
			// 이미 예약되어있으면 cnt = 1, 없으면 cnt = 0
			return cnt;
		}
		
		
		// 예약 등록 메소드 (전역 변수로 설정된 vo는 로그인한 사람의 정보가 담긴 세션이 아니므로 vo2로 사용)
		public int register(memberVO vo2, String cls, String time) {
			try {
				connection();
				
				// lecture_reservation 테이블에 예약 정보 추가
				// 첫번째 컬럼은 예약 순서를 알려줄 seq이름의 시퀸스 사용
				// 마지막 컬럼에는 예약한 날짜를 알려줄 sysdate
				String sql = "insert into lecture_reservation values(seq.nextval,?,?,?,?,sysdate)";
				
				psmt = conn.prepareStatement(sql);
				
				// 테이블에 현재 로그인한사람이 신청한 반, 로그인한 사람의 아이디,이름 신청한 체크아웃 시간을 추가
				psmt.setString(1,cls);
				psmt.setString(2,vo2.getId());
				psmt.setString(3,vo2.getName());
				psmt.setString(4,time);
				
				cnt = psmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			// 예약 성공,실패 여부를 리턴
			return cnt;
		}
		
		
		// 각 반에 해당하는 예약 조회 메소드
		public ArrayList<reservationVO> ReservationSelect(String cls) {
			try {
				connection();
				
				// 해당 반에 있는 모든 예약 정보를 가져온다
				String sql = "select * from lecture_reservation where rsv_class=?";
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,cls);
				
				rs = psmt.executeQuery();
				
				// ResultSet 행 하나당 사람 한명의 예약정보
				while (rs.next()) {
					String rsv_cls = rs.getString(2);
					String rsv_id = rs.getString(3);
					String rsv_name = rs.getString(4);
					String checkout = rs.getString(5);
					String rsv_date = rs.getString(6);
					
					// 사람 한명의 예약정보들을 묶어줄 rsv_vo 객체
					rsv_vo = new reservationVO(rsv_cls,rsv_id,rsv_name,checkout,rsv_date);
					list.add(rsv_vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}  finally {
				close();
			}
			return list;
		}
		
		
		// 예약 수정 메소드
		public int reservationUpdate(String id, String update_checkout) {
			try {
				connection();
				
				String sql = "update lecture_reservation set checkout = ? where rsv_id =?";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1,update_checkout);
				psmt.setString(2,id);
			
				cnt = psmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return cnt;
		}
		
		
		// 예약 삭제 메소드
		public int ReservationDelete(String id) {
			try {
				connection();
				
				String sql = "delete lecture_reservation where rsv_id =?";
				
				psmt = conn.prepareStatement(sql);

				psmt.setString(1,id);
			
				cnt = psmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return cnt;
		}
	
}
