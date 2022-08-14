<%@page import="com.VO.memberVO"%>
<%@page import="com.VO.reservationVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {width: 400px; border: 2px solid black; text-align:center;}
</style>
</head>
<body>

	<% 
		ArrayList<reservationVO> list = (ArrayList)session.getAttribute("reservation_list");
		memberVO vo = (memberVO)session.getAttribute("vo");
	%>
		<!-- 수정버튼을 누른 사람의 checkout 시간만 바꿔주면되기 때문에 ReservationSelect와 동일한 구조 -->
		<h1><%=list.get(0).getRsv_cls() %></h1>
		<table>
			<tr>
				<th>이름</th>
				<th>퇴실시간</th>
				<th>예약날짜</th>
			</tr>
			<% for (int i=0; i<list.size(); i++) { %>
				<tr>
					<form action="ReservationUpdateCon" method="post">
						<td><%=list.get(i).getRsv_name() %></td>
						
						<!-- 현재 로그인한 사람의 아이디와 예약된 사람의 아이디가 같을때만 수정 가능 -->
						<% if (vo.getId().equals(list.get(i).getRsv_id())) {%>
						<td>
							<select name="update_checkout">
								<option value="19:00">19:00</option>
								<option value="20:00">20:00</option>
								<option value="21:00">21:00</option>
								<option value="22:00">22:00</option>
								<option value="23:00">23:00</option>
							</select>
						</td>
						<% } else {%>
						<td><%=list.get(i).getCheckout() %></td>
						<% } %>
						
						<td><%=list.get(i).getRsv_date() %></td>
						
						<% if (vo.getId().equals(list.get(i).getRsv_id())) {%>
						<td>
							<input type="submit" value="수정">
						</td>
						<% } %>
						
					</form>
				</tr>
			<% } %>
		</table>
	
</body>
</html>