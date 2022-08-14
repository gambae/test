package com.reservationController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.memberDAO;
import com.DAO.reservationDAO;
import com.VO.memberVO;

@WebServlet("/ReservationCon")
public class ReservationCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		// formaction 태그로 보내진 반 이름
		String cls = request.getParameter("cls");
		// select 태그로 보내진 체크아웃 시간
		String time = request.getParameter("time");
		// 현재 로그인한 사람의 정보가 담신 vo 세션
		memberVO vo = (memberVO) session.getAttribute("vo");
		// 예약 기능 메소드를 사용 할 reservationDAO 객체
		reservationDAO dao = new reservationDAO();
		
		// 로그인이 안되있으면 cnt > 0을 판별하는 if문에서 cnt를 사용할 수 없으므로 전역변수로 설정
		int cnt = 0;
		
		// 로그인이 안된 사람은 예약을 할 수 없기때문에 로그인이 되어있으면
		if (vo != null) {
			// 예약 신청을 해주는 register() 메소드를 호출 후 성공,실패 여부를 리턴 받는다.
			// 로그인한 사람의 정보가 담긴 vo세션과 , 반 , 체크아웃 시간을 매개변수로 보내준다.
 			cnt = dao.register(vo,cls,time);
		}
		
		if (cnt > 0) {
			response.sendRedirect("Reservation.jsp");
		} else {
			System.out.println("신청 실패");
			response.sendRedirect("Reservation.jsp");
		}
		
	}
}
