package com.freeboardController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.freeboardDAO;
import com.VO.freeboardVO;
import com.VO.reservationVO;

@WebServlet("/freeboardSelectCon")
public class freeboardSelectCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int viewPage = 1;
		
		String num = request.getParameter("num");
		
		if (num != null) {
			viewPage = Integer.parseInt(num);
		}
		
		freeboardDAO dao = new freeboardDAO();

		ArrayList<freeboardVO> list = dao.postSelect(viewPage);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("post_list",list);
		
		response.sendRedirect("Freeboard.jsp");
	}
}
