<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <title>▒▒ 이젠핏에 오신것을 환영합니다. ▒▒</title>
     <link href="${contextPath}/css/ezen.css" rel="stylesheet">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script>
var slideIndex = 0; // slide index => 사진은 배열로 0부터 2까지 담겨져 있다.

// HTML 로드가 끝난 후 동작
window.onload=function(){
showSlides(slideIndex);

	// 자동 슬라이드 제어
	var sec = 5000;
	setInterval(function(){  // setInterval(): 일정한 시간 간격으로 작업을 수행하기 위해서 사용
	 slideIndex++;
	 showSlides(slideIndex);
	
	}, sec);
}

// 다음 이전 버튼 제어
function moveSlides(n) {
	slideIndex = slideIndex + n 
	showSlides(slideIndex);
}

// 사진 제어
function currentSlide(n) {
	slideIndex = n;
	showSlides(slideIndex);
}

function showSlides(n) {

	var slides = document.getElementsByClassName("mySlides"); // mySlides : 각 사진이 3개이므로 3
	var dots = document.getElementsByClassName("dot");        // dot : 하단 도트버튼
	var size = slides.length;  
	// alert(slides[0]);
	// alert(size);
	if ((n+1) > size) { // 마지막 사진에서 넘어갈떄 n은 3이 되므로 +1하면 4가 되어서 size(3)보다 커지므로 실행 => slideIndex와 n을 0으로 바꿔주어 다시 처음사진이 출력
		// alert(n);     
	 	slideIndex = 0; n = 0;
	}else if (n < 0) { 
		 // alert(n);
	 	slideIndex = (size-1);
	 	n = (size-1);
	}
	
	for (i = 0; i < slides.length; i++) {
	   slides[i].style.display = "none";
	}
	for (i = 0; i < dots.length; i++) {
	   dots[i].className = dots[i].className.replace(" active", "");
	}
	
	slides[n].style.display = "block";
	dots[n].className += " active";
}
</script>

<body>
	<!-- 슬라이드쇼 컨테이너 -->
	<div class="slideshow-container">
	
	  <!-- 숫자와 캡션이 있는 이미지 -->
	  <div class="mySlides fade"> 
	    <img class="slideImg" src="../../resources/images/wing1.png">
	  </div>	
	  <div class="mySlides fade">
	    <img class="slideImg" src="../../resources/images/wing2.png">
	  </div>
	
	  <!-- 다음, 이전 이미지 버튼 -->
	  <a class="prev" onclick="moveSlides(-1)">&#10094;</a>
	  <a class="next" onclick="moveSlides(1)">&#10095;</a>	  
	</div>
	
	<!-- 현재 이미지를 알려주는 하단의 점 -->
	<div style="text-align:center">
	  <span class="dot" onclick="currentSlide(0)"></span> 
	  <span class="dot" onclick="currentSlide(1)"></span> 
	</div>
	
</body>
</html>





