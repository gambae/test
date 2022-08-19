package com.freeboardController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.freeboardDAO;

@WebServlet("/postUpdateCon")
public class postUpdateCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String post_id = request.getParameter("post_id");
		String update_title = request.getParameter("update_title");
		String update_content = request.getParameter("update_content");
		
		freeboardDAO dao = new freeboardDAO();
		
		int cnt = dao.postUpdate(post_id,update_title,update_content);
		
		if (cnt > 0) {
			response.sendRedirect("freeboardSelectCon");
		} else {
			System.out.println("수정 실패");
		}
				
	}	
}
