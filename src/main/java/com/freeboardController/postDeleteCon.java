package com.freeboardController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.freeboardDAO;

@WebServlet("/postDeleteCon")
public class postDeleteCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String post_id = request.getParameter("post_id");
		
		freeboardDAO dao = new freeboardDAO();
		
		int cnt = dao.postDelete(post_id);
		
		if (cnt > 0) {
			response.sendRedirect("freeboardSelectCon");
		} else {
			System.out.println("삭제 실패");
		}
	}
}
