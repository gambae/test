package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.memberVO;

// 회원들에 관한 기능만 수행하는 클래스
public class memberDAO {
	
	// 자주쓰는 변수들을 전역변수로 빼줘서 중복으로 사용하는것을 방지
	// 메소드 안에서 전역변수를 사용할때는 자료형 선언x 
	ArrayList<memberVO> list = new ArrayList<memberVO>();
	memberVO vo = null;
	int cnt = 0;
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	// 커넥션 메소드
	public void connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String db_url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String db_id = "hr" ;
			String db_pw = "hr";
			
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ResultSet,PrepareStatement,Connection 객체를 종료해주는 메소드
	public void close() {
		try {
			// conn -> psmt -> rs 순서로 연결해주었기때문에 역순으로 닫아준다.
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
	
	// 회원가입 메소드 (회원가입이 됬는지 안됬는지만 판별해서 JoinCon으로 cnt를 리턴)
	public int join(String cls, String id, String pw, String email, String name, String birth, String tel) {
		try {
			connection();
			
			// school_member 테이블에 컬럼은 8개
			// JoinCon에서 받아온 값은 7개, 마지막 값은 가입한 날짜를 의미하는 sysdate로 대체
			String sql = "insert into school_member values(?,?,?,?,?,?,?,sysdate)";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,cls);
			psmt.setString(2,id);
			psmt.setString(3,pw);
			psmt.setString(4,email);
			psmt.setString(5,name);
			psmt.setString(6,birth);
			psmt.setString(7,tel);
			
			// executeUpdate 메소드는 sql문 실행 후 적용된 행 수를 리턴
			// insert문이 적용되면 1행이 추가, 적용 되지않으면 0행 추가이므로 0 또는 1을 리턴 
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		// 회원가입 성공,실패 여부를 리턴
		return cnt;
	}
	
	// 로그인 메소드 (로그인 한 사람의 정보를 묶은 memberVO 객체를 리턴)
	public memberVO login(String id, String pw) {
		try {
			connection();
			
			// LoginCon에서 받은 id,pw를 가지고 school_member 테이블에 사용자가 존재하는지 판별
			String sql = "select * from school_member where id = ? and pw = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,id);
			psmt.setString(2,pw);
			
			// select문에서는 executeQuery 메소드 사용, 표 형태의 ResultSet 객체를 리턴
			rs = psmt.executeQuery();
			
			// 서로 다른 두 사람의 id와pw가 같은 수는 없기 때문에
			// 위에 sql문을 만족하는 행은 1개아니면 0개여서 if문 사용
			// school_member 테이블에 id,pw 값이 일치하는 행이 있다면
			if (rs.next()) {
				// id,pw가 일치하는 사용자의 반,아이디,이메일,이름을 가져온다
				String getCls = rs.getString(1);
				String getId = rs.getString(2);
				String getEmail = rs.getString(4);
				String getName = rs.getString(5);
				
				// id,pw가 일치하는 사용자의 반,아이디,이메일,이름을 묶은 memberVO 객체 생성
				// id,pw가 일치하는 사용자가 없으면 vo에는 null값이 들어간다.
				vo = new memberVO(getCls,getId,getEmail,getName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}
	
	
	

}
