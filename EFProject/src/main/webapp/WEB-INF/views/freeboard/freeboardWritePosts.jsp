<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판 글 작성하기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script> 
	// jQuery를 이용하여 이미지 파일을 첨부할 때 미리보기 기능을 구현한다. 		
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	$(document).ready(function() {

		
		   // 게시글 쓰기 제목이 공란인 경우
		   $("#addBtn").on("click", function() {
		      
		      if($("#freeBoard_title").val() == '') {
		         alert("제목을 입력하십시오");
		         $("#freeBoard_title").focus();
		         return false;
		      }
		      
		      document.articleForm.action="${contextPath}/freeboard/addPosts.do";
		      document.articleForm.submit();   
		      
		   });
		   
		});
	</script>
</head>
<body>
<div class="ownerField">
	<p/>
	<span class="ownerMenuTile">게시글 쓰기</span><p/>
<div class="boardField">
		<form name="articleForm" method="post" action="${contextPath}/freeboard/addPosts.do" enctype="multipart/form-data">
		<div style="display:none;"><input type="text" name="ef_id" value="${ef_id}">작성자 아이디</div>
		<div style="display:none;"><input type="text" name="freeBoard_notice" value="free">공지글 구분</div>

		<table class="writePostTable">
			<tr>
				<td class="title">제목 </td>
				<td><input class="writeTitle" type="text" maxlength="94" name="freeBoard_title" id="freeBoard_title" placeholder="제목을 입력해 주세요."/></td>
				<td class="side">공개 범위 : 
					<select name="freeBoard_publicScope">
		            <option value="open" selected>공개</option>
		            <option value="private">비밀</option>
		            </select>
	            </td>
			</tr>		
			<tr>
				<td rowspan=2 class="title">내용 </td>
				<td rowspan=2><textarea class="writeContent" name="freeBoard_content" rows="20" cols="100" maxlength="3000" placeholder="내용을 입력해 주세요."></textarea></td>
				<td class="side">
						<span class="title">[미리보기]</span><br/>
						<div class="fileBox">
						<input id="uploadFile" type="file" name="freeBoard_imageFileName" onchange="readURL(this);" style="display:none;"/>
						</div>	
						<img id="preview"/><br/>
						<label for="uploadFile" class="bbsUpload">▶파일 첨부</label>
				</td>
			</tr>	
			<tr>
				<td class="side">
						<div class="submitBtnField">
							<br/>
							<button id="addBtn" class="classModifyBtn">등록하기</button>
							<a href="${contextPath}/freeboard/freeboardMenu.do" class="returnListBtn">목록보기</a>
						</div>
				</td>
			</tr>
		</table>	
	</form>
</div>
</div>
</body>
</html>