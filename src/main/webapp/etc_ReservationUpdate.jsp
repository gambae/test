<%@page import="com.VO.memberVO"%>
<%@page import="com.DAO.etc_reservationDAO"%>
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
	
		String date = request.getParameter("date");
		String location = request.getParameter("location");
		String seat = request.getParameter("seat");
	%>
	
	<h1><%=location %></h1>
	
	<b><%=date %></b><br><br>
	
	<!-- 수정 할 퇴실 시간과 좌석 번호를 전달 -->
	<form action="etc_ReservationUpdateCon?location=<%=location %>&date=<%=date %>&seat=<%=seat %>" method="post">
		<!-- 수정 할 퇴실 시간 선택 select 박스 -->
		<select name="checkout">
			<option value="19:00">19:00</option>
			<option value="20:00">20:00</option>
			<option value="21:00">21:00</option>
			<option value="22:00">22:00</option>
			<option value="23:00">23:00</option>
		</select>
		
		<input type="submit" value="예약수정">	
		
		<br><br>
	
		<% 
			int cnt = 0;
			
			if (location.equals("AI_lab(2F)")) cnt=5;
			else if (location.equals("Retiringroom(2F)")) cnt=7;
			else cnt=10;
		%>
		
		<% for (int i=1; i<=cnt; i++) { %>
			<!-- 좌석을 바꾸지 않고 시간만 바꾸면 update_seat에는 null값이 들어간다. -->
			<% if (dao.reservedSeat(date,location,i) == 0) { %>
				<input type="checkbox" name="update_seat" value="<%=i %>" onclick="doOpenCheck(this)"> <%=i %>번
			<% } else { %>
				<input type="checkbox" name="update_seat" value="<%=i %>" onclick="doOpenCheck(this)" checked="checked" disabled="disabled"> <%=i %>번
			<% } %>	
		<% } %>
		
	</form>
	
	<script>
		function doOpenCheck(chk) {
			var obj = document.getElementsByName("update_seat");
			for(var i=0; i<obj.length; i++){
				if(obj[i] != chk){
					obj[i].checked = false;
				}
			}
		}
	</script>
</body>
</html>