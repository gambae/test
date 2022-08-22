package com.commentController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.commentDAO;

@WebServlet("/commentDeleteCon")
public class commentDeleteCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comment_id = request.getParameter("comment_id");
		
		String post_id = request.getParameter("post_id");		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String post_date = request.getParameter("post_date");	
		int views = Integer.parseInt(request.getParameter("views"));
		
		commentDAO dao = new commentDAO();
		
		int cnt = dao.commentDelete(comment_id);
		
		if (cnt > 0) {
			request.setAttribute("title", title);
			request.setAttribute("writer", writer);
			request.setAttribute("content", content);
			request.setAttribute("post_date", post_date);
			request.setAttribute("post_id", post_id);
			request.setAttribute("views", views);
			
			RequestDispatcher rd = request.getRequestDispatcher("View.jsp");
			rd.forward(request, response);
		} else {
			System.out.println("삭제 실패");
		}
	}
}
