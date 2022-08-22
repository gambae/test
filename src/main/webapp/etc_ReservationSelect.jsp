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
		// 세션으로 저장한 리스트 가져오기
		ArrayList<etc_reservationVO> list = (ArrayList)session.getAttribute("etc_reservation_list");
		memberVO vo = (memberVO) session.getAttribute("vo");
	%>
	
	<!-- 리스트 사이즈가 0이면 예약 한 사람이 없기 때문에 알림창 띄운 후 history.back()을 통해 뒤로가기 -->		
	<% if(list.size() == 0) { %>
		<script>
			alert('예약 한 사람이 없습니다.')
			history.back()
		</script>
	<% } else { %>
		<%	
			// 리스트의 etc_reservationVO 객체는 모두 같은 날짜와 위치를 가지고 있다
			// ReservationSelect 메소드에 매개변수로 날짜와 위치를 지정해 뒀기때문에
			String location = list.get(0).getRsv_location();
			String date = list.get(0).getRsv_date();
		%>
		
		<h1><%=location%></h1>
		<h3><%=date%></h3>
		
		<table>
			<tr>
				<th>반</th>
				<th>이름</th>
				<th>좌석</th>
				<th>퇴실시간</th>
			</tr>
			
			<!-- 리스트를 돌면서 etc_reservationVO의 getter 메소드를 통해 예약 정보 출력 -->
			<% for (int i=0; i<list.size(); i++) { %>
				<tr>									
					<td><%=list.get(i).getRsv_class() %></td>
						
					<td><%=list.get(i).getRsv_name() %></td>
						
					<td><%=list.get(i).getRsv_seat() %>번</td>
						
					<td><%=list.get(i).getCheckout() %></td>
					
					<!-- 현재 로그인 한 아이디와 예약 아이디가 같으면 수정,삭제 권한 부여 -->	
					<% if (vo.getId().equals(list.get(i).getRsv_id())) {%>
					<td>
						<!-- 수정 할 때는 현재 로그인 한 사람의 예약 위치,예약 날짜,예약 좌석을 보내준다. -->
						<button type"button" onclick="location.href='etc_ReservationUpdate.jsp?location=<%=location%>&date=<%=date%>&seat=<%=list.get(i).getRsv_seat()%>'">수정</button>
						<!-- 삭제 할 때는 현재 로그인 한 사람의 예약 날짜를 보내준다. -->
						<button type"button" onclick="location.href='etc_ReservationDeleteCon?date=<%=date%>'">삭제</button>
					</td>
					<% } %>										
				</tr>
			<% } %>
		</table>
	<%} %>
</body>
</html>