<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <title>Insert title here</title>
	<!-- jQuery (Datepicker 사용) -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<script>
$(function() {
	//오늘 날짜를 출력
	$("#today").text(new Date().toLocaleDateString());
	
	// 강의 시작일자
	$("#calander1").datepicker({
		// 옵션
		// minDate: 0
		
		// 시작일이 종료일 보다 이후 날짜 일 수 없다.
		onClose: function( selectedDate ) {    
            // 시작일(datepicker1) datepicker가 닫힐때
            // 종료일(datepicker2)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
            $("#calander2").datepicker( "option", "minDate", selectedDate );
        }  
	});

	// 강의 종료일자
	$("#calander2").datepicker({
		// 옵션
		
		// 종료일이 시작일 보다 이전 날짜 일 수 없다.
		onClose: function( selectedDate ) {
            // 종료일(datepicker2) datepicker가 닫힐때
            // 시작일(datepicker1)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
            $("#calander1").datepicker( "option", "maxDate", selectedDate );
        } 
	});
});

// 데이트피커의 기본설정
$(function() {
	$.datepicker.setDefaults({
		changeYear:		true,		// 년도를 바꿀 수 있는 셀렉트 박스를 보여준다.
		changeMonth:	true,		// 월을 바꿀 수 있는 셀렉트 박스를 보여준다.
		showAnim:		"slide",	// 애니메이션을 적용한다.
		dateFormat:		'yy-mm-dd',	// 날짜 포맷. 보통 yy-mm-dd를 많이 사용한다.
		prevText:		'이전 달',	// ◀ 버튼에 마우스 오버시 이전달 텍스트를 보여준다.
		nextText:		'다음 달',	// ▶ 버튼에 마우스 오버시 다음달 텍스트를 보여준다.
		closeText:		'닫기',		// 닫기 버튼의 텍스트 변경
		currentText:	'오늘',		// 오늘 텍스트 변경
		// 한들 캘린더 중 월 표시를 위한 부분
		monthNames:		['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort:['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames:		['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort:	['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin:	['일', '월', '화', '수', '목', '금', '토'],
		showMonthAfterYear:	true,	// true : 년 월, false : 월 년 순으로 보여준다.
		yearSuffix:		'년',		// 년도 뒤에 글자
		showButtonPanel:	true,	// 오늘로 가는 버튼과 달력의 닫기 버튼 보기 옵션
		// buttonImageOnly:		true,	// input 옆에 조그마한 아이콘으로 캘린더 선택가능하게 하기
		// buttonImage:			"images/calendar.gif"	// 조그마한 아이콘 이밎
		// buttonText:			"Select Date"			// 조그마한 아이콘 툴팁
	});
});
</script>
<script>
$(document).ready(function() {
			
	$("#subBtn").on("click", function() {
		
		var RegExp    = /^[0-9]{1,3}$/; // 횟수부여 유효성 검사 (1~999)까지
		var RegExpp   = /^[-][0-9]{1,3}$/; // 횟수부여 유효성 검사 (-999~-1)까지
	   				    
	    var countNumm  = document.getElementById("ef_r_count"); // 추가수강횟수 넘겨받기 
	
		  // 추가 수강횟수 유효성 검사 영역
		if($("#ef_r_count").val() == '') { // 수강횟수를 입력하지 않을때
			alert("추가수강 횟수를 입력하십시오.");
			$("#ef_r_count").focus(); // 포커스()
			return false;
		}
		if(!RegExpp.test(countNumm.value)&&!RegExp.test(countNumm.value)) { // 수강횟수를 1~999까지로 입력하지 않거나 0으로 입력하였을 때
			alert("-999에서 999회 까지만 가능합니다.");   
			$("#ef_r_count").focus();
			return false;
	    }		
		// 수강시작일 유효성 검사 영역								  
		if($("#calander1").val() == '') { // 수강시작일을 입력하지 않을때
			alert("수강 시작일을 입력하십시오.");
			$("#calander2").focus(); // 포커스()
			return false;
		}
		
		// 수강종료일 유효성 검사 영역								  
		if($("#calander2").val() == '') { // 수강종료일을 입력하지 않을때
			alert("수강 종료일을 입력하십시오.");
			$("#calander2").focus(); // 포커스()
			return false;
		}
		document.ef_alter.action = "/owner/courseRegist.do";
		document.ef_alter.submit();	
	}); // submit버튼
});
</script>

<!-- 수강일정 수정하기 -->
<div class="Field">
	<span class="alterTitle"> 강의 일정 수정하기</span><p/>
	<form method="post" name="ef_alter" >
		<table id="host_alterTable">
   			<tr>
      			<td colspan=4>수정 회원 아이디 : ${member.ef_id}</td>
   				<td style="display:none;"><input value="${member.ef_id}" name="ef_id"/></td>
      		</tr>
		    <tr>
		      	<td colspan=2>수정사유 : </td>
		      	<td colspan=2>
		      		<select id="ef_detail" class="alterInput" name="ef_detail" >
	       				<option value="등록" selected>등록</option>
	       				<option value="기간변경">기간변경</option>
	       				<option value="수강횟수변경">수강횟수변경</option>
	       				<option value="환불">환불</option>
       	          	</select>
		        </td>
	      	</tr>
			<tr>
        		<td colspan=2>과정명 : </td>
        		<td colspan=2>
        			<select id="ef_p_type" class="alterInput" name="ef_p_type" >
         				<option value="필라테스" selected>필라테스</option>
         				<option value="PT">PT</option>
         			</select>
         		</td>
      		</tr>
     		<tr>
         		<td colspan=2>잔여횟수 : </td>
         		<td colspan=2><input type="text" class="alterInput" name="ef_count" maxlength="20" value="${member.ef_count}" placeholder="수강횟수 입력" readonly/></td>
  			</tr>
   			<tr>
      			<td colspan=2>추가횟수 : </td>
      			<td colspan=2><input type="text" id="ef_r_count" class="alterInput" name="ef_r_count" maxlength="20" placeholder="추가횟수 입력"/></td>
   			</tr>
   			<tr>
	      		<td colspan=2>강의시작일 : </td>
	      		<td colspan=2><input type="text" id="calander1" class="alterInput" name="ef_start_term" value="${member.ef_start_term}" placeholder="수강 시작일 선택" readonly/></td>
  		 	</tr>
	   	 	<tr>
		     	<td colspan=2>강의종료일 : </td>
		      	<td colspan=2><input type="text" id="calander2" class="alterInput" name="ef_end_term" value="${member.ef_end_term}" placeholder="수강 종료일 선택" readonly/></td>
		   	</tr>
		   	<tr>
		   		<td colspan=2><input type="button" class="button" value="뒤로가기" onclick="history.back();" /></td>
				<td><button type="reset" id="reset" class="button">다시입력</button></td>
				<td><button type="button" id="subBtn" class="button">수정하기</button></td>
		   	</tr>
		</table>
	</form>
</div>

</body>
</html>