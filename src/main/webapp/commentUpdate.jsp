<%@page import="com.VO.commentVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.DAO.commentDAO"%>
<%@page import="com.VO.memberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table {border:1px solid black; height:300px; width:400px;}
	.content {height:300px; font-size:200%;}
	td {border:1px solid black;}
	.comment_area {height:60px;}
	.comment_write {height:60px;}
	.comment_flex {display:flex; height:60px;}
	textarea {width:310px;}
</style>
</head>
<body>
	<%		
		memberVO vo = (memberVO)session.getAttribute("vo");
 
		String post_id;
		String title;
		String writer;
		String content;
		String post_date;
		int views;
		
		String comment_id = request.getParameter("comment_id");

		if (request.getParameter("post_id") == null) {
			post_id = (String)request.getAttribute("post_id");
		} else {
			post_id = request.getParameter("post_id");
		}
		
		if (request.getParameter("title") == null) {
			title = (String)request.getAttribute("title");
		} else {
			title = request.getParameter("title");
		}
		
		if (request.getParameter("writer") == null) {
			writer = (String)request.getAttribute("writer");
		} else {
			writer = request.getParameter("writer");
		}
		
		if (request.getParameter("content") == null) {
			content = (String)request.getAttribute("content");
		} else {
			content = request.getParameter("content");
		}
		
		if (request.getParameter("post_date") == null) {
			post_date = (String)request.getAttribute("post_date");
		} else {
			post_date = request.getParameter("post_date");
		}
		
		if (request.getParameter("views") == null) {
			views = (int)request.getAttribute("views");
		} else {
			views = Integer.parseInt(request.getParameter("views"));
		}
		
		commentDAO dao = new commentDAO();

		ArrayList<commentVO> list = dao.commentSelect(post_id);
	%>
	
	<table>
		<tr>
			<th><%=title%><th>
		</tr>
		<tr>
			<td>작성자<%=writer %></td>
			<td>작성일자<%=post_date %></td>
			<td>조회수 <%=views %></td>
		</tr>
		<tr>
			<td class="content" colspan="4"><%=content %></td>
		</tr>
	</table>
	
	<form action="commentWriteCon?post_id=<%=post_id%>&title=<%=title%>&writer=<%=writer%>&content=<%=content%>&post_date=<%=post_date%>&views=<%=views %>" method="post">
		<table class="comment_write">
			<tr>
				<td class="comment_flex">
					<textarea name="comment_content"></textarea>
					<% if (vo == null) { %>
						<button type="button" onclick="alert('로그인 하세요')">댓글 작성</button>
					<% } else { %>						
						<input type="submit" value="댓글 작성">
					<% } %>	
				</td>
			</tr>
		</table>
	</form>	
	
	<% for(int i=0; i<list.size(); i++) { %>
		<table class="comment_area">
			<tr>
				<td>
					<%=list.get(i).getComment_writer()%>					
					<%=list.get(i).getComment_date()%>
				</td>
			</tr>
			<tr>
				<form action="commentUpdateCon?comment_id=<%=comment_id %>&post_id=<%=post_id%>&title=<%=title%>&writer=<%=writer%>&content=<%=content%>&post_date=<%=post_date%>&views=<%=views %>" method="post">
					<!-- View.jsp에서 보낸 댓글의 아이디와 리스트에 담긴 댓글의 아이디가 같은 단 하나의 댓글만 수정가능 -->
					<% if (list.get(i).getComment_id().equals(comment_id)) { %>					
						<td>
							<input type="text" name="update_content" value="<%=list.get(i).getContent() %>">
							<input type="submit" value="수정">
						</td>
					<% } else {%>
						<td><%=list.get(i).getContent() %></td>
					<% } %>
				</form>
			</tr>
		</table>
	<% } %>

</body>
</html>