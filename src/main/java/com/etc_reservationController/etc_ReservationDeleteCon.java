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
		
		HttpSession session = request.getSession();
		
		memberVO vo = (memberVO) session.getAttribute("vo");
		String date = (String)session.getAttribute("rsv_date");
		
		etc_reservationDAO dao = new etc_reservationDAO();
		
		int cnt = dao.ReservationDelete(vo.getId(),date);
		
		session.removeAttribute("rsv_date");
		
		if (cnt > 0) {
			response.sendRedirect("Reservation.jsp");
		} else {
			System.out.println("삭제 실패");
		}
	}
}
