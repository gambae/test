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
		memberVO vo = (memberVO) session.getAttribute("vo");
	%>
	
	<!-- ReservationSelectCon에서 호출한 ReservationSelect()메소드의 리턴값이 null이 아닐때 즉,해당 반의 예약된 사람이 있을때 -->
	<% if(list != null) { %>
	
		<!-- list가 null이 아니라는것은 reservationVO 객체가 최소 한개는 존재한다는 뜻 -->
		<!-- ★★ReservationSelectCon에서 넘겨준 list는 모두 같은 반의 예약 정보이다.-->
		<!-- ReservationSelect(cls) 메소드에 반이름을 넘겨줬기 때문에 -->
		<h1><%=list.get(0).getRsv_cls() %></h1>
		
		<table>
			<tr>
				<th>이름</th>
				<th>퇴실시간</th>
				<th>예약날짜</th>
			</tr>
			
			<!-- 한 번 반복될때마다 한 사람의 예약 정보를 출력 -->
			<% for (int i=0; i<list.size(); i++) { %>
				<!-- 행을 나타내는 tr태그 1개의 행마다 한 명의 정보를 출력 -->
				<tr>
					<form action="#">
						<!-- list에 담긴 reservationVO 객체의 rsv_name 필드를 호출 -->
						<td><%=list.get(i).getRsv_name() %></td>
						
						
						<td><%=list.get(i).getCheckout() %></td>
						
						
						<td><%=list.get(i).getRsv_date() %></td>
						
						<!-- 현재 로그인한 사람의 id와 예약된 id가 같을때만 수정,삭제 버튼이 보인다 -->
						<!-- 내가 다른사람의 예약 정보를 삭제,수정하면 안되기때문에 -->
						<% if (vo.getId().equals(list.get(i).getRsv_id())) {%>
						<td>
							<button type"submit" formaction="ReservationUpdate.jsp">수정</button>
							<button type"submit" formaction="ReservationDeleteCon">삭제</button>
						</td>
						<% } %>
						
					</form>
				</tr>
			<% } %>
			
		</table>
		
	<!-- list의 값이 null일때, 즉 해당 반에 예약을한 정보가 데이터베이스에 존재하지 않을 경우 -->
	<%} else { %>
		<script>alert('현재 예약한 사람이 없습니다.')</script>
	<%} %>
	
	
</body>
</html>