package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.etc_reservationVO;
import com.VO.memberVO;
import com.VO.reservationVO;

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

	public int register(memberVO vo2, String location, int seat, String checkout, String date) {
		try {
			connection();

			String sql = "insert into etc_reservation values(?,?,?,?,?,?,?)";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,location);
			psmt.setString(2,vo2.getCls());
			psmt.setString(3,vo2.getId());
			psmt.setString(4,vo2.getName());
			psmt.setInt(5,seat);
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
	
	public int reservedSeat(String date,String location,int seat) {
		try {
			cnt = 0;
			
			connection();
			
			String sql = "select * from etc_reservation where rsv_date=? and rsv_location=? and rsv_seat=? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,date);
			psmt.setString(2,location);
			psmt.setInt(3,seat);
			
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

	public int ReservationDelete(String id, String date) {
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
	
	
	
	
}
