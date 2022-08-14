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
import com.VO.memberVO;
import com.VO.reservationVO;

@WebServlet("/ReservationUpdateCon")
public class ReservationUpdateCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		memberVO vo = (memberVO) session.getAttribute("vo");
		
		reservationDAO dao = new reservationDAO();
		
		// ReservationUpdate.jsp에서 전달해준 update_checkout
		String update_checkout = request.getParameter("update_checkout");
		
		// reservationDAO 클래스의 reservationUpdate() 메소드에게 현재 로그인한 사람의 아이디,수정할 퇴실 시간을 전달
		int cnt = dao.reservationUpdate(vo.getId(),update_checkout);
		
		// 수정에 성공했으면
		if (cnt > 0) {
			// 예약 정보가 담긴 reservation_list 세션 호출
			ArrayList<reservationVO> list = (ArrayList)session.getAttribute("reservation_list");
			
			for (int i=0; i<list.size(); i++) {
				// reservation_list 세션에있는 reservationVO 객체의 예약 아이디 와 현재 로그인 한 아이디가 같다면
				if (vo.getId().equals(list.get(i).getRsv_id())) {
					//  그 reservationVO 객체의 체크아웃 시간을 수정된 체크아웃 시간으로 변경
					list.get(i).setCheckout(update_checkout);
				}
			}
			response.sendRedirect("ReservationSelect.jsp");
		} else {
			System.out.println("수정 실패");
		}
	}
}
