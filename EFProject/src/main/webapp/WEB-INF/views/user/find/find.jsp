<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 / 비밀번호 찾기</title>
<link href="${contextPath}/css/ezen.css" rel="stylesheet">
</head>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
function setDisplay(){
    if($('input:radio[id=idFind]').is(':checked')){
        $('#pwFindForm').hide();
        $('#idFindForm').show();
    }else{
        $('#pwFindForm').show();
        $('#idFindForm').hide();
    }

}

function find_id() {
	 var name = $("#find_id_name").val();
	 var num  = $("#find_id_num").val();
	 
	 // alert(name + ", " + num); 
	 
	 $.ajax({
		 type:		"post",
		 url:		"${contextPath}/user/findId.do",
		 dataType:	"json",
		 async : false,
		 data:		{"ef_name": name, "ef_p_number": num},
		 success:	function(data) {
			 // alert(data.ef_id);
			 $("#finishId").html("회원님의 아이디는 '" + data.ef_id + "' 입니다." ); 
		}, error: function (){
        	alert('정보를 다시 입력해주시길 바랍니다.' );
        }
	 });
}

function find_pwd() {
	var id  = $("#find_pwd_id").val();
    var num = $("#find_pwd_num").val();
    
    // alert(id + ", " + num);
    
    $.ajax({
		 type:		"post",
		 url:		"${contextPath}/user/findPwd.do",
		 dataType:	"json",
		 async : false,
		 data:		{"ef_id": id, "ef_p_number": num},
		 success:	function(data) {
			 // alert(data.ef_id);
			 $("#finishPw").html("회원님의 비밀번호는 '" + data.ef_pwd + "' 입니다." ); 
		}, error: function (){
       	alert('정보를 다시 입력해주시길 바랍니다.' );
       }
	 });
}

</script>

<body id="noScroll">
<div class="findView"> 
<!-- 아이디/비밀번호 찾기 영역 -->
<span class="findField">아이디 / 비밀번호 찾기</span><p/>
		<input type="radio" id="idFind" name="abcd" value="00" onchange="setDisplay()" checked="checked" class="radioText"><span class="radioText">아이디 찾기</span>
		<input type="radio" id="pwFind" name="abcd" value="10" onchange="setDisplay()" class="radioText"><span class="radioText">비밀번호 찾기</span><p/>
		
		<table id="idFindForm">
			<tr>
				<td>
					<table id="findFind">	
						<tr>	
							<td class="findText">이　름</td>
							<td><input type="text" name="ef_name" class="form-control" id="find_id_name" placeholder="이름을 입력하세요" required autofocus></td>
						</tr>
						<tr>
							<td class="findText">연락처</td>
							<td><input type="text" name="ef_p_number" class="form-control" id="find_id_num" placeholder="연락처 입력하세요" required autofocus></td>
						</tr>
						<tr>
							<td colspan=2><input type="button" id="findIdBtn" class="findBtn" value="찾기" onclick="find_id()"/>
							    <input type="button" class="findBtn" value="닫기" onClick="window.close()" class="cancel"/></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td id="finishId">  <!--  아이디 결과값 출력 -->		
				</td>	
			</tr>
		</table>
		
		
		<table id="pwFindForm" style="display:none;">
			<tr>
				<td>
					<table id="findFind">
						<tr>
							<td class="findText">아이디</td>
							<td><input type="text" name="ef_id" class="form-control" id="find_pwd_id" placeholder="아이디를 입력하세요" required autofocus></td>
						</tr>
						<tr>
							<td class="findText">연락처</td>
							<td><input type="text" name="ef_p_number" class="form-control" id="find_pwd_num" placeholder="연락처를 입력하세요" required autofocus></td>
						</tr>
						<tr>
							<td colspan=2><input type="button" id="findPwdBtn" class="findBtn" value="찾기" onclick="find_pwd()"/>
							    <input type="button" class="findBtn" value="닫기" onClick="window.close()" class="cancel"/></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td id="finishPw">  <!--  비밀번호 결과값 출력 -->				
				</td>
			</tr>
		</table>	
</div>
</body>
</html>