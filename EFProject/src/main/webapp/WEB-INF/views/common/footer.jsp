<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>footer</title>
	<link href="${contextPath}/css/ezen.css" rel="stylesheet">
</head>
<body>
<div id="footerText">
	e-mail : master@ezenfit.com<br/>
	회사주소 : 서울특별시 종로구 관철동 좋은빌딩 304호<br/>
	포로젝트 멤버 : 강지훈 장종문 정보성 조영석 최종인
</div>
</body>
</html>