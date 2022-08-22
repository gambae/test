<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table {border:1px solid black; height:300px; width:400px;}
	.content {width:400px; height:300px; font-size:200%;}
	td {border:1px solid black;}
</style>
</head>
<body>
	<%	
		String post_id = request.getParameter("post_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String post_date = request.getParameter("post_date");
	%>
	
	<!-- postUpdateCon에 글 아이디,수정 제목,수정 내용을 전달 -->
	<form action="postUpdateCon?&post_id=<%=post_id%>" method="post">
		<table>
			<tr>
				<th><input type="text" name="update_title" value=<%=title%>><th>
			</tr>
			<tr>
				<td>작성자<%=writer%></td>
				<td>작성일자<%=post_date%></td>
				<td>조회수 0</td>
				<td>추천수 0</td>
			</tr>
			<tr>
				<td colspan="4"><textarea class="content" name="update_content"><%=content%></textarea></td>
			</tr>
		</table>
		
		<input type="submit" value="수정하기">
	</form>	
		
</body>
</html>