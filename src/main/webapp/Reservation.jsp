<%@page import="com.DAO.etc_reservationDAO"%>
<%@page import="com.DAO.reservationDAO"%>
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
	// 현재 로그인한 사람의 정보를 가진 vo 세션
	memberVO vo = (memberVO) session.getAttribute("vo");
	// 예약기능만 수행하는 reservationDAO 객체 생성
	reservationDAO dao = new reservationDAO();
	
	etc_reservationDAO etc_dao = new etc_reservationDAO();
	%>
	
	<h1>강의장 연장 신청</h1> 
	<!-- 강의장 버튼을 누르면 강의장예약에관한 정보를 보여준다 -->
	<button id="button1" onclick="showLecture()">강의장</button>
	<!-- AI Lab 및 휴게공간 버튼을 누르면 AI Lab 및 휴게공간 예약에관한 정보를 보여준다 -->
	<button id="button2" onclick="showEtc()">AI Lab 및 휴게공간</button> <a href="Main.jsp">메인페이지</a>
	<br>
	<br>
	
	<!-- 강의장 예약 정보를 보여줄 내용, id는 lecture_reservation  -->
	<div id="lecture_reservation" style="white-space: nowrap;">
		
		<!-- formaction을 쓰기 위해서는 form태그안에 감싸져야한다. -->
		<form action="#" method="post">
			
			<!-- Spring_A반의 예약을 담당하는 div태그 -->
			<div style="border: 1px solid black; width: 220px; height: 130px;">
				<h3>Spring_A</h3>
				
				<!-- 
					현재 lecture_reservation테이블에 예약된 Spring_A반 학생의 수를 
					cntReservation() 메소드를 통해 리턴받는다. 
				-->
				현재 신청 인원 : <%= dao.reservationCount("Spring_A") %>명 <br>
				
				<!-- 사용자가 퇴실 시간을 선택하는 select태그 -->
				<select name="time">
					<option value="19:00">19:00</option>
					<option value="20:00">20:00</option>
					<option value="21:00">21:00</option>
					<option value="22:00">22:00</option>
					<option value="23:00">23:00</option>
				</select>
				
				<!-- 
					로그인이되어있고 예약한 정보가 없으면 ReservationCon에 Sprint_A 값을 전달
					★★formaction은 해당 submit 버튼을 누를때에만 작동한다.
				-->
				<% if (vo != null && dao.repetitionCheck(vo.getId()) == 0) { %>
				<input type="submit" value="신청" formaction="ReservationCon?cls=Spring_A" onclick="alert('신청 완료')">
				
				<!-- 로그인이 안되어있으면 ReservationCon으로 이동하지 않는다 -->
				<% } else if (vo == null) { %>
				<input type="submit" value="신청" onclick="alert('로그인하시오')">
				
				<!-- 
					로그인은 되어있지만 중복체크를 통해 이미 예약된 정보가 있다면 신청이 안된다
					한 사람이 여러 강의장에 중복으로 예약할 수 없기 때문 
				-->
				<% } else { %>
				<input type="submit" value="신청" onclick="alert('이미 신청한 아이디입니다.')">
				<% } %>
				
				<!-- 어떤 반의 예약 현황을 조회할것인지 ReservationSelectCon에 반 이름을 넘겨준다. -->
				<a href="ReservationSelectCon?cls=Spring_A">예약 현황</a>
			</div>
			
			
			<!-- Spring_B반의 예약을 담당하는 div태그 -->
			<div style="border: 1px solid black; width: 220px; height: 130px;">
				<h3>Spring_B</h3>
				현재 신청 인원 : <%= dao.reservationCount("Spring_B") %>명 <br> <select name="time">
					<option value="19">19:00</option>
					<option value="20">20:00</option>
					<option value="21">21:00</option>
					<option value="22">22:00</option>
					<option value="23">23:00</option>
				</select>
				<% if (vo != null && dao.repetitionCheck(vo.getId()) == 0) { %>
				<input type="submit" value="신청" formaction="ReservationCon?cls=Spring_B" onclick="alert('신청 완료')">
				<% } else if (vo == null) { %>
				<input type="submit" value="신청" onclick="alert('로그인하시오')">
				<% } else { %>
				<input type="submit" value="신청" onclick="alert('이미 신청한 아이디입니다.')">
				<% } %>
				<a href="ReservationSelectCon?cls=Spring_B">예약 현황</a>
			</div>
			
		</form>
	</div>
	
	
	<!-- AI Lab 및 휴게공간 버튼을 누를때만 내용이 보여져야하므로 클릭하지않으면 내용을 숨긴다  -->
	<div id="etc_reservation" style="visibility: hidden; position:relative; top:-260px;">
	
		<form action="#" method="post">
			<!-- 예약 할 날짜를 입력받는 input 태그 -->
			<input type="date" id="now_date" name="reservation_date"><br><br>
			
			<div style="border: 1px solid black; width: 220px; height: 130px;">
				<h3>AI_lab(2F)</h3>
				<!-- 로그인 한 사람만 예약한 날짜를 etc_Reservation.jsp로 전달 -->
				<% if (vo != null) { %>
					<input type="submit" value="예약하기" formaction="etc_Reservation.jsp?location=AI_lab(2F)">
				<% } else { %>
					<input type="submit" value="예약하기" onclick="alert('로그인하시오')">
				<% } %>
			</div>		

			<div style="border: 1px solid black; width: 220px; height: 130px;">
				<h3>휴게실(2F)</h3>
				<% if (vo != null) { %>
					<input type="submit" value="예약하기" formaction="etc_Reservation.jsp?location=Retiringroom(2F)">
				<% } else { %>
					<input type="submit" value="예약하기" onclick="alert('로그인하시오')">
				<% } %>
			</div>		
		</form>
	</div>
		
	
	
	<script>
		<!-- 강의장 버튼을 누르면 강의장 예약이 보여지고 그외 예약은 숨겨진다.-->
		function showLecture() {
			document.getElementById("lecture_reservation").style.visibility = "visible";
			document.getElementById("etc_reservation").style.visibility = "hidden";
		}
		<!-- AI Lab 및 휴게공간 버튼을 누르면 AI Lab 및 휴게공간 예약이 보여지고 강의장 예약은 숨겨진다.-->
		function showEtc() {
			document.getElementById("etc_reservation").style.visibility = "visible";
			document.getElementById("lecture_reservation").style.visibility = "hidden";
		}
		<!-- 현재 날짜 -->
		var today = new Date();
		<!-- input태그의 날짜를 현재날짜를 기본값으로 사용하도록 설정-->
		document.getElementById('now_date').valueAsDate= today;
	</script>
	
</body>
</html>