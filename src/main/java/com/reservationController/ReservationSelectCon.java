package com.reservationController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.reservationDAO;
import com.VO.reservationVO;

@WebServlet("/ReservationSelectCon")
public class ReservationSelectCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 예약 현황 제출시 전달했던 반이름
		String cls = request.getParameter("cls");
		
		reservationDAO dao = new reservationDAO();
		
		// 반이름을 매개변수로 넘겨주고 한 사람의 예약정보를 가진 reservationVO 객체 여러개를 배열로 리턴받는다.
 		ArrayList<reservationVO> list = dao.ReservationSelect(cls);
		
		HttpSession session = request.getSession();
		
		// 세션의 이름을 reservation_list로 지정
		session.setAttribute("reservation_list",list);
		
		response.sendRedirect("ReservationSelect.jsp");
	}
}
