<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<body>
<h1>
	Hello world!  gd
</h1>

<P>  The time on the server is ${serverTime}. </P>
<input type="date" placeholder="달력"/>
<span class="inTableLinkText reservationBtn" onclick="moDivOn3()">예약현황</span>
</body>
</html>
