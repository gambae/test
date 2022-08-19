<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table {border:1px solid black; width:300px; text-align:center;}
	textarea {width:300px; height:300px;}
</style>
</head>
<body>
	<h1>인공지능 사관학교 게시판</h1>
	
	<form action="freeBoardWriteCon" method="post">
		<table>
			<tr>
				<td><input type="text" placeholder="제목" name="title" style="width:300px"></td>
			</tr>
			<tr>
				<td><textarea placeholder="내용" name="content"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="작성">
	</form>
</body>
</html>