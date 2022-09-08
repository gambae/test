package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.etc_reservationVO;
import com.VO.memberVO;
import com.VO.reservationVO;

// AI lab 및 휴게공간 예약의 기능을 담당할 클래스
public class etc_reservationDAO {
	
	ArrayList<etc_reservationVO> list = new ArrayList<etc_reservationVO>();
	memberVO vo = null;
	etc_reservationVO etc_vo = null;
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
	
	// 예약 등록 메소드
	public int register(memberVO vo2, String location, String seat, String checkout, String date) {
		try {
			connection();

			String sql = "insert into etc_reservation values(?,?,?,?,?,?,?)";
			
			psmt = conn.prepareStatement(sql);
			
			// 현재 로그인 한  사람의 예약 신청 정보를 DB에 등록해준다.
			psmt.setString(1,location);
			psmt.setString(2,vo2.getCls());
			psmt.setString(3,vo2.getId());
			psmt.setString(4,vo2.getName());
			psmt.setString(5,seat);
			psmt.setString(6,checkout);
			psmt.setString(7,date);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	// 예약된 좌석인지 판별하는 메소드 (매개변수로 날짜,위치,좌석번호를 받는다)
	public int reservedSeat(String date,String location,int seat) {
		try {
			cnt = 0;
			
			connection();
			
			// 해당 날짜의 해당 위치의 해당 좌석이 예약되있는지 판별
			String sql = "select * from etc_reservation where rsv_date=? and rsv_location=? and rsv_seat=? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,date);
			psmt.setString(2,location);
			psmt.setInt(3,seat);
			
			rs = psmt.executeQuery();
			
			// 좌석이 예약되있으면 cnt = 1, 안되있으면 cnt = 0
			if (rs.next()) {
				cnt++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			close();
		}
		return cnt;
	}
	
	// 선택 위치,날짜에 해당하는 예약 명단 확인 메소드
	public ArrayList<etc_reservationVO> ReservationSelect(String location, String date) {
		try {
			connection();

			String sql = "select * from etc_reservation where rsv_location=? and rsv_date=?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,location);
			psmt.setString(2,date);
			
			rs = psmt.executeQuery();

			while (rs.next()) {
				String rsv_location = rs.getString(1);
				String rsv_class = rs.getString(2);
				String rsv_id = rs.getString(3);
				String rsv_name = rs.getString(4);
				String rsv_seat = rs.getString(5);
				String checkout = rs.getString(6);
				String rsv_date = rs.getString(7);
				
				// 예약 정보를 etc_vo로 묶은 뒤 리스트에 출력	
				etc_vo  = new etc_reservationVO(rsv_location,rsv_class,rsv_id,rsv_name,rsv_seat,checkout,rsv_date);
				list.add(etc_vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			close();
		}
		return list;
	}
	
	// 예약 중복 체크 메소드 (매개변수로 받은 날짜에 매개변수로 받은 아이디가 예약되있는지 판별)
	public int repetitionCheck(String id,String date) {
		try {
			cnt = 0;
			
			connection();
			
			String sql = "select * from etc_reservation where rsv_id=? and rsv_date=?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,id);
			psmt.setString(2,date);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				cnt++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			close();
		}
		return cnt;
	}
	
	// 예약 삭제 메소드 (매개변수로 받은 날짜에 매개변수로 받은 아이디로 저장된 예약 정보를 삭제)
	public int reservationDelete(String id, String date) {
		try {
			connection();
			
			String sql = "delete etc_reservation where rsv_id =? and rsv_date =?";
			
			psmt = conn.prepareStatement(sql);

			psmt.setString(1,id);
			psmt.setString(2,date);
		
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	// 예약 수정 메소드
	public int reservationUpdate(memberVO vo2, String location, String update_seat, String update_checkout,String date) {
		try {
			connection();

			String sql = "update etc_reservation set rsv_seat=?,checkout=? where rsv_id=? and rsv_location=? and rsv_date=?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,update_seat);
			psmt.setString(2,update_checkout);
			psmt.setString(3,vo2.getId());
			psmt.setString(4,location);
			psmt.setString(5,date);
			
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	
	
	
}
