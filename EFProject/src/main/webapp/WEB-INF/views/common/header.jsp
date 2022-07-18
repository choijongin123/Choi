<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8");%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>header</title>
	<link href="${contextPath}/css/ezen.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script>
// 로그인 시도시 DB에 저장된 ef_class로 회원 등급을 판단하여 보여지게 될 메뉴를 특정하는 기능.
var memberChk = ${ef_class};
$(function () {
	if(memberChk=='11'){
		$('#hostMenu').show();
	}else if(memberChk=='12'){
		$('#trainerMenu').show();
	}else{
		$('#memberMenu').show();
	}
});
</script>
<body>
	<table id="topTable">
		<tr>
			<td>
				<a href="${contextPath}/index.do"><img src="${contextPath}/resources/images/mainlogo.png" width="170" height="75" id="mainLogo"></a>
			</td>
			<td>
				<a href="${contextPath}/companyProfile.do" class="topMenuBtn">회사소개</a>
			</td>
			<td>
				<a href="${contextPath}/ProgramIntroduction.do" class="topMenuBtn">프로그램 소개</a>
			</td>
			<td>
				<a href="${contextPath}/InformationUse.do" class="topMenuBtn">이용안내</a>
			</td>
			<td>
				<a href="${contextPath}/article/inquiry.do" class="topMenuBtn">문의하기</a>
			</td>
			<td>
				<a href="${contextPath}/freeboard/freeboardMenu.do" class="topMenuBtn">자유게시판</a>
			</td>
			<td colspan=2>
				<div id="loginField">
					<c:choose>
						<c:when test="${isLogOn == true && ef_id != null}"> 
							<span class="textColorGold">${ef_name}님</span><span class="loginInfoText"> 환영합니다.</span><p/>
							<div id="hostMenu" style="display:none;">
								<a href="${contextPath}/owner/approval.do"><input type="button" value="업체관리" class="memberBtn2"/></a>
								<a href="${contextPath}/user/logout.do" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class="memberBtn"/></a>
								<a href="${contextPath}/user/myPage.do"><input type="button" value="MyPage" class="memberBtn"/></a>
							</div>
							<div id="trainerMenu" style="display:none;">
								<a href="${contextPath}/trainer/trainerMenu.do"><input type="button" value="강의관리" class="memberBtn2"/></a>
								<a href="${contextPath}/user/logout.do" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class="memberBtn"/></a>
								<a href="${contextPath}/user/myPage.do"><input type="button" value="MyPage" class="memberBtn"/></a>
							</div>
							<div id="memberMenu" style="display:none;">
								<a href="${contextPath}/member/memberMenu.do"><input type="button" value="예약관리" class="memberBtn2"/></a>
								<a href="${contextPath}/user/logout.do" onclick="return confirm('정말 로그아웃 하시겠습니까?');"><input type="button" value="로그아웃" class="memberBtn"/></a>
								<a href="${contextPath}/user/myPage.do"><input type="button" value="MyPage" class="memberBtn"/></a>
							</div>
						</c:when>
						<c:otherwise>
							<form method="post" action="${contextPath}/user/login.do">
								<span class="loginText">아이디</span>
								<input class="loginInput" type="text" name="ef_id"/>
								<input class="loginBtn" type="submit" value="로그인" class="button"><br/>					
								<span class="loginText">비밀번호</span>
								<input class="loginInput" type="password" name="ef_pwd"/>
								<input class="loginBtn" type="reset" value="취　소" class="button"><br/>	
							</form>	
			  				<a href="${contextPath}/user/userClassForm.do" class="linkText">[회원가입]</a>
 							<a href="${contextPath}/user/find.do" class="linkText"
 									onclick="window.open(this.href,'_blank','width=500,height=400, scrollbars=no');return false;">[아이디/비밀번호 찾기]</a>
						</c:otherwise>	
					</c:choose>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>