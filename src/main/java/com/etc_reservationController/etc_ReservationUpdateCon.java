package com.etc_reservationController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.etc_reservationDAO;
import com.VO.etc_reservationVO;
import com.VO.memberVO;

@WebServlet("/etc_ReservationUpdateCon")
public class etc_ReservationUpdateCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String location = request.getParameter("location");
		String date = request.getParameter("date");
		String seat = request.getParameter("seat");
		
		String update_checkout = request.getParameter("checkout");
		String update_seat = request.getParameter("update_seat");
		
		HttpSession session = request.getSession();
		
		memberVO vo = (memberVO) session.getAttribute("vo");
		
		etc_reservationDAO dao = new etc_reservationDAO();

		// 좌석을 수정하지않고 시간만 수정하면 update_seat에는 null값이 들어가므로
		// update_seat에 기존에 예약했던 좌석을 사용한다. 
		// null값이 아니면 수정 할 때 좌석을 바꾼것이므로 바꾼 좌석 값을 사용한다.		
		if (update_seat == null) {
			update_seat = seat;
		}
		
		// 수정한 좌석,시간을 매개변수로 예약 변경 메소드를 통해 성공,실패 여부를 리턴받는다.
		int cnt = dao.reservationUpdate(vo,location,update_seat,update_checkout,date);
		
		if (cnt > 0) {
			response.sendRedirect("ReservationUpdateSuccess.html");
		} else {
			System.out.println("수정 실패");
			response.sendRedirect("Reservation.jsp");
		}
		
		
	}
}
