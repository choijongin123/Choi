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
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<script>

$(document).ready(function() {

	// 취소버튼, 가입하기 버튼 눌렀을 시
	
	$("#modBtn").on("click", function() {
		var RegExp = /^[a-zA-Z0-9]{4,12}$/; // 비밀번호 유효성 검사
		var e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; // 이메일 유효성 검사
	    var n_RegExp = /^[가-힣]{2,12}$/; //이름 유효성검사
	    
	    var objPwd = document.getElementById("ef_pwd"); // 비밀번호 입력값 받기
	    var objEmail = document.getElementById("ef_email"); // 이메일 입력값 받기
	    var objName = document.getElementById("ef_name"); //이름 입력값 받기
	    
	    

	    // 비밀번호 유효성 검사 영역
		if($("#ef_pwd").val() == '') {
			alert("비밀번호를 입력하십시오");
			$("#ef_pwd").focus();
			return false;
		}
		if(!RegExp.test(objPwd.value)){
            alert("비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");   
            $("#ef_pwd").focus();
            return false;
        }
		if($("#repwd").val() == '') {
			alert("비밀번호 확인을 입력하십시오");
			$("#repwd").focus();
			return false;
		}
		if($("#ef_pwd").val() != $("#repwd").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#repwd").focus();
			return false;
		}
		
		
		// 이름 유효성 검사 영역
		if($("#ef_name").val() == '') {
			alert("이름을 입력하십시오");
			$("#ef_name").focus();
			return false;
		}
		if(!n_RegExp.test(objName.value)){
            alert("이름에 특수문자,영어,숫자는 사용할수 없습니다. 한글만 입력하여주세요.");
            $("#ef_name").focus();
            return false;
        }
		
		// 전화번호 유효성 검사 영역
		if($("#ef_p_number").val() == '') {
			alert("전화번호를 입력하십시오");
			$("#ef_p_number1").focus();
			return false;
		}
       
		
		document.memUpdateForm.action = "${contextPath}/user/updateInfo.do";
		document.memUpdateForm.submit();
	
	}); // submit버튼
	
		
}); // function

function pwTest(){
	if($("#ef_pwd").val() == $("#repwd").val() && $("#ef_pwd").val()!="" ) {
		$("#pwChkMsg").html("비밀번호가 일치합니다.");
		$("#pwChkMsg").css("color","blue")
	}
	else if($("#ef_pwd").val() != $("#repwd").val()) {
		$("#pwChkMsg").html("비밀번호가 일치하지 않습니다.");
		$("#pwChkMsg").css("color","red")
	}
	else{
		$("#pwChkMsg").html("비밀번호를 입력해주세요.");
		$("#pwChkMsg").css("color","black")
	}
}

</script>

<body>
	
<div class="Field">
<span class="companyTitle">회원 정보 수정</span><p/>
	<form method="post" name="memUpdateForm">
	<table id="upDateInfoTable">
		<tr>
			<td><span class="mypageText" >아이디 : </span></td>
			<td><input type="text" class="modInput" id="ef_id" name="ef_id" maxlength="20" value="${member.ef_id}" placeholder="아이디 입력" readonly style="display:none;"/>
			<span class="mypageText2" >${member.ef_id}</span></td>
		</tr>
		
		<tr>
			<td><span class="mypageText" >비밀번호 : </span></td>
			<td><input type="password" class="modInput" id="ef_pwd" name="ef_pwd" maxlength="20" value="" placeholder="비밀번호 입력" oninput="pwTest();"/></td>
		</tr>
		
		<tr>
			<td><span class="mypageText" >비밀번호 확인 : </span></td>
			<td><input type="password" class="modInput" id="repwd" name="repwd" maxlength="20" value="" placeholder="비밀번호 다시 입력"  oninput="pwTest();"/></td>
		</tr>
		<tr>
			<td><span class="mypageText">비밀번호 일치여부 : </span></td>
			<td><span class="effText" id="pwChkMsg">비밀번호를 입력해주세요.</span></td>
		</tr>
		
		<tr>
			<td><span class="mypageText" >이 름 : </span></td>
			<td><input type="text" class="modInput" id="ef_name" name="ef_name" maxlength="20" value="${member.ef_name}" placeholder="이름 입력"/></td>
		</tr>
		
		<tr>
			<td><span class="mypageText">연락처 : </span></td>
			<td><input type="text" class="modInput" id="ef_p_number" name="ef_p_number" maxlength="20" value="${member.ef_p_number}" placeholder="연락처 입력"/></td>
		</tr>
		
		<tr>
			<td><span class="mypageText">이메일 : </span></td>
			<td><input type="text" class="modInput"id="ef_email" name="ef_email" maxlength="50" value="${member.ef_email}" placeholder="이메일 입력" readonly  style="display:none;"/>
			<span class="mypageText2" >${member.ef_email}</span></td>
		</tr>
	</table>	
	<input type="button" class="button"  onclick="location.href='${contextPath}/user/myPage.do'" value="취소하기">
	<input type="button" class="button" id="modBtn" value="수정하기">
	</form>
</div>	
</body>
</html>