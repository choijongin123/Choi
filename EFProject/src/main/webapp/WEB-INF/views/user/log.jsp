<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib	prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="result"		 value="${param.result}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인 화면</title>
	<link href="${contextPath}/css/ezen.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<c:choose>
		<c:when test="${result =='loginFailed'}">
			<script>
			window.onload=function() {
				alert("로그인에 실패 하였습니다.\n아이디 비밀번호를 확인 하십시오.");
			}
			</script>
		</c:when>
		<c:when test="${result =='unRegistered'}">
			<script>
			window.onload=function() {
				alert("미등록 회원입니다.\n관리자에게 문의 하십시오.");
			}
			</script>
		</c:when>
	</c:choose>
</head>
<body>
<div class="Field">
	<!-- 로그인 폼 -->
	<form class="form-horizontal" method="post" action="${contextPath}/user/login.do">
	<table class="loginTable">
		<tr>			
			<th colspan=2><span class="companyTitle">로그인</span></th>
		</tr>
		<tr>
			<td><span class="loginText">아 이 디</span></td>
			<td><input type="text" name="ef_id" id="loginPageInput" placeholder="아이디 입력"/></td>
		</tr>
		<tr>
			<td><span class="loginText">비밀번호</span></td>
			<td><input type="password" name="ef_pwd" id="loginPageInput" placeholder="비밀번호 입력"/></td>
		</tr>
		<tr>
			<td colspan=2><button type="reset" class="logBtn">다시입력</button>&emsp;
			<button type="submit" class="logBtn">로그인</button></td>
		</tr>		
		<tr>
			<td colspan=2 id="find"><a href="${contextPath}/user/find.do" onclick="window.open(this.href,'_blank','width=500,height=500, scrollbars=no, resizable=no');return false;"  class="ownerMenuLinkText2">아이디/비밀번호 찾기</a></td>
		</tr>
	</table>
	</form>
</div>		
</body>
</html>