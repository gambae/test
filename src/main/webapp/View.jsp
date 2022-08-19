<%@page import="com.DAO.commentDAO"%>
<%@page import="com.VO.commentVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.VO.memberVO"%>
<%@page import="com.VO.freeboardVO"%>
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
		
		commentDAO dao = new commentDAO();
		
		ArrayList<commentVO> list = dao.commentSelect(post_id);

	%>
	
	<table>
		<tr>
			<th><%=title%><th>
		</tr>
		<tr>
			<td>작성자<%= writer %></td>
			<td>작성일자<%= post_date %></td>
			<td>조회수 0</td>
			<td>추천수 0</td>
		</tr>
		<tr>
			<td class="content" colspan="4"><%= content %></td>
		</tr>
	</table>
	
	<form action="commentWriteCon?post_id=<%=post_id%>&title=<%=title%>&writer=<%=writer%>&content=<%=content%>&post_date=<%=post_date%>" method="post">
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
					<% if (vo.getId().equals(list.get(i).getComment_writer())) { %>
						<button onclick="#">수정</button>
						<button onclick="#">삭제</button>
					<% } %>
				</td>
			</tr>
			<tr>
				<td><%=list.get(i).getContent() %></td>
			</tr>
		</table>
	<% } %>
	
		
	
	<% if (vo != null && vo.getId().equals(writer)) { %>
		<button onclick="location.href='PostUpdate.jsp?post_id=<%=post_id%>&title=<%=title%>&writer=<%=writer%>&content=<%=content%>&post_date=<%=post_date%>'">글 수정</button>
		<button onclick="confirm('게시글을 삭제하시겠습니까?');location.href='postDeleteCon?post_id=<%=post_id%>';">글 삭제</button>
	<% } %>
	
</body>
</html>