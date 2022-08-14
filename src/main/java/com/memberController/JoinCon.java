package com.memberController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.memberDAO;

@WebServlet("/JoinCon")
public class JoinCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST방식으로 Join.html에서 보냈기때문에 UTF-8로 인코딩
		request.setCharacterEncoding("utf-8");
		
		// form태그에서 입력했던 값들을 request 객체를 통해 servlet으로 받아와준다.
		String cls = request.getParameter("cls");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String tel = request.getParameter("tel");
		
		// memberDAO 객체생성
		memberDAO dao = new memberDAO();
		
		// dao객체에게 회원가입 기능을하는 join메소드 요청
		// Join.html에서 적었던 값들을 매개변수로 넘겨주고 회원가입 성공,실패 여부를 0또는1로 리턴받는다
		int cnt = dao.join(cls,id,pw,email,name,birth,tel);
		
		// 회원가입 성공시 메인페이지로 이동
		if (cnt > 0)
			response.sendRedirect("Main.jsp");
		else
			System.out.println("등록 실패");
	}
}
