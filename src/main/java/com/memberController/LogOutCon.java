package com.memberController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VO.memberVO;

@WebServlet("/LogOutCon")
public class LogOutCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// servlet에서는 session 내장 객체가 없기때문에 객체를 생성해줘야한다.
		HttpSession session = request.getSession();
		
		// 로그인한 사람의 정보가담긴 vo 이름의 세션을 삭제 후 메인페이지로 이동
		session.removeAttribute("vo");
		
		// ★★메인페이지로 이동하면 vo 세션은 null값이 된다.
		response.sendRedirect("Main.jsp");
		
	}
}
