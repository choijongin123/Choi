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

// function buildCalendar() 달력을 생성하는 기능 선언
function buildCalendar(){
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
  	for(i = 0; i < firstDate.getDay(); i++){
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
  		if (i<10 && 1+today.getMonth() <10){		// 일이 10보다 작고, 월이 10보다 작은 경우(둘 다 10보다 작은 경우) 구분기호 뒤에 0을 더한다.
  			cellDate=today.getFullYear()+"-0"+(1+today.getMonth())+"-0"+i;
  		
  		} else if (1+today.getMonth() <10){			// 월이 10보다 작은 경우에도 구분기호 뒤에 0을 더한다.
			cellDate=today.getFullYear()+"-0"+(1+today.getMonth())+"-"+i;
		
  		} else if (i <10){							// 일이 10보다 작은 경우에도 구분기호 뒤에 0을 더한다.
			cellDate=today.getFullYear()+"-"+(1+today.getMonth())+"-0"+i;
		
  		} else {										// 그 외에는 구분기호 -만 표기한다.
			cellDate=today.getFullYear()+"-"+(1+today.getMonth())+"-"+i;
		}

		// DB와의 균일화를 위해 상동 원리로 구분기호 뒤 0을 구분한다.
  		if(i < 10 && (1+today.getMonth() < 10)) {	
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
  	
  		// 셀 클릭시 수행할 기능에 대하여 설정한다.
	  	cell.onclick = function() {
	    	clickedDate = this.getAttribute('id'); // 상기 설정한 각 셀의 정보를 가져온다.
	    	if(clickedDate.valueOf() < curTime.valueOf()) { //오늘 이전 날짜를 클릭 시
	    		document.all("dayChk").innerHTML='';	// ddd라는 dvi 위치에 셀의 정보를 출력한다.
	    		$('input[name=ef_p_date]').attr('value',''); // 공란을 출력해서 강의 등록을 막음
	    		$('#addClassField').hide(50);
	    	} else {	// 선택한 셀에 가져온 셀의 정보를 출력한다.
	    		document.all("dayChk").innerHTML=clickedDate;	// ddd라는 dvi 위치에 셀의 정보를 출력한다.
	    		$('input[name=ef_p_date]').attr('value',clickedDate);
			 	$('#addClassField').hide(50);
		    	$('#addClassField').show(200);
		    	$('#classListField').hide(50);
		    	$('.revMemListField').hide(50);
	    	}
  		} // End -- cell.onclick = function()
		
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
	    if (cellDate==curTime){
	    	cell.innerHTML = "<font color=orange class=\"textShadow\">"+i+"</font>";
	    }
 	
		// ajax 수행을 포문의 i(인덱스) 값 만큼 수행한다.
  		(function(i){
  			var cntt = 0;
  		
  			$.ajax({
  				type:		"POST",						// ajax 요청을 보낼 방식.
  				url:		"${contextPath}/trainer/findMyOpenPtList.do",		// ajax 요청을 보낼 주소.
  				dataType:	"json",						// ajax 요청을 보낼 타입.
  				async:		false,
  				success:	function(data) { 			// success(데이터를 성공적으로 가져왔을 경우)  (data) 를 기준하여 기능을 수행한다.
  					$(data).each(function(c, item) {		// 데이터.each 함수로 function(인덱스넘버, 가져올 오브젝트)를 for문 처럼 인덱스넘버 만큼 수행한다. (인덱스 넘버는 데이터를 가져온 갯수) 편의를 위해 c로 분리.
  					
  						if(data[c].ef_p_date==cellDate) {	// if문으로 data[c]를 입력하여 가져온 데이터 수 만큼 반복수행되는 each문 안에서 각 데이터 오브젝트의.'ef_p_time' 에 대항하는 값을 가져와 비교한다.
  							if(cntt<3) {
  								if(data[c].ef_p_time=='오전반') {
  									cell.innerHTML +="<font size=1 color=green><br/>●</font><span class=\"calenderText\"> 오전반</span>";
  									cell.style.backgroundColor="#e6e6e6";
  								}	
  								
  								if(data[c].ef_p_time=='오후반') {
  									cell.innerHTML +="<font size=1 color=blue><br/>●</font><span class=\"calenderText\"> 오후반</span>";
  									cell.style.backgroundColor="#e6e6e6"; 
  								}
  						
  								if(data[c].ef_p_time=='저녁반') {
  									cell.innerHTML +="<font size=1 color=red><br/>●</font><span class=\"calenderText\"> 저녁반</span>";
  									cell.style.backgroundColor="#e6e6e6"; 
  								}
  							}
  							if(cntt>=3 && cntt<4) {
  								cell.innerHTML +="<br/><span class=\"calenderMoreText\">...더보기</span>";
  							}
  							cntt+=1; 
  						}
  					});
  				}
  			
  			})
  		})(i);	// ajax 기능 끝.
  	} // for문 끝
 	
 	 // 날짜가 유효한 달력칸이 7칸 미만인 경우 나머지는 빈 셀로 채운다.
 	 if(cnt % 7 != 0){
  	 	for(i = 0; i < 7 - (cnt % 7); i++) {
  			cell = row.insertCell();
  			cell.style.backgroundColor="#eeeeee";
  		}
  	}
 	 
  	if(cnt % 7 == 0) {							
		calendarTable.deleteRow(calendarTable.rows.length-1);
	}
} //END - function buildCalendar()
		
function todayCalendar(){	// 오늘 날짜 찾아가기 기능.
	today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	buildCalendar();
}	

function prevCalendar(){	// 달 뒤로 가기 기능.
	today = new Date(today.getFullYear(), today.getMonth()-1);
	buildCalendar();
}

function nextCalendar(){	// 달 앞으로 가기 기능.
	today = new Date(today.getFullYear(), today.getMonth()+1);
	buildCalendar();
}

</script>
<script>
$(document).ready(function testP() {
	$("#sbBtn").on("click", function() {
		var RegExp = /^[0-9]{1,2}$/; 
     	var objNum2 = document.getElementById("ef_p_personal"); 
    	rtv=false;

     
   		if($("#ef_p_date").val()!='') {
  	  		var testTime = 0;
    		$.ajax({
          		type:      "POST",                  // ajax 요청을 보낼 방식.
          		url:      "${contextPath}/trainer/findMyOpenPtList.do",         // ajax 요청을 보낼 주소.
          		dataType:   "json",         // ajax 요청을 보낼 타입.
          		async:      false,
          		success:   function(data) {          // success(데이터를 성공적으로 가져왔을 경우)  (data) 를 기준하여 기능을 수행한다.
             		$(data).each(function(c, item){      // 데이터.each 함수로 function(인덱스넘버, 가져올 오브젝트)를 for문 처럼 인덱스넘버 만큼 수행한다. (인덱스 넘버는 데이터를 가져온 갯수) 편의를 위해 c로 분리.
                
                		if(data[c].ef_p_date == $("#ef_p_date").val()) {   // if문으로 data[c]를 입력하여 가져온 데이터 수 만큼 반복수행되는 each문 안에서 각 데이터 오브젝트의.'ef_p_time' 에 대항하는 값을 가져와 비교한다.

		                   if(data[c].ef_p_time == $("#ef_p_time").val() && $("#ef_p_time").val()=='오전반') {
			                   alert("오전반 이미 예약중");
			                   testTime = 1;
		                   }

                   		   if(data[c].ef_p_time == $("#ef_p_time").val() && $("#ef_p_time").val()=='오후반') {
	                     	   alert("오후반 이미 예약중");
	                           testTime = 1;
                   		   }

                   		   if(data[c].ef_p_time == $("#ef_p_time").val() && $("#ef_p_time").val()=='저녁반') {
	                     	   alert("저녁반 이미 예약중");
	                     	   testTime = 1;
                   		   }
               			}
             	   });
          	    }
       		});
   			if(testTime == 1) {
				return false;
   			}
    	}
    
   		// 정원 입력을 안 했거나 0으로 입력한 경우.
    	if($("#ef_p_personal").val() == ''||$("#ef_p_personal").val() == 0) {
      		alert("정원(1~99명)을 입력하여 주십시오.");
       		$("#ef_p_personal").focus();
       		return false;
   		}   
    
    	// 정원을 숫자 외 문자로 입력한 경우.
    	if(!RegExp.test(objNum2.value)){
        	alert("정원은 숫자로만(1~99명) 입력 가능합니다.");   
        	$("#ef_p_personal").focus();
        	return false;
    	}
    
    	document.schedule.action = "${contextPath}/trainer/addPt.do";
    	document.schedule.submit();
	});
   
   $(".inTableLinkText").click(function() {
		var modifyBtn = $(this);

		var row = modifyBtn.parent().parent();
		var tr = row.children();
		
		var mef_p_no   = tr.eq(0).text();
		var mef_p_type = tr.eq(1).text();
		var mef_p_date = tr.eq(2).text();
		var mef_p_time = tr.eq(3).text();
		var mef_p_personal = tr.eq(4).text();
		
		$("#mef_p_no").val(mef_p_no);
		$("#mef_p_type").val(mef_p_type);
		$("#mef_p_date").val(mef_p_date);
		$("#mef_p_time").val(mef_p_time);
		$("#mef_p_personal").val(mef_p_personal);
	});
  
   $("#classModifyBtn").click(function() {
	   var pnum = /^[0-9]{1,2}$/; // 강의정원 수 (1~10)까지
	   var countNum = document.getElementById("mef_p_personal"); // 입력한 강의정원 넘겨받기  
	   // 인원 수 유효성 검사
       if($("#mef_p_personal").val() == '') { // 강의 정원을 입력하지 않을때
	       alert("강의 정원을 입력하십시오.");
	       $("#mef_p_personal").focus(); // 포커스()
	       return false;
       }
	   
       // 강의 정원을 1~10까지로 입력하지 않거나 0으로 입력하였을 때, 11로 입력하였을 때
       if(!pnum.test(countNum.value)||$("#mef_p_personal").val() == 0||$("#mef_p_personal").val() >= 11) { 
	       alert("1~10명까지만 가능합니다.(숫자만 입력가능합니다.)");   
	       $("#mef_p_personal").focus();// 포커스()
	       return false;
       }         
	}); // modal submit 버튼 끝
	
	$(document).on('click', '.reservationBtn', function() {
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
	           		$(data).each(function(r, item){
		                tr += '<tr><td style="display:none;">'+ data[r].ef_p_no + '</td>'
		                	+ '<td>'+ data[r].ef_id + '</td>'
		                    + '<td>'+ data[r].ef_name+'</td>'
		                    + '<td>'+ data[r].ef_p_number + '</td></tr>'; 
	            	});
	           } else {
		        	tr += '<tr><td colspan=3>예약된 회원이 없습니다.</td></tr>';	
	           }
	           $("#reservationList").html(tr);
	        }
	    });
	});
	
	 // 강의 삭제하기 클릭 시 예약정원 > 0 삭제 안됨
	 $(document).on('click', '.deletePt', function() {
		 var deletePt = $(this);
		    
		 var row = deletePt.parent().parent();
		 var tr = row.children();
		    
		 var ef_r_personal   = tr.eq(5).text();
		 if(ef_r_personal > 0) {
			 alert("예약 인원이 있어 강의 취소가 어렵습니다.");
		 }
	});
	 
});

$(".hidden").attr('style', "display:none;");
</script>
<script>
function moDivOff() {
	$('#addClassField').hide(200);
}

function moDivOff2() {
	$('#classListField').hide(200);
}

function moDivOff3() {
	$('.revMemListField').hide(200);
}

function moDivOn() {
	$('#classListField').hide(50);
	$('#classListField').show(200);
	$('#addClassField').hide(200);
	$('.revMemListField').hide(200);
}

function moDivOn2() {
	$('.revMemListField').hide(50);
	$('.revMemListField').show(200);
	$('#addClassField').hide(200);
	$('#classListField').hide(200);
}

</script>
<body>
<div class="ownerField">
	<div class="subMenu">
		<span class="ownerTitle">나의 업체명 : ${ef_c_name}</span>
		<span class="ownerMenuTile">관리 메뉴 : </span>
		<a href="#" class="ownerMenuLinkText">강의관리</a> 
		<a href="${contextPath}/trainer/ptHistory.do" class="ownerMenuLinkText">강의이력</a> 
	</div>	
	<table class="ownerCalenderLayout">
		<tr>
			<td>
				<span class="calenderTitle">달력 스케쥴러</span><p/>
				<table id="calendar" class="calendarList">
					<tr class="calendarListTop">
						<td align="center" onclick="prevCalendar()" class="calendarListTop"><span> ◀ 이전 달 </span></td>
						<td colspan="4" align="center" id="calendarTitle" class="calendarListTop">yyyy년 m월</td>
						<td align="center" onclick="todayCalendar()" class="calendarListTop"><span>오늘로 가기</span></td> 
						<td align="center" onclick="nextCalendar()" class="calendarListTop"><span> 다음 달 ▶ </span></td>
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
				<div id="listAll">
					<span class="calenderTitle">개설 강의 목록</span><p/>
					<table class="ownerCalList2">
						<thead>
							<tr>
								<th style="display:none;"></th>
								<th>과정명</th>
								<th>날짜</th>
								<th>시간</th>
								<th>강의정원</th>
								<th>예약인원</th>
								<th>강의관리</th>
								<th>에약현황</th>
								<th style="padding: 0 5px 0 0;">강의삭제</th>									
							</tr>
						</thead>
						<tbody>
							<c:forEach var="trainer_pt" items="${trainer_ptList}">
								<tr>
									<td style="display:none;">${trainer_pt.ef_p_no}</td>
									<td align="center">${trainer_pt.ef_p_type}</td>
									<td align="center">${trainer_pt.ef_p_date}</td>
									<td align="center">${trainer_pt.ef_p_time}</td>
									<td align="center">${trainer_pt.ef_p_personal}</td>
									<td align="center">${trainer_pt.ef_r_personal}</td>
									<td align="center"><span class="inTableLinkText" onclick="moDivOn()">강의관리</span></td>
									<td><span class="inTableLinkText reservationBtn" onclick="moDivOn2()">예약현황</span></td>
									<td><a href="${contextPath}/trainer/deletePt.do?ef_p_date=${trainer_pt.ef_p_date}&&ef_p_time=${trainer_pt.ef_p_time}"
							               onclick="return confirm('강의를 삭제 하시겠습니까?');" class="inTableLinkText deletePt">
							               삭제하기</a></td>  
								</tr>	
							</c:forEach>
						</tbody>
					</table>
				</div>
	
				<div id="addClassField" class="addClassField" style="display:none;">
					<span class="calenderTitle">강의 개설하기</span><p/>
					<form id="schedule" name="schedule" method="Post">
						<table class="addClassTable">
							<tr style="display:none;">
								<td>
									<input type="text" id="ef_id" name="ef_id" value="${ef_id}">
									<input type="text" id="ef_name" name="ef_name" value="${ef_name}">
									<input type="text" id="ef_c_name" name="ef_c_name" value="${ef_c_name}">
									<input type="text" id="ef_r_personal" name="ef_r_personal" value="0">
									<input type="text" id="ef_p_date" name="ef_p_date">
								</td>
							</tr>
							<tr>
								<td>
									<span class="addClassText">선택한 날짜 : </span>
								</td>
								<td class="addClassLAlign">
									<div id="dayChk" class="addClassDayText"></div>
								</td>
							</tr>
							<tr>
								<td>
									<span class="addClassText">강의 종류 : </span>
								</td>
								<td class="addClassLAlign">
									<select id="ef_p_type" name="ef_p_type" style="width:80px;">
								   		<option value="필라테스" selected>필라테스</option>
								    	<option value="PT">PT</option>
								    </select>
								</td>
							</tr>	
							<tr>
								<td>
									<span class="addClassText">강의 시간 : </span>
								</td>
								<td class="addClassLAlign">
									<select id="ef_p_time" name="ef_p_time" style="width:80px;">
								    	<option value="오전반" selected>오전반</option>
								    	<option value="오후반">오후반</option>
								    	<option value="저녁반">저녁반</option>
								    </select>
								</td>
							</tr>
							<tr>
								<td>
									<span class="addClassText">강의 정원 : </span>
								</td>
								<td class="addClassLAlign">
									<input type="text" id="ef_p_personal" name="ef_p_personal" style="width:80px;"> 명<br/>
								</td>
							</tr>
							<tr>
								<td>
								    <span id="sbBtn" class="ownerMenuLinkText2">강의개설</span>
								</td>
								<td>
									<span onclick="moDivOff()" class="ownerMenuLinkText2">닫기</span>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</td>	
	</table>
</div>

<div id="classListField" class="classListField" style="display:none;">
	<span class="calenderTitle">강의현황</span><p/>
	<form id="updatePt" method="post" action="${contextPath}/trainer/updatePt.do">
		<table class="classListTable">
			<tr style="display:none;">
				<td class="ListTableFst" style="display:none;">no</td>
				<td style="display:none;"><input id="mef_p_no" name="ef_p_no" type="text" readonly></td>
			</tr>
			<tr>
				<td class="ListTableFst"><span class="classListTableText">과정명 :</span></td>
				<td><input id="mef_p_type" name="ef_p_type" type="text" class="updatePtInput" readonly></td>
			</tr>
			<tr>
				<td class="ListTableFst"><span class="classListTableText">PT날짜 :</span></td>
				<td><input id="mef_p_date" name="ef_p_date" type="text" class="updatePtInput" readonly></td>
			</tr>
			<tr>
				<td class="ListTableFst"><span class="classListTableText">강의시간 :</span></td>
				<td><input id="mef_p_time" name="ef_p_time" type="text" class="updatePtInput" readonly></td>
			</tr>
			<tr>
				<td class="ListTableFst"><span class="classListTableText2">강의정원 :</span></td>
				<td><input id="mef_p_personal" name="ef_p_personal" type="text" class="updatePtInput2" ></td>
			</tr>
			<tr>
				<td colspan=2>
					<button id="classModifyBtn" type="submit" class="classModifyBtn">수정하기</button>
					<span>　 　</span>
					<span onclick="moDivOff2()" class="ownerMenuLinkText2">닫기</span>
				</td>
			</tr>				
		</table>
	</form>
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