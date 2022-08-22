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
		// viewPage에 맞는 글이 담긴 리스트 
		ArrayList<freeboardVO> list = (ArrayList)session.getAttribute("post_list");
		memberVO vo = (memberVO)session.getAttribute("vo");
		
		// 글에 관련된 기능을 담당하는 dao 댓글 관련된 기능을 담당하는 comment_dao
		freeboardDAO dao = new freeboardDAO();
		commentDAO comment_dao = new commentDAO();
		
		// 총 글의 개수
		int total = dao.postTotal();
		// 게시판 아래 1 2 3 순번을 나타내는 변수
		int pageNumber = 1;
		
		// 5개 기준으로 한 페이지에 보여줄 글 개수를 설정
		// total이 12면 5/5/2 총 3페이지가 존재
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
		
		<!-- 현재 페이지에 맞는 글의 정보들을 출력 -->
		<% for (int i=0; i<list.size(); i++) { %>
		<% String post_id = list.get(i).getPost_id(); %>
		<% String title = list.get(i).getTitle(); %>
		<% String writer = list.get(i).getWriter(); %>
		<% String content = list.get(i).getContent(); %>
		<% String post_date = list.get(i).getPost_date(); %>
		<% int views = list.get(i).getViews(); %>
			<tr>
				<td><%= list.get(i).getPost_id() %></td>
				<!-- 제목을 클릭하면 해당 글의 정보가 전달된다. 제목 옆에는 해당 글의 댓글 개수 -->
				<td><a href="View.jsp?post_id=<%=post_id%>&title=<%=title%>&writer=<%=writer%>&content=<%=content%>&post_date=<%=post_date%>&views=<%=views%>"><%= list.get(i).getTitle() %></a> [<%=comment_dao.commentNum(post_id) %>]</td>
				<td><%= list.get(i).getWriter() %></td>
				<td><%= list.get(i).getPost_date().substring(0,10) %></td>
				<td><%= list.get(i).getViews() %></td>			
			</tr>
		<% } %>
	</table>
	
	<!-- 1부터 pageNumber까지의 목록,클릭하는순간 해당 숫자가 viewPage가 된다.-->
	<% for (int i=1; i<=pageNumber; i++) {	
			out.print("<a href='freeboardSelectCon?num="+i+"'>"+i+"</a> ");
		} 
	%>
	
	<!-- 로그인 한 사람만 글쓰기 가능 -->
	<% if (vo != null) { %>
		<button onclick="location.href='Write.jsp'">글쓰기</button>
	<% } else { %>
		<button onclick="alert('로그인하시오')">글쓰기</button>
	<% } %>
</body>
</html>