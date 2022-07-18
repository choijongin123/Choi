<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
<div class="Field">
	<span class="joinTitle">회원 가입 하기</span>
	<table id="classTable">
		<tr>
			<td><a href="${contextPath}/user/userForm.do?ef_class=01"><button type="button" class="imgBtn"><img class="img" src="../css/images/owner.png"></button></a></td> <!-- 업체 계정 만들기  -->
			<td><a href="${contextPath}/user/userForm.do?ef_class=02"><button type="button" class="imgBtn"><img class="img"src="../css/images/trainer.png"></button></a></td> <!-- 강사 계정 만들기  -->
			<td><a href="${contextPath}/user/userForm.do?ef_class=03"><button type="button" class="imgBtn"><img class="img"src="../css/images/member.png"></button></a></td> <!-- 개인 회원 계정 만들기  -->
		</tr>			
	</table>
</div>	
</body>
</html>