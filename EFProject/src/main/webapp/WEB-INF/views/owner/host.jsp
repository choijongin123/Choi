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
<body>
<div class="ownerField">
	<div class="subMenu">
		<p/>
		<span class="ownerMenuTile">관리 메뉴 : </span>
		<a href="${contextPath}/owner/company.do" class="ownerMenuLinkText">강의현황</a>
		<a href="${contextPath}/owner/approval.do" class="ownerMenuLinkText underline">승인관리</a>
		<a href="${contextPath}/owner/management.do" class="ownerMenuLinkText">인원관리</a>
		<a href="${contextPath}/owner/memRecord.do" class="ownerMenuLinkText">회원이력</a>
	</div>
    <!-- 강사등록 요청란 -->
	<section>	
		<span class="ownerTableTitle">* 강사 승인 요청</span>		
        <table class="ownerScTable">           		
			<tr>
				<th><b>이름</b></th>
				<th><b>아이디</b></th>
				<th><b>연락처</b></th>
				<th><b>요청일시</b></th>
				<th><b>가입승인</b></th>
			</tr>
			<c:forEach var="trainer" items="${trainerList}">
				<tr>
					<td>${trainer.ef_name}</td>
					<td>${trainer.ef_id}</td>
					<td>${trainer.ef_p_number}</td>
					<td>${trainer.ef_date}</td>
					<td><a href="${contextPath}/owner/registUser.do?ef_id=${trainer.ef_id}&&ef_class=02" 
						   onclick="return confirm('[${trainer.ef_name}] 강사를 승인 하시겠습니까?');" class="inTableLinkText">승인</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<!-- 회원정보 -->
	<section>                           
    	<span class="ownerTableTitle">* 회원 승인 요청</span>
        <table class="ownerScTable">
            <tr>
	            <th>이름</th>
	            <th>아이디</th>
	            <th>연락처</th>
	            <th>요청일시</th>
                <th>가입승인</th>
            </tr>
            <c:forEach var="member" items="${memberList}">
	            <tr>
		            <td>${member.ef_name}</td>
		            <td>${member.ef_id}</td>
		            <td>${member.ef_p_number}</td>
		            <td>${member.ef_date}</td>
                    <td><a href="${contextPath}/owner/registUser.do?ef_id=${member.ef_id}&&ef_class=03"
                      	   onclick="return confirm('[${member.ef_name}] 회원을 승인 하시겠습니까?');" class="inTableLinkText">승인</a></td>	
	            </tr>
            </c:forEach>
    	</table>	
	</section>
</div>
</body>
</html>