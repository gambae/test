<%@page import="com.DAO.etc_reservationDAO"%>
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
	<%	
		etc_reservationDAO dao = new etc_reservationDAO();
		memberVO vo = (memberVO) session.getAttribute("vo");
		
		// 예약 날짜와 예약 위치를 받아온다.
		String date = request.getParameter("reservation_date");
		String location = request.getParameter("location");
			
	%>
	
	<h1><%=location %></h1>
	
	<b><%=date %></b><br><br>
	
	<!-- 예약 날짜,예약 위치,퇴실 시간,예약 좌석 번호를 전달 -->
	<form action="etc_ReservationCon?location=<%=location %>&date=<%=date %>" method="post">
		
		<select name="checkout">
			<option value="19:00">19:00</option>
			<option value="20:00">20:00</option>
			<option value="21:00">21:00</option>
			<option value="22:00">22:00</option>
			<option value="23:00">23:00</option>
		</select>
		
		<!-- 로그인 한 아이디와 현재 선택한 날짜에 예약된 정보가 있는지 중복체크 메소드를 통해 확인 -->
		<% if (dao.repetitionCheck(vo.getId(),date) == 0) { %>
			<input type="submit" value="예약하기" onclick="alert('예약 성공')">
		<% } else { %>
			<input type="button" value="예약하기" onclick="alert('이미 예약하셨습니다.')">
		<% } %>
		
		<!-- 쿼리스트링으로 보내는 위치와 날짜에 해당하는 예약 명단 확인 -->
		<a href="etc_ReservationSelectCon?location=<%=location %>&date=<%=date %>">예약 명단</a>
		
		<br><br>
		
		<!-- 해당 위치의 좌석 개수만큼 범위 설정 -->	
		<% 
			int cnt = 0;
			
			if (location.equals("AI_lab(2F)")) cnt=5;
			else if (location.equals("Retiringroom(2F)")) cnt=7;
			else cnt=10;
		%>
		
		<% for (int i=1; i<=cnt; i++) { %>
			<!-- 1번부터 cnt번까지의 좌석들이 예약된 좌석인지 확인하는 메소드를 통해 예약되있으면 체크박스 선택 못하게 설정 -->
			<% if (dao.reservedSeat(date,location,i) == 0) { %>
				<input type="checkbox" name="seat" value="<%=i %>" onclick="doOpenCheck(this)"> <%=i %>번
			<% } else { %>
				<input type="checkbox" name="seat" value="<%=i %>" onclick="doOpenCheck(this)" checked="checked" disabled="disabled"> <%=i %>번
			<% } %>	
		<% } %>
		
	</form>
	
	<script>
	
		<!--
			체크박스를 클릭할때마다 실행되는 함수
			한 사람이 여러 좌석을 예약하면 안되기때문에 하나의 체크박스만 선택할 수 있게하는 함수
			for문을 통해 좌석 1번부터 cnt번까지 돌면서 체크한 좌석번호를 제외하고 체크된 좌석이있으면 체크를 해제
		-->
		
		function doOpenCheck(chk) {
			var obj = document.getElementsByName("seat");
			for(var i=0; i<obj.length; i++){
				if(obj[i] != chk){
					obj[i].checked = false;
				}
			}
		}
	</script>
	
</body>
</html>