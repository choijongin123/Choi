<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="article"		value="${article}"/>
<c:set var="replyList"		value="${replyList}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	/* 수정하기 버튼을 눌렀을 겨우 텍스트 박스를 활성화시킨다.	*/
	function update_button() {
		document.getElementById("tr_btn").style.display = "none";
		document.getElementById("tr_btn_modify").style.display = "block";
		document.getElementById("contentsField").disabled = false;
		document.getElementById("ef_imageFileName").disabled = false;
	}	
	/* 삭제하기 버튼을 누르면 ef_i_no를 전달해서 함수를 실행시킨다.	*/
	function remove_article(url, ef_i_no) {		
		/* 자바스크립트를 이용하여 동적으로 <form>태그를 만든다.	*/
		var form = document.createElement("form");
		form.setAttribute("method",	"post");
		form.setAttribute("action",	url);
		/* 콘트롤러에 넘겨줄 게시글 번호를 <input>태그를 생성하여 넣는다.	*/
		var ef_i_noInput = document.createElement("input");
		ef_i_noInput.setAttribute("type",		"hidden");
		ef_i_noInput.setAttribute("name",		"ef_i_no");
		ef_i_noInput.setAttribute("value",	ef_i_no);		
		alert("ef_i_no ===> " + ef_i_no);
		/* 동적으로 생성된 <input> 태그를 동적으로 생성한 <form>태그에 append한다.	*/
		form.appendChild(ef_i_noInput);
		document.body.appendChild(form);
		form.submit();
	}	
	/* 문의목록으로 뒤돌아가기 */
	function backToList(obj) {
		obj.action="${contextPath}/article/inquiry.do";
		obj.submit();
	}	
	/* 수정반영하기 버튼을 누르면 컨트롤러에 수정된 데이터를 전송한다.	*/
	$(document).ready(function() {
		$("#subBtnInquiry").on("click", function() {							
			// 내용 유효성 검사 영역
			if($("#ef_content").val() == '') {
				$("#ef_content").focus();
				return false;
			}		
			document.frmUpdate.action = "${contextPath}/article/addUpdate.do";
			document.frmUpdate.submit();		
		}); // submit버튼			
	}); // function	
	/* 수정반영하기의 취소버튼을 눌렀을 겨우 텍스트 박스를 비활성화시킨다.*/
	function reset_button(obj) {
		document.getElementById("contentsField").disabled = true;
		document.getElementById("ef_imageFileName").disabled = true;
		document.getElementById("tr_btn_modify").style.display = "none";
		document.getElementById("tr_btn").style.display = "block";
	}	
	// jQuery를 이용하여 이미지 파일을 첨부할 때 미리보기 기능을 구현한다. 
	function readRUL(input) {
		$('#originalImg').hide();
		$('#previewImg').show();		
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#boardLinkText').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}	
/*-------------------------------------------------------------------------------------------------------*/	
	/* 답글반영하기 버튼을 누르면 컨트롤러에 수정된 데이터를 전송한다.	*/	
	$(document).ready(function() {			
			$("#subBtnReply").on("click", function() {				  
			    var objContent = document.getElementById("ef_r_content"); // 답글내용 입력값 받기			    		    				
				// 답글내용 유효성 검사 영역
				if($("#ef_r_content").val() == '') {
					alert("답글내용을 입력하십시오");
					$("#ef_r_content").focus();
					return false;
				}						       					
			}); // submit버튼					
		}); // function	
		/* 삭제하기 버튼을 누르면 ef_i_no를 전달해서 함수를 실행시킨다.	*/
		function remove_reply(url, ef_i_no) {		
			/* 자바스크립트를 이용하여 동적으로 <form>태그를 만든다.	*/
			var form = document.createElement("form");
			form.setAttribute("method",	"post");
			form.setAttribute("action",	url);
			/* 콘트롤러에 넘겨줄 게시글 번호를 <input>태그를 생성하여 넣는다.	*/
			var ef_i_noInput = document.createElement("input");
			ef_i_noInput.setAttribute("type",		"hidden");
			ef_i_noInput.setAttribute("name",		"ef_i_no");
			ef_i_noInput.setAttribute("value",	ef_i_no);		
			alert("ef_i_no ===> " + ef_i_no);
			/* 동적으로 생성된 <input> 태그를 동적으로 생성한 <form>태그에 append한다.	*/
			form.appendChild(ef_i_noInput);
			document.body.appendChild(form);
			form.submit();
		}	
	</script>
</head>
<body>
<div class="ownerField">
	<p/>
	<span class="ownerMenuTile">문의하기 현황</span><p/>
<div class="boardField">	
<form name="frmUpdate" method="post" enctype="multipart/form-data">
	<table class="boardViewTable">
		<tr>
			<td class="tableName">
				<input type="hidden" name="ef_i_no" value="${article.ef_i_no}"/>	
				<input type="hidden" name="ef_i_title" value="${article.ef_i_title}"/>
				<span>작성자</span>
			</td>
			<td>
				<span>${article.ef_id}</span>
			</td>
			<td class="tableName">
				<span>작성일자</span>
			</td>
			<td>
				<span>${article.ef_writeDate}</span>
			</td>
		</tr>
		<tr>
			<td class="tableName">		
				<span>제목</span>
			</td>
			<td colspan=3>
		    <span>${article.ef_i_title}</span>
		    </td>
		</tr>
		<tr>
		    <td class="tableName">
				<span>글 내용</span>
			</td>
			<td colspan=3>
			<textarea name="ef_content" id="contentsField" class="writeContent" rows="20" cols="85" maxlength="3000" disabled>${article.ef_content}</textarea>
		<div id="originalImg">		
			<c:if test="${not empty article.ef_imageFileName && article.ef_imageFileName != 'null'}">		
			<%-- 이미지를 수정하는 것에 대비해서 원래 이미지 파일의 이름을 <hidden>태그에 저장해 놓는다. --%>
			<input type="hidden" name="origimageFileName" value="${article.ef_imageFileName}"/>		
			<!-- 외부 경로 이미지 불러오기 -->
			<div>[확대보기]</div>
			<img id="originalImage" usemap="#test" width="120" height="120" src="${contextPath}/articleimg/${article.ef_i_no}/${article.ef_imageFileName}"/>	
				<map name="test">
					<area shape="default" coords="10,8,150,292"
							href="${contextPath}/articleimg/${article.ef_i_no}/${article.ef_imageFileName}" target="_blank"/>
				</map>					
			</c:if>
		</div>
		<div id="previewImg" style="display:none;">	
			<img id="boardLinkText" width="120" height="120"/>
		</div>
			<input type="file" name="ef_imageFileName" id="ef_imageFileName" onchange="readRUL(this);" style="display:none;" disabled/>
			<label for="ef_imageFileName" class="bbsUpload">▶파일 첨부</label>	
		</tr>		
	</table>	
	<p/>	
	<div id="tr_btn" class="div_btnMenu">
			<c:if test="${article.ef_id == ef_id}">
			<input class="div_btn" type="button" value="수정하기" 	 onclick="update_button(frmUpdate)"/>				
			<input class="div_btn deleteFreeboard" type="button" value="삭제하기" 	 onclick="remove_article('${contextPath}/article/removeArticle.do', ${article.ef_i_no})"/>
			</c:if>	
			<input class="div_btn" type="button" value="문의목록" onclick="backToList(location.href='inquiry.do')"/>
	</div>							
	<div id="tr_btn_modify" class="div_btnMenu" style="display:none;">	
			<input class="div_btn" type="submit" id="subBtnInquiry" value="수정반영"/>				
			<input class="div_btn" type="button" value="취소"	 onclick="reset_button(frmUpdate)"/>								
	</div>
</form>
<p/>
<span class="ownerMenuTile">답글창</span><p/>
<div class="boardField">	
<form method="post" action="${contextPath}/article/addReply.do">
	<table class="boardViewTable">
		<tr>			
			<td class="tableName">
				<input type="hidden" name="ef_i_no" value="${article.ef_i_no}"/>	
				<span>글쓴이</span>
			</td>
			<td>
				<input type="text" maxlength="60" name="ef_id" value="${ef_id}" readonly/>
			</td>			
		</tr>
		<tr>
			<td colspan=4>
				<textarea class="writeReplyContent" name="ef_r_content" id="ef_r_content" maxlength="3000" placeholder="내용을 입력해 주세요."></textarea>
			</td>
		</tr>		
	</table>	
	<input class="div_btn" type="submit" id="subBtnReply" value="답글반영"/>
</form>
	<p/>
	<table class="replyListTable">
		<tr>
			<th class="reName">글쓴이</th>
			<th class="reContent">내용</th>
			<th class="reDate">작성날짜</th>
			<th class="delReply">삭제</th>
		</tr>
		<c:choose>
			<%-- 게시글 데이터가 한 건도 없으면 --%>
			<c:when test="${empty replyList}">
		<tr>
			<th colspan=4 style="font-size:10pt;">등록된 글이 없습니다.</th>
		</tr>
			</c:when>
			<%-- 게시글 데이터가 있으면 --%>
			<c:when test="${!empty replyList}">
				<c:forEach var="replyList"  items="${replyList}">
					<tr>
						<td>${replyList.ef_id}</td>
						<td class="reConLeft">${replyList.ef_r_content}</td>																			
						<td><fmt:formatDate value="${replyList.ef_r_date}" pattern="yyyy-MM-dd"/></td>
						<td class="classModifyBtn">
							<c:if test="${replyList.ef_id==ef_id}">
								<a href="${contextPath}/article/deleteReply.do?ef_comment=${replyList.ef_comment}&&ef_i_no=${article.ef_i_no}" 
								onclick="return confirm('답글을 삭제 하시겠습니까?');">삭제</a>
							</c:if>
							<c:if test="${replyList.ef_id!=ef_id}">
								--
							</c:if>							
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</div>
</div>
</div>
</body>
</html>
