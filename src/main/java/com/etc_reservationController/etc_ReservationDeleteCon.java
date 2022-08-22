package com.etc_reservationController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.etc_reservationDAO;
import com.DAO.reservationDAO;
import com.VO.memberVO;

@WebServlet("/etc_ReservationDeleteCon")
public class etc_ReservationDeleteCon extends HttpServlet {	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String date = request.getParameter("date");
		
		HttpSession session = request.getSession();
		
		memberVO vo = (memberVO) session.getAttribute("vo");	
		
		etc_reservationDAO dao = new etc_reservationDAO();
		
		// 매개변수로 보낸 날짜에 로그인 한 사람의 id로 예약한 정보를 삭제 후 성공,실패여부를 리턴받는다.
		int cnt = dao.reservationDelete(vo.getId(),date);
		
		if (cnt > 0) {
			response.sendRedirect("Reservation.jsp");
		} else {
			System.out.println("삭제 실패");
		}
	}
}
