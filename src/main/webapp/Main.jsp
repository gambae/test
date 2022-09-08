<%@page import="com.VO.memberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		로그인에 성공했을때만 생성되는 vo라는 이름을 가진 세션 선언부
		이 vo안에는 로그인한 사람의 반,아이디,이메일,이름이 담겨져있다.
	-->
	<% memberVO vo = (memberVO)session.getAttribute("vo"); %>
	
	<h1>인공지능 사관</h1>
	
	<!-- vo라는 세션이 null이 아니라는것은 누군가가 로그인에 성공한것-->
	<!-- memberVO 객체에 필드값을 가져올때는 getter메소드를 사용  -->
	
	<% if (vo != null) { %>
	<%= vo.getName()%>님 로그인중<br><br>
	<a href="LogOutCon">로그아웃</a>
	<%} else { %>
	<a href="Login.html">로그인</a>
	<%} %>
	
	<a href="Join.html">회원가입</a>
	<a href="Reservation.jsp">강의장 연장 신청</a>
	<a href="freeboardSelectCon">자유게시판</a> <br>
</body>
</html>
