<%@page import="com.VO.memberVO"%>
<%@page import="com.VO.etc_reservationVO"%>
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
		ArrayList<etc_reservationVO> list = (ArrayList)session.getAttribute("etc_reservation_list");
		memberVO vo = (memberVO) session.getAttribute("vo");
		
	%>
			
	<% if(list.isEmpty()) { %>
		<script>
			alert('예약 한 사람이 없습니다.')
			history.back()
		</script>
	<% } else { %>
		<h1><%=list.get(0).getRsv_location()%></h1>
		<h3><%=list.get(0).getRsv_date()%></h3>
		<table>
			<tr>
				<th>반</th>
				<th>이름</th>
				<th>좌석</th>
				<th>퇴실시간</th>
			</tr>
			
			<% for (int i=0; i<list.size(); i++) { %>
				<tr>
					
						
						<td><%=list.get(i).getRsv_class() %></td>
						
						<td><%=list.get(i).getRsv_name() %></td>
						
						<td><%=list.get(i).getRsv_seat() %>번</td>
						
						<td><%=list.get(i).getCheckout() %></td>
						
						<% if (vo.getId().equals(list.get(i).getRsv_id())) {%>
						<td>
							<button type"button" onclick="history.back()">수정</button>
							<button type"button" onclick="location.href='etc_ReservationDeleteCon'">삭제</button>
						</td>
						<% } %>
						
					
				</tr>
			<% } %>
			
		</table>
	<%} %>
</body>
</html>