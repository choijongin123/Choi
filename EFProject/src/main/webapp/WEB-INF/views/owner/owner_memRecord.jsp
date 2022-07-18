<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원기록</title>
</head>
<body>
<div class="ownerField">
	<div class="subMenu">
		<p/>
		<span class="ownerMenuTile">관리 메뉴 : </span>
		<a href="${contextPath}/owner/company.do" class="ownerMenuLinkText">강의현황</a>
		<a href="${contextPath}/owner/approval.do" class="ownerMenuLinkText">승인관리</a>
		<a href="${contextPath}/owner/management.do" class="ownerMenuLinkText">인원관리</a>
		<a href="${contextPath}/owner/memRecord.do" class="ownerMenuLinkText underline">회원이력</a>
	</div>
	<span class="ownerTableTitle">* 회원 수강 이력</span>	
	<table class="ownerScTable">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>과정명</th>
			<th>수강횟수</th>
			<th>시작날짜</th>	
			<th>종료날짜</th>	
			<th>세부사항</th>
			<th>등록날짜</th>
			<th>기타</th>
			<th>삭제하기</th>			
		</tr>
		<c:forEach var="memRecord" items="${memRecord}">
			<tr>
				<td>${memRecord.ef_id}</td>
				<td>${memRecord.ef_name}</td>
            	<td>${memRecord.ef_p_type}</td>
            	<td>${memRecord.ef_r_count}</td>
            	<td>${memRecord.ef_start_term}</td>
            	<td>${memRecord.ef_end_term}</td>
            	<td>${memRecord.ef_detail}</td>
            	<td>${memRecord.ef_r_time}</td>
            	<td>${memRecord.ef_use}</td>
            	<c:choose>
            		<c:when test="${memRecord.ef_use == 'use'}">
		            	<td>
							<a href="${contextPath}/owner/memRecordDelete.do?ef_r_time=${memRecord.ef_r_time}"
		                    		 onclick="return confirm('기록을 삭제 하시겠습니까?');" class="inTableLinkText">삭제하기</a>
		      			</td> 
    				</c:when>
	    			<c:otherwise>
	      				 <td>
						삭제하기
	      				 </td> 
	      			</c:otherwise>
            	</c:choose>
            </tr>
        </c:forEach>														
	</table >
</div>
</body>
</html>