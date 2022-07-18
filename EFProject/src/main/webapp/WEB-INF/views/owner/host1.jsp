<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
		<link href="${contextPath}/css/ezen.css" rel="stylesheet">    
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<script>
$(document).ready(function() {
	$(document).on('click', '.deleteUser', function() {
		if(confirm("정말 탈퇴 처리 하시겠습니까??") == true) {
			var deleteUser = $(this);

			var row = deleteUser.parent().parent();
		 	var tr = row.children();
		
		 	var mef_p_class   = tr.eq(0).text();
		 	var mef_p_id      = tr.eq(1).text();
		 	var mef_p_name    = tr.eq(2).text();
		 	//alert(mef_p_id + ", " + mef_p_class);
		 
		  	$.ajax({
	       		type:      "POST",                              
	        	url:        "${contextPath}/owner/delete.do",       
	        	dataType:   "json",                           
	        	data:      {ef_class: mef_p_class, ef_id: mef_p_id},
	       		async:      false,
	        	success:   function(data) {
	        		if(mef_p_class == 12) {
			        	if(data != 0) {
			        		alert("개설된 강의를 취소해 주세요.");
			        	} else {
			        		alert(mef_p_name + "님이 탈퇴 처리 되었습니다.");
			        		location.reload();	
			        	}
	        		} else {
		        		if(data != 0) {
		        			alert("에약된 강의를 취소해 주세요.");
		        		} else {
		        			alert(mef_p_name + "님이 탈퇴 처리 되었습니다.");
			        		location.reload();	
		        		}		
	        		}
	        	}
		  });
		} else {
			return;
		}  		
	});
});

</script>
<body>
<div class="ownerField">
	<div class="subMenu">
		<p/>
		<span class="ownerMenuTile">관리 메뉴 : </span>
		<a href="${contextPath}/owner/company.do" class="ownerMenuLinkText">강의현황</a>
		<a href="${contextPath}/owner/approval.do" class="ownerMenuLinkText">승인관리</a>
		<a href="${contextPath}/owner/management.do" class="ownerMenuLinkText underline">인원관리</a>
		<a href="${contextPath}/owner/memRecord.do" class="ownerMenuLinkText">회원이력</a>
	</div>
    <!-- 강사등록 요청란 -->
    
	<section>	
		<span class="ownerTableTitle">* 승인 강사 목록</span>		
        <table class="ownerScTable">           		
			<tr>
				<th style="display:none;">class</th>
				<th>아이디</th>
				<th>이름</th>
				<th>연락처</th>
				<th>퇴사처리</th>
			</tr>
			<c:forEach var="trainer" items="${trainerList}">
				<tr>
					<td style="display:none;">${trainer.ef_class}</td>
					<td>${trainer.ef_id}</td>
					<td>${trainer.ef_name}</td>
					<td>${trainer.ef_p_number}</td>
					<td><span class="inTableLinkText deleteUser">퇴사처리</span></td>	
				</tr>
			</c:forEach>
		</table>
	</section>

	<!-- 회원정보 -->
	<section>                           
    	<span class="ownerTableTitle">* 승인 회원 목록</span>
        <table class="ownerScTable">
        	<tr>
            	<th style="display:none;">class</th>
	            <th>아이디</th>
	            <th>이름</th>
	            <th>연락처</th>
	            <th>이메일</th>	
                <th>과정명</th>
                <th>잔여횟수</th>
                <th>수강시작일</th>	
                <th>수강종료일</th>
                <th>남은수강기간</th>
                <th>수강일수정</th>
                <th>탈퇴하기</th>		
            </tr>
            <c:forEach var="member" items="${memberList}">
            	<tr>
	            	<td style="display:none;">13</td>
		            <td>${member.ef_id}</td>
		            <td>${member.ef_name}</td>
		            <td>${member.ef_p_number}</td> 
		            <td>${member.ef_email}</td>  
                    <td>${member.ef_p_type}</td>
                    <td>${member.ef_count}</td>
                    <td>${member.ef_start_term}</td>
                    <td>${member.ef_end_term}</td>
                    <td>${member.ef_term}</td>      
                    <td><a href="${contextPath}/owner/courseRegistForm.do?ef_id=${member.ef_id}" class="inTableLinkText">수정하기</a></td>                                           
                    <td><span class="inTableLinkText deleteUser">탈퇴하기</span></td>	
	            </tr>
            </c:forEach>
    	</table>	
	</section>
</div>
</body>
</html>