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
		String date = request.getParameter("reservation_date");
		etc_reservationDAO dao = new etc_reservationDAO();
		String location = "AI_lab(2F)";
		memberVO vo = (memberVO) session.getAttribute("vo");
	%>
	
	<h1>AI_Lab(2F)</h1>
	
	<b><%= date %></b><br><br>
	
	<form action="etc_ReservationCon" method="post">
	
		
		<select name="checkout">
			<option value="19:00">19:00</option>
			<option value="20:00">20:00</option>
			<option value="21:00">21:00</option>
			<option value="22:00">22:00</option>
			<option value="23:00">23:00</option>
		</select>
		
		<% if (dao.repetitionCheck(vo.getId(),date) == 0) { %>
			<input type="submit" value="예약하기" formaction="etc_ReservationCon?location=AI_lab(2F)&date=<%=date %>">
		<% } else { %>
			<input type="button" value="예약하기" onclick="alert('이미 예약하셨습니다.')">
		<% } %>
		
		<a href="etc_ReservationSelectCon?location=AI_lab(2F)&date=<%=date %>">예약 명단</a>
		
		<br><br>
		
		<% for (int i=1; i<6; i++) { %>
			<% if (dao.reservedSeat(date,location,i) == 0) { %>
				<input type="checkbox" name="seat" value="<%=i %>" onclick="doOpenCheck(this)"> <%=i %>번
			<% } else { %>
				<input type="checkbox" name="seat" value="<%=i %>" onclick="doOpenCheck(this)" checked="checked" disabled="disabled"> <%=i %>번
			<% } %>	
		<% } %>
		
	</form>
	
	
	<script>
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