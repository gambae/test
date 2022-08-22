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
		
		// 쿼리스트링으로 전달한 값을 받아온다.
		String location = request.getParameter("location");
		String date = request.getParameter("date");
		
		etc_reservationDAO dao = new etc_reservationDAO();
		
		// 선택 장소,선택 날짜에 해당하는 예약 정보를 etc_reservationVO로 묶은 리스트를 리턴받는다.
 		ArrayList<etc_reservationVO> list = dao.ReservationSelect(location,date);
		
		HttpSession session = request.getSession();

		// 리스트를 세션으로 저장 후 페이지 이동
		session.setAttribute("etc_reservation_list",list);
		response.sendRedirect("etc_ReservationSelect.jsp");
		
		
		
		

		
	}
}
