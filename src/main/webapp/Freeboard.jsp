<%@page import="com.DAO.commentDAO"%>
<%@page import="com.DAO.freeboardDAO"%>
<%@page import="com.VO.memberVO"%>
<%@page import="com.VO.freeboardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
		ArrayList<freeboardVO> list = (ArrayList)session.getAttribute("post_list");
		memberVO vo = (memberVO)session.getAttribute("vo");
		freeboardDAO dao = new freeboardDAO();
		commentDAO comment_dao = new commentDAO();
		
		int total = dao.postTotal();
		int pageNumber = 1;
		
		if (total % 5 == 0) {
			pageNumber = total/5;
		} else {
			pageNumber = (total/5)+1;
		}
	%>

	<h1>인공지능 사관학교 게시판</h1>
	<table border="1px solid black;" width="500px;">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		<% for (int i=0; i<list.size(); i++) { %>
		<% String post_id = list.get(i).getPost_id(); %>
		<% String title = list.get(i).getTitle(); %>
		<% String writer = list.get(i).getWriter(); %>
		<% String content = list.get(i).getContent(); %>
		<% String post_date = list.get(i).getPost_date(); %>
			<tr>
				<td><%= list.get(i).getPost_id() %></td>
				<td><a href="View.jsp?post_id=<%=post_id%>&title=<%=title%>&writer=<%=writer%>&content=<%=content%>&post_date=<%=post_date%>"><%= list.get(i).getTitle() %></a> [<%=comment_dao.commentNum(post_id) %>]</td>
				<td><%= list.get(i).getWriter() %></td>
				<td><%= list.get(i).getPost_date().substring(0,10) %></td>
				<td>0</td>			
			</tr>
		<% } %>
	</table>
	
	<% for (int i=1; i<=pageNumber; i++) {	
			out.print("<a href='freeboardSelectCon?num="+i+"'>"+i+"</a> ");
		} 
	%>
		
	<% if (vo != null) { %>
		<button onclick="location.href='Write.jsp'">글쓰기</button>
	<% } else { %>
		<button onclick="alert('로그인하시오')">글쓰기</button>
	<% } %>
</body>
</html>