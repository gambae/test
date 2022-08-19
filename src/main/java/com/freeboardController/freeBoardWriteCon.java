package com.freeboardController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.freeboardDAO;
import com.VO.memberVO;

@WebServlet("/freeBoardWriteCon")
public class freeBoardWriteCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		memberVO vo = (memberVO)session.getAttribute("vo");
		freeboardDAO dao = new freeboardDAO();
		
		int cnt = dao.write(vo.getId(),title,content);
		
		if (cnt > 0) {
			response.sendRedirect("freeboardSelectCon");
		} else {
			System.out.println("작성 실패");
		}
	}
}
