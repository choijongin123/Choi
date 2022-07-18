<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>myPage</title>
	<link href="${contextPath}/css/ezen.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="Field">
<span class="companyTitle">My Page</span><p/>
	<form method="post" name="memUpdateForm" action="${contextPath}/user/myPage.do">
	<table id="myInfoTable">
		<tr>
			<td><span class="mypageText">업체명 : </span></td>
			<td><span class="mypageResultText">${member.ef_c_name}</span></td>
		</tr>
		<tr>
			<td><span class="mypageText">아이디 : </span></td>
			<td><span class="mypageResultText">${member.ef_id}</span></td>
		</tr>
		<tr>	
			<td><span class="mypageText">이름 : </span></td>
			<td><span class="mypageResultText">${member.ef_name}</span></td>
		</tr>
		<tr>
			<td><span class="mypageText">전화번호 : </span></td>
			<td><span class="mypageResultText">${member.ef_p_number}</span></td>
		</tr>
		<tr>
			<td><span class="mypageText">이메일 : </span></td>
			<td><span class="mypageResultText">${member.ef_email}</span></td>
		</tr>
	</table>
	<input type="button" onclick="location.href='${contextPath}/user/updateForm.do'" value="수정하기" class="button">
	</form>	
</div>
</body>
</html>