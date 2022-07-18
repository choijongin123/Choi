<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약을 원하시는 날짜를 선택해 주세요</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
// 변수 선언부
var today = new Date();			// 오늘 날자 데이터를 변수 today로 저장.
var date = new Date();			// 비교를 하기 위하여 오늘 날자 데이터를 변수 date로 저장.
var curTime = new Date()  // 현재 정확한 날짜를 불러오되, 비교를 하기 위해 날짜 사이에 '-' 표시로 균일한 조건을 갖추도록 변수 저장.
if((today.getMonth() + 1)<10 && today.getDate() < 10) {
	curTime = today.getFullYear() + "-0"+ (today.getMonth() + 1) + "-0"+ today.getDate();
		
} else if((today.getMonth() + 1)<10 && today.getDate() >= 10){
	curTime = today.getFullYear() + "-0"+ (today.getMonth() + 1) + "-"+ today.getDate();
				
} else if((today.getMonth() + 1)>=10 && today.getDate() < 10) {
	curTime = today.getFullYear() + "-"+ (today.getMonth() + 1) + "-0"+ today.getDate();
				
} else {
	curTime = today.getFullYear() + "-"+ (today.getMonth() + 1) + "-"+ today.getDate();
}

var arr = new Array();
<c:forEach items="${reservationPtlist}" var="reservationPtlist">
	arr.push({
		ef_p_no: "${reservationPtlist.ef_p_no}"
	});
</c:forEach>

var startTerm = "<c:out value='${memregi.ef_start_term}'/>";
var endTerm   = "<c:out value='${memregi.ef_end_term}'/>";

// function buildCalendar() 달력을 생성하는 기능 선언
function buildCalendar() {

	// 기본 변수 초기화
	var row = null
	var cnt = 0;		// 칸의 고유 주소값을 지정하기 위한 변수.

	// 달력을 만들어서 출력할 table 및 tableTitle을 참조하기 위한 변수 설정.
	var calendarTable = document.getElementById("calendar");				// 달력을 생성할 위치참조
	var calendarTableTitle = document.getElementById("calendarTitle");	// 생성한 달력의 타이틀 주소 위치참조
	calendarTableTitle.innerHTML = today.getFullYear()+"년"+(today.getMonth()+1)+"월";	// 생성한 달력의 연,월 표기를 위한 날짜 로딩 및 분배.

	// 달력의 첫 날과 마지막 날에 대한 변수 설정.
	var firstDate = new Date(today.getFullYear(), today.getMonth(), 1);	// 1일 부터.
	var lastDate = new Date(today.getFullYear(), today.getMonth()+1, 0);	// 그 다음 달의 0일 까지.

	// 작성할 테이블을 초기화.
	while(calendarTable.rows.length > 2) {						
		calendarTable.deleteRow(calendarTable.rows.length -1);		
	}

 	// 달의 첫 날까지 빈 셀을 생성한다.
	row = calendarTable.insertRow();
	for(i = 0; i < firstDate.getDay(); i++) {
		cell = row.insertCell();
		cell.style.backgroundColor="#eeeeee";
		cell.width = 100; // 셀 생성시 높이 고정값.
		cnt += 1;
	}
 
 	// 달의 첫 번째 칸에 올 숫자를 설정한다.
 	for(i = 1; i <= lastDate.getDate(); i++) {		// 1일부터 그 다음 달의 0일 까지(즉, 현재 달의 마지막 날 까지) 변수값을 증가하며 반복수행.
 		cell = row.insertCell();
 		cnt += 1;									// 각 칸의 고유 주소값을 1부터 세기 위하여 1 증가.
 		todayDate=today.getFullYear()+"-"+(1+today.getMonth())+"-"+i;		// 오늘 날짜를 저장하기 위한 변수.
 	
 		// 각 셀의 고유 주소값에 대한 정보를 하기의 구분기호로 구분하여 표기한다.
 		if (i<10 && 1+today.getMonth() <10) {		// 일이 10보다 작고, 월이 10보다 작은 경우(둘 다 10보다 작은 경우) 구분기호 뒤에 0을 더한다.
 			cellDate=today.getFullYear()+"-0"+(1+today.getMonth())+"-0"+i;
 		
 		} else if (1+today.getMonth() <10) {			// 월이 10보다 작은 경우에도 구분기호 뒤에 0을 더한다.
			cellDate=today.getFullYear()+"-0"+(1+today.getMonth())+"-"+i;
		
 		} else if (i <10){							// 일이 10보다 작은 경우에도 구분기호 뒤에 0을 더한다.
			cellDate=today.getFullYear()+"-"+(1+today.getMonth())+"-0"+i;
		
 		} else {										// 그 외에는 구분기호 -만 표기한다.
			cellDate=today.getFullYear()+"-"+(1+today.getMonth())+"-"+i;
		}
 	
		// DB와의 균일화를 위해 상동 원리로 구분기호 뒤 0을 구분한다.
 		if(i < 10 && (1+today.getMonth() < 10)  ) {	
			cell.setAttribute('id',today.getFullYear()+"-0"+(1+today.getMonth())+"-0"+i); // 셀의 주소 정보.
 		
 		} else if((1+today.getMonth() < 10)) {
   			cell.setAttribute('id',today.getFullYear()+"-0"+(1+today.getMonth())+"-"+i); // 셀의 주소 정보.
 		
 		} else if(i < 10) {
			cell.setAttribute('id',today.getFullYear()+"-"+(1+today.getMonth())+"-0"+i); // 셀의 주소 정보.
		
 		} else {
 			cell.setAttribute('id',today.getFullYear()+"-"+(1+today.getMonth())+"-"+i); // 셀의 주소 정보.
 		}
   
 		cell.innerHTML = i;		// 달력에 표기될 일자의 카운트.
 		cell.align = "center"; // 각 셀 정렬 방법.
 	
		// 카운트 숫자(각 셀의 고유 카운트)가 7로 나누었을 때 1이 남는 경우 일요일로 구분한다. 즉, 각 1번칸, 8번칸, 15번칸 ---- 순.
   		if (cnt % 7 == 1) {
   			cell.innerHTML = "<font color=red>" + i + "</font>";
   		}

		// 카운트 숫자가 7로 나누어 딱 떨어지는 경우 토요일로 처리한다. 즉, 각 7번, 14번, 21번 칸 ----순.
   		if (cnt % 7 == 0) {
   			cell.innerHTML = "<font color=blue>" + i + "</font>";
   			row = calendar.insertRow();
   		}

		// 각 셀의 고유 날짜 정보가 현재의 날짜와 일치하는 경우 오늘로 구분한다.
   		if (cellDate==curTime) {
   			cell.innerHTML = "<font color=orange class=\"textShadow\">"+i+"</font>";
  		 }
   
		// ajax 수행을 포문의 i(인덱스) 값 만큼 수행한다.
 		(function(i) {
 			var cntt = 0;
 		
 			$.ajax({
 				type:		"POST",						// ajax 요청을 보낼 방식.
 				url:		"${contextPath}/member/findMyTypePtList.do",		// ajax 요청을 보낼 주소.
 				dataType:	"json",						// ajax 요청을 보낼 타입.
 				async:		false,
 				success:	function(data) { 			// success(데이터를 성공적으로 가져왔을 경우)  (data) 를 기준하여 기능을 수행한다.
 				
 					$(data).each(function(c, item) {		// 데이터.each 함수로 function(인덱스넘버, 가져올 오브젝트)를 for문 처럼 인덱스넘버 만큼 수행한다. (인덱스 넘버는 데이터를 가져온 갯수) 편의를 위해 c로 분리.
 				
 						if(startTerm.valueOf() <= cellDate.valueOf() && endTerm.valueOf() >= cellDate.valueOf()) {  // 회원의 수강시작일 부터 종료일 까지의 데이터만 보여줌
 							if(data[c].ef_p_date==cellDate) {	// if문으로 data[c]를 입력하여 가져온 데이터 수 만큼 반복수행되는 each문 안에서 각 데이터 오브젝트의.'ef_p_time' 에 대항하는 값을 가져와 비교한다.
 								if(cntt<3) {
  									if(data[c].ef_p_time=='오전반') {
	  									cell.innerHTML +="<font size=1 color=green><br/>●</font><span class=\"calenderText\"> 오전 : "+data[c].ef_r_personal+"/"+data[c].ef_p_personal+"</span>";
	  									cell.style.backgroundColor="#e6e6e6";
  									}
  									if(data[c].ef_p_time=='오후반') {
  										cell.innerHTML +="<font size=1 color=blue><br/>●</font><span class=\"calenderText\"> 오후 : "+data[c].ef_r_personal+"/"+data[c].ef_p_personal+"</span>";
  										cell.style.backgroundColor="#e6e6e6"; 
  									}
  									if(data[c].ef_p_time=='저녁반'){
  										cell.innerHTML +="<font size=1 color=red><br/>●</font><span class=\"calenderText\"> 저녁 : "+data[c].ef_r_personal+"/"+data[c].ef_p_personal+"</span>";
  										cell.style.backgroundColor="#e6e6e6"; 
  									}
 								}
 								if(cntt>=3 && cntt<4) {
 									cell.innerHTML +="<br/><span class=\"calenderMoreText\">...더보기</span>";
 								}
 								cntt+=1;
 							}
 						}
 					});
 				}	
 			}) // ajax 기능 끝.
 		})(i);	
 
		// 셀 클릭시 수행할 기능에 대하여 설정한다.
     	cell.onclick = function() {
        	var testCnt = 0;
        	var tr = '';
       	 	var test_pt = 0;
        	var efCount   = "<c:out value='${memregi.ef_count}'/>";
        	clickedDate = this.getAttribute('id'); // 상기 설정한 각 셀의 정보를 가져온다.

        	if(startTerm.valueOf() > clickedDate.valueOf() || endTerm.valueOf() < clickedDate.valueOf()) {  // 회원의 수강시작일 부터 종료일 까지의 데이터만 보여줌
            	$('#overData').show();
               	$('#passData').hide();
               	$('#nullData').hide();
          		return false;
        	}	
    
        	// alert(JSON.stringify(arr[0] ));
       	 	// 예약하기에 선택한 날짜를 뿌려주기 위한 ajax  
       	 	$.ajax({
          		type:      "POST",                  // ajax 요청을 보낼 방식.
            	url:      "${contextPath}/member/findMyTypePtList.do",         // ajax 요청을 보낼 주소.
            	dataType:   "json",         // ajax 요청을 보낼 타입.
             	async:      false,
             	success:   function(data) {       // success(데이터를 성공적으로 가져왔을 경우)  (data) 를 기준하여 기능을 수행한다.
       
               		tr = '<tr>'
                   	   + '<th>과정명</th>'
                 	   + '<th>강사명</th>'
                 	   + '<th>날짜</th>'
	                   + '<th>시간</th>'
	                   + '<th>강의정원</th>'
	                   + '<th>강의인원</th>'
	                   + '<th>예약하기</th>'
	                   + '</tr>';
                   
            		$(data).each(function(r, item) {     // 데이터.each 함수로 function(인덱스넘버, 가져올 오브젝트)를 for문 처럼 인덱스넘버 만큼 수행한다. (인덱스 넘버는 데이터를 가져온 갯수) 편의를 위해 c로 분리.       
              
                  		if(data[r].ef_p_date == clickedDate) { 
                     		for(var k = 0; k < arr.length; k++) {
                       			if(data[r].ef_p_no == arr[k].ef_p_no) { // ajax로 가져온 데이터와 member_r.jsp를 불러올 때 가져온 데이터를 비교
                          			test_pt=1;                                           
                       			}
                    		}
                     		if(test_pt == 1) {
                        		tr += '<tr>' 
                        		    + '<td>'+ data[r].ef_p_type + '</td>'
                        	 	    + '<td>'+ data[r].ef_name + '</td>'
                       				+ '<td>'+ data[r].ef_p_date+'</td>'
                          			+ '<td>'+ data[r].ef_p_time + '</td>'
                          			+ '<td>'+ data[r].ef_p_personal + '</td>'
                          			+ '<td>'+ data[r].ef_r_personal + '</td>'                           
                          			+ '<td class="okMem">'+ '예약완료' + '</td>' 
                          			+ '</tr>'; 
                        		testCnt = 1;
                        		test_pt = 0;
                     		} else {
                        		if(data[r].ef_p_personal == data[r].ef_r_personal) {
                           			tr += '<tr>'
                           				+ '<td>' + data[r].ef_p_type + '</td>'
                           				+ '<td>' + data[r].ef_name + '</td>'
                           				+ '<td>' + data[r].ef_p_date + '</td>'
                             			+ '<td>' + data[r].ef_p_time + '</td>' 
		                             	+ '<td>' + data[r].ef_p_personal + '</td>'
		                             	+ '<td>' + data[r].ef_r_personal + '</td>'                           
		                             	+ '<td class="maxMem">' + '정원초과' + '</td>' 
		                             	+ '</tr>'; 
		                           	testCnt = 1;
                        		} else {
                          	 		if(efCount == 0) {
                              			tr += '<tr>' 
			                                + '<td>' + data[r].ef_p_type + '</td>'
			                                + '<td>' + data[r].ef_name + '</td>'
			                                + '<td>' + data[r].ef_p_date +'</td>'
			                                + '<td>' + data[r].ef_p_time + '</td>' 
			                                + '<td>' + data[r].ef_p_personal + '</td>'
			                                + '<td>' + data[r].ef_r_personal + '</td>'   
			                                + '<td class="minCnt">'+ '수강횟수부족' + '</td></tr>';
			                              testCnt = 1;
                           			} else {
										tr += '<tr>'
											+ '<td style="display:none;">' + data[r].ef_p_no + '</td>'
											+ '<td>' + data[r].ef_p_type + '</td>'
											+ '<td>' + data[r].ef_name + '</td>'
											+ '<td>' + data[r].ef_p_date + '</td>'
											+ '<td>' + data[r].ef_p_time + '</td>' 
											+ '<td>' + data[r].ef_p_personal + '</td>'
											+ '<td>'+ data[r].ef_r_personal + '</td>'                           
											+ '<td>' + '<a'
											+ ' onclick="return confirm(\''+data[r].ef_name+" 강사의 ["+data[r].ef_p_date+'일 '+data[r].ef_p_time+'] 예약 하시겠습니까?\')" id="memReservation" class="inTableLinkText";>'                             
											+ '예약하기'+'</a></td></tr>';   
										testCnt = 1;
                           			}
                        		}
                     		}
                		}                                    
            		});
            
            		$("#ptList").html(tr);
            	}
          	}); // End -- ajax
            
          	// 빈 셀일 경우 강의 없음 표시.
           	if(testCnt==0) {
            	$('#nullData').show();
            	$('#passData').hide();
            	$('#overData').hide();
            }
            // 내용이 있는 셀일 경우 리스트 출력.
            if(testCnt==1) {
                $('#passData').show();
                $('#nullData').hide();
                $('#overData').hide();
            }
    	} // End -- cell.onclick = function()
	} // End -- for문 

	// 날짜가 유효한 달력칸이 7칸 미만인 경우 나머지는 빈 셀로 채운다.
	if(cnt % 7 != 0) {
 		for(i = 0; i < 7 - (cnt % 7); i++) {
 			cell = row.insertCell();
 			cell.style.backgroundColor="#eeeeee";
 		}
	}
 	if(cnt % 7 == 0) {							
		calendarTable.deleteRow(calendarTable.rows.length-1);
	}
}	//END - function buildCalendar()
		
function todayCalendar() {	// 오늘 날짜 찾아가기 기능.
	today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	buildCalendar();
}	

function prevCalendar() {	// 달 뒤로 가기 기능.
	today = new Date(today.getFullYear(), today.getMonth()-1);
	buildCalendar();
}

function nextCalendar() {	// 달 앞으로 가기 기능.
	today = new Date(today.getFullYear(), today.getMonth()+1);
	buildCalendar();
}

$(document).ready(function() {
	$(document).on('click', '#memReservation', function() {
	 	
		// 클릭한 버튼의 데이터 가져오기
	 	var memReservation = $(this);
		var row = memReservation.parent().parent();
		var tr = row.children();
		
		var mef_p_no   = tr.eq(0).text();
		var mef_p_date = tr.eq(3).text();
		var mef_p_time = tr.eq(4).text();	
		
		$.ajax({
			type:		"post",
			url:		"${contextPath}/member/reservationCount.do",
			dataType:	"json",
			async : 	false,
			data:		{ef_p_date: mef_p_date, ef_p_time: mef_p_time},
			success:	function(data) {
				if(data == 0) {
					location = "${contextPath}/member/addReservation.do?ef_p_no="+mef_p_no;
				} else {
					alert("이미 [" + mef_p_date + "]일 [" +  mef_p_time + "]에 예약이 되어 있습니다.");
					return;
				}
			}
			
		});
	});
});

</script>
<body>
<div class="ownerField">
	<span class="ownerMenuTile">[ 나의 잔여 수강횟수 : 
		<span class="impactColor"><c:out value='${memregi.ef_count}'/></span> 회 / 나의 수강 기간 : 
		<span class="impactColor"><c:out value='${memregi.ef_start_term}'/></span> ~ 
		<span class="impactColor"><c:out value='${memregi.ef_end_term}'/></span> ]
	</span>
	<table class="ownerCalenderLayout">
		<tr>
			<td>
				<span class="calenderTitle">달력 스케쥴러</span><p/>
				<table id="calendar" class="calendarList">
					<tr class="calendarListTop">
						<td onclick="prevCalendar()" class="calendarListTop"><span> ◀ 이전 달 </span></td>
						<td colspan="4" id="calendarTitle" class="calendarListTop">yyyy년 m월</td>
						<td onclick="todayCalendar()" class="calendarListTop"><span>오늘로 가기</span></td> 
						<td onclick="nextCalendar()" class="calendarListTop"><span> 다음 달 ▶ </span></td>
					</tr>
					<tr class="calendarDayText">
						<td class="calendarDayText" style="color:red; background:#ffdddd;">일</td>
						<td class="calendarDayText">월</td>
						<td class="calendarDayText">화</td>
						<td class="calendarDayText">수</td>
						<td class="calendarDayText">목</td>
						<td class="calendarDayText">금</td>
						<td class="calendarDayText" style="color:blue; background:#c8e3ff;">토</td>
					</tr>
					<tr>
						<td><script type="text/javascript">buildCalendar();</script></td>
					</tr>
				</table>
			</td>
			<td>
				<span class="calenderTitle">나의 예약 현황</span><p/>
				<table class="ownerCalList">
					<tr>
						<th style="display:none;"><b>피티넘버</b></th>
						<th>과정명</th>
						<th>강사명</th>
						<th>PT날짜</th>
						<th>PT시간</th>
						<th>예약정원</th>
						<th>예약현황</th>		
						<th>예약취소</th>								
					</tr>
					<c:forEach var="reservationPtlist" items="${reservationPtlist}">
						<tr>
							<td style="display:none;">${reservationPtlist.ef_p_no}</td>
							<td>${reservationPtlist.ef_p_type}</td>
							<td>${reservationPtlist.ef_name}</td>		
							<td>${reservationPtlist.ef_p_date}</td>
							<td>${reservationPtlist.ef_p_time}</td>
							<td>${reservationPtlist.ef_p_personal}</td>
							<td>${reservationPtlist.ef_r_personal}</td>
							<!-- 현황보기 버튼을 누르면 뜨는 모달 창 테이블  -->			
							<td><a href="${contextPath}/member/deleteReservation.do?ef_id=${ef_id}&&ef_p_no=${reservationPtlist.ef_p_no}"
					               onclick="return confirm('${reservationPtlist.ef_name}강사의 '+'[${reservationPtlist.ef_p_date}일 '+ '${reservationPtlist.ef_p_time}] 예약을 취소 하시겠습니까?');"
					               class="inTableLinkText">
					               예약취소</a></td> 
						</tr>
					</c:forEach>
				</table>
<!-- 메인 달력화면 강의 현황이 보여지는 테이블 끝 -->
				<div id="nullData" style="display:none; text-align:center;">개설된 강의 목록이 없습니다. 업체 관리자에게 문의하여 주세요.</div>
				<div id="overData" style="display:none; text-align:center;">수강 가능기간이 아닙니다. 업체 관리자에게 문의하여 주세요.</div>
				<div id="passData" style="text-align:center; display:none;">
					<p/>
					<br/>
					<p/>
					<span class="calenderTitle">예약 하기</span><p/>
					<table id="ptList" class="ownerCalList">
					</table>
				</div>
			</td>
		</tr>	
	</table>
</div>
</body>
</html>