package com.etc_reservationController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.etc_reservationDAO;
import com.VO.etc_reservationVO;

@WebServlet("/etc_ReservationSelectCon")
public class etc_ReservationSelectCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String location = request.getParameter("location");
		String date = request.getParameter("date");
		
		etc_reservationDAO dao = new etc_reservationDAO();
		
 		ArrayList<etc_reservationVO> list = dao.ReservationSelect(location,date);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("rsv_date", date);
		
		session.setAttribute("etc_reservation_list",list);
		
		response.sendRedirect("etc_ReservationSelect.jsp");
		
		
		
		

		
	}
}
