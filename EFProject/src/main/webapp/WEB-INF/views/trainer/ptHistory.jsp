<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <title>Insert title here</title>
</head>
<script>
$(document).ready(function() {
	$(document).on('click', '.reservationBtn', function(){
	    var reservationBtn = $(this);
	    
	    var row = reservationBtn.parent().parent();
	    var tr = row.children();
	    
	    var mef_p_no   = tr.eq(0).text();
	    var tr = '';
	    
	    // alert(mef_p_no);
	    
	    $.ajax({
	       	type:      "POST",                              
	        url:        "${contextPath}/trainer/ptReservation.do",       
	        dataType:   "json",                           
	        data:      {ef_p_no: mef_p_no},
	        async:      false,
	        success:   function(data) {
	           	tr = '<table class="revMemList"><tr><th style="display:none;">ptno</th>'
	               + '<th>아이디</th>'
	               + '<th>이름</th>'
	               + '<th>연락처</th></tr>';
	            if(data.length>=1) {
	            	$(data).each(function(r, item) {
			              tr += '<tr><td style="display:none;">'+ data[r].ef_p_no + '</td>'
			              	  + '<td>' + data[r].ef_id + '</td>'
			              	  + '<td>' + data[r].ef_name +'</td>'
			              	  + '<td>' + data[r].ef_p_number + '</td></tr>'; 
	            	});
	            }
	            
         		 else {
	            	tr += '<tr><td colspan=3>예약된 회원이 없습니다.</td></tr>';	
	            }
	            $("#reservationList").html(tr);
	        }
	    });
	});
});
	
function moDivOn3() {
	$('.revMemListField').hide(50);
	$('.revMemListField').show(200);
}
function moDivOff3() {
	$('.revMemListField').hide(200);
}
</script>
<body>	
<div class="ownerField">
	<div class="subMenu">
		<span class="ownerTitle">나의 업체명 : ${ef_c_name}</span>
		<span class="ownerMenuTile">관리 메뉴 : </span>
		<a href="${contextPath}/trainer/trainerMenu.do" class="ownerMenuLinkText">강의관리</a> 
		<a href="#" class="ownerMenuLinkText">강의이력</a> 
	</div>	
	<table class="ownerCalenderLayout">
		<tr>
			<td>
				<span class="calenderTitle">나의 강의 이력</span><p/>	
				<table class="ownerCalList">
					<tr>
						<th style="display:none;"></th>
						<th>과정명</th>
						<th>PT날짜</th>
						<th>PT시간</th>
						<th>강의정원</th>
						<th>수강인원</th>
						<th>수강율(%)</th>	
						<th>예약회원</th>				
					</tr>
					<c:forEach var="trainer_pt" items="${ptHistory}">
						<tr>
							<td style="display:none;">${trainer_pt.ef_p_no}</td>
				           	<td>${trainer_pt.ef_p_type}</td>
				            <td>${trainer_pt.ef_p_date}</td>
				            <td>${trainer_pt.ef_p_time}</td>
				            <td>${trainer_pt.ef_p_personal}</td>
				            <td>${trainer_pt.ef_r_personal}</td>
				            <td><fmt:formatNumber value="${(trainer_pt.ef_r_personal+0.0) / (trainer_pt.ef_p_personal+0.0) * 100}" pattern="#.##"/> %</td>
				            <td><span class="inTableLinkText reservationBtn" onclick="moDivOn3()">예약현황</span></td>
				        </tr>
        			</c:forEach>														
				</table >
			</td>
		</tr>
	</table>
</div>

<div class="revMemListField" style="display:none;">
	<p/>
	<span class="calenderTitle">예약 회원 리스트</span>
	<hr>
	<div id="reservationList" class="revMemList"></div>
	<hr>
	<p/>
	<span onclick="moDivOff3()" class="ownerMenuLinkText2">닫기</span>
</div>
</body>
</html>