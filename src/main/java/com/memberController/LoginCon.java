package com.memberController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.memberDAO;
import com.VO.memberVO;

@WebServlet("/LoginCon")
public class LoginCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// Login.html에서 입력한 id,pw를 가져온다.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// ★★memberDAO에 있는 메소드를 실행시키려면 memberDAO 객체를 반드시 만들어줘야한다★★
		memberDAO dao = new memberDAO();
		// memberDAO에서 로그인 기능을 담당하는 login메소드에 id,pw값을 넘겨주고 memberVO 객체를 리턴받는다
		memberVO vo = dao.login(id,pw);
		
		// 사용자가 school_member에 등록되어 있으면 
		if (vo != null) {
			HttpSession session = request.getSession();
			
			// vo라는 이름을 가진 세션 생성, 세션을 삭제하기 전까지 유지된다.
			// ★★ vo세션에는 로그인한 사람의 반,아이디,이메일,이름이 담겨져있다
			session.setAttribute("vo",vo);
			
			response.sendRedirect("Main.jsp");
		} else {
			// vo 객체가 null, 즉 사용자가 school_member에 없으면 LoginF.html로 이동
			response.sendRedirect("LoginF.html");
		}
	}
}
