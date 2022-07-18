<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의사항 글 작성</title>
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
		$("#subBtnWrite").on("click", function() {	
			alert("aaaa");
		    var objTitle = document.getElementById("ef_i_title"); // 글제목 입력값 받기
		    var objContent = document.getElementById("ef_content"); // 글내용 입력값 받기		    		    
		    // 글제목 유효성 검사 영역
			if($("#ef_i_title").val() == '') {
				alert("글제목를 입력하십시오");
				$("#ef_i_title").focus();
				return false;
			}		
			// 글내용 유효성 검사 영역
			if($("#ef_content").val() == '') {
				alert("글내용을 입력하십시오");
				$("#ef_content").focus();
				return false;
			}						       		
			document.inqWriteForm.action = "/article/addWrite.do";
			document.inqWriteForm.submit();		
		}); // submit버튼				
	}); // function
	</script>
</head>
<body>
<div class="ownerField">
	<p/>
	<span class="ownerMenuTile">문의사항 글 작성</span><p/>
<div class="boardField">	
	<form name=inqWriteForm method="POST" enctype="multipart/form-data">
		<table class="writePostTable">
			<tr>
				<td><input type="hidden" maxlength="100" name="ef_id" value="${ef_id}" readonly/></td> 				
			</tr>
			<tr>
				<td class="title">글제목</td>
				<td colspan=6><input class="writeinqTitle" type="text" maxlength="100" name="ef_i_title" id="ef_i_title" placeholder="제목을 입력해 주세요."/></td>
			</tr>		
			<tr>
				<td rowspan=2 class="title">글내용</td>
				<td rowspan=2 colspan=6><textarea class="writeContent" rows="20" cols="85" maxlength="3000" name="ef_content" id="ef_content" placeholder="내용을 입력해 주세요."></textarea></td>			
				<td class="side">
					<span class="title">[미리보기]</span><br/>
					<div class="fileBox">
						<input id="uploadFile" type="file" name="ef_imageFileName" id="ef_imageFileName" onchange="readURL(this);" style="display:none;"/>
					</div>	
					<img id="preview"/><br/>
					<label for="uploadFile" class="bbsUpload">▶파일 첨부</label>
				</td>
			</tr>
			<tr>			
				<td class="side">
					<div class="submitBtnField">
						<br/>
						<input class="classModifyBtn" type="submit" id="subBtnWrite" value="문의사항 등록"/>
						<input class="returnListBtn" type="button" value="문의 목록보기" onClick="location.href='inquiry.do'"/>
					</div>	
				</td>
			</tr>
		</table>
	</form>
</div>	
</div>	
</body>
</html>
