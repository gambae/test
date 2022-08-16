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

@WebServlet("/etc_ReservationCon")
public class etc_ReservationCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String location = request.getParameter("location");
		String date = request.getParameter("date");
		String checkout = request.getParameter("checkout");
		int seat = Integer.parseInt(request.getParameter("seat"));
			
		HttpSession session = request.getSession();
		
		memberVO vo = (memberVO) session.getAttribute("vo");
		
		etc_reservationDAO dao = new etc_reservationDAO();
		
		int cnt = dao.register(vo,location,seat,checkout,date);
		
		if (cnt > 0) {
			response.sendRedirect("ReservationSuccess.html");
		} else {
			System.out.println("신청 실패");
			response.sendRedirect("Reservation.jsp");
		}
		
	}
}
