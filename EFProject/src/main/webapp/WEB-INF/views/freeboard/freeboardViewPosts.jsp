<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
var id   = "<c:out value='${ef_id}'/>";
var no   = "<c:out value='${freeboard.freeBoard_no}'/>";
function fn_enable() {
	document.getElementById("freeBoard_content").disabled = false;
	document.getElementById("freeBoard_imageFileName").disabled = false;
	document.getElementById("publicScope2").style.display = "block";
	document.getElementById("publicScope1").style.display = "none";
	document.getElementById("div_btn_modify").style.display = "block";
	document.getElementById("div_btn").style.display = "none";
	document.getElementById("freeBoard_content").style.backgroundColor = "#dfefff";
	$('.bbsUpload').show();
}

function fn_enable1() {
	document.getElementById("freeBoard_content").disabled = true;
	document.getElementById("freeBoard_imageFileName").disabled = true;
	document.getElementById("publicScope2").style.display = "none";
	document.getElementById("publicScope1").style.display = "block";
	document.getElementById("div_btn_modify").style.display = "none";
	document.getElementById("div_btn").style.display = "block";
	document.getElementById("freeBoard_content").style.backgroundColor = "white";
	$('.bbsUpload').hide();
}
function readURL(input) {
	$('#originalImg').hide();
	$('#previewImg').show();
	
	if(input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#modImage').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

$(document).ready(function() {
	
	// 게시글 수정
	$("#modBtn").on("click", function() {
		if (confirm("게시글을 수정 하시겠습니까??") == true) {
			document.freeboard.action = "${contextPah}/freeboard/modPosts.do";
			document.freeboard.submit();
		} else {
			return;
		}
	});
	
	// 게시글 삭제
	$(".deleteFreeboard").on("click", function() {
		
		if (confirm("게시글을 삭제 하시겠습니까??") == true) {
			location = "${contextPath}/freeboard/freeBoardDeletePosts.do?freeBoard_no="+no;
		} else {
			return;
		}
	});
	
	// 댓글 삭제
	$(document).on('click', '#delReply', function(){
		var delReply = $(this);
		var row = delReply.parent().parent();
		var tr = row.children();
		
		var comment_no  = tr.eq(0).text();
		if (confirm("댓글을 삭제 하시겠습니까??") == true) {
			location = "${contextPath}/freeboard/deleteComments.do?freeBoard_commentNO="+comment_no
		} else {
			return;
		}
		
	});
	
	// 댓글 달기
	$("#sbBtn").on("click", function() {
		// 비로그인 인 경우
		if(id == '' || id == null) {
			alert("로그인이 필요합니다.");
			return false;
		} else {
			// 댓글 내용이 공란인 경우
			if($("#freeBoard_commentContent").val() == '') {
				alert("댓글 내용을 입력하십시오");
				return false;
			}
			document.comment.action = "${contextPah}/freeboard/addComments.do";
			document.comment.submit();
		}
	});
	
	// 답글
	$(".replyBtn").on("click", function() {
		
		var replyBtn = $(this);
		var row = replyBtn.parent().parent();
		var tr = row.children();
		
		var comment_no   = tr.eq(0).text();
		//alert(comment_no);
		
		var reply = '';
		var replyList = '';
	
		reply +='<div class="replyList"></div>'
				+'<form id="replyForm" name="replyForm" method="post" action="${contextPath}/freeboard/addReply.do">'
				+ '<div style="display:none;"><input type="text">'+comment_no+'</div><table id="reCommentTable"><tr><td id="coment">'
				+ '<div style="display:none;"><input type="text" id="ef_id" name="ef_id" value="' + id + '">작성자</div>'
				+ '<div style="display:none;"><input type="text" id="freeBoard_no" name="freeBoard_no" value="' + no + '">게시물번호</div>'
				+ '<div style="display:none;"><input type="text" id="freeBoard_commentParentsNO" name="freeBoard_commentParentsNO" value="' + comment_no + '">페런트넘버</div>'
				+ '<div style="display:none;"><input type="text" id="freeBoard_commentLevel" name="freeBoard_commentLevel" value="1"></div>'
				+ '<input type="text" id="freeBoard_commentContent'+comment_no+'" name="freeBoard_commentContent" placeholder="대-댓글 내용 입력" class="comentContent"></td><td id="etc">'
				+ '<button type="submit" id="replySbBtn" class="div_reBtn">등록</button></td></tr></table></form><p/>';
	          
	          
	          
	          
	          
	      $('.replyTest').hide();
	      $("#reply"+comment_no).html(reply);
	      $('#reply'+comment_no).show();

			$.ajax({
				type:		"post",
				url:		"${contextPath}/freeboard/replyList.do",
				dataType:	"json",
				async : 	false,
				data:		{freeBoard_commentParentsNO: comment_no},
				success:	function(data) {
					
					if(data.length>=1){
				replyList = '<table class="reReplyListTable"><tr>'+
	            			'<th class="reName">작성자</th>'+
	            			'<th class="reContent">내용</th>'+
	            			'<th class="reDate" colspan=3>작성날짜</th>'+
	            			'</tr>';
	            
			            $(data).each(function(r, item){
			            if(id == data[r].ef_id) {
			           		replyList += '<tr><td>'+ data[r].ef_id + '</td>'+
			            			 '<td class="reConLeft">'+ data[r].freeBoard_commentContent + '</td>'+
			            			 '<td>'+ data[r].freeBoard_commentWriteDate + '</td>'+
			            			 '<td>'+'<a href="${contextPath}/freeboard/deleteComments.do?freeBoard_commentNO='+ data[r].freeBoard_commentNO + '"' +
	            					 ' onclick="return confirm(\'' + '대-댓글을 삭제 하시겠습니까?' + '\');" class="inTableLinkText2">' + '삭제' + '</a>'+
	            					 '</td></tr>';
						
			            } else {
			            	replyList += '<tr><td>'+ data[r].ef_id + '</td>'+
	            			 '<td class="reConLeft">'+ data[r].freeBoard_commentContent + '</td>'+
	            			 '<td>'+ data[r].freeBoard_commentWriteDate + '</td>'+
	            			 '<td>'+' '+'</td></tr>';
				
			            }
			            });
			           
				}
					else{
						replyList+= '<table class="reReplyListTable"><tr>'+
            			'<th class="reName">작성자</th>'+
            			'<th class="reContent">내용</th>'+
            			'<th class="reDate" colspan=2>작성날짜</th>'+
            			'</tr><tr><td colspan=4 style="background:white;">작성된 대-댓글 목록이 없습니다.</td>';
					}
				       $(".replyList").html(replyList);
				}
			});
			
	

	});
	
	// 답글
	$(document).on('click', '#replySbBtn', function(){

		var replyBtn = $(this);
		var row = replyBtn.parent().parent().parent().parent().parent();
		var tr = row.children();
		
		var comment_no   = tr.eq(0).text();
		
		if(id == '' || id == null) {
			alert("로그인이 필요합니다.");
			return false;
		}	
		
		// 답글 내용이 공란인 경우
		if($("#freeBoard_commentContent"+comment_no).val() == '') {
			alert("답글 내용을 입력하십시오");
			return false;
		}
		
		
	});
	
});

</script>
</head>
<body>
<div class="ownerField">
	<p/>
	<span class="ownerMenuTile">게시글 보기</span><p/>
<div class="boardField">
<form name="freeboard" method="post" enctype="multipart/form-data">
			
			
		<table class="boardViewTable">
			<tr>
				<td class="tableName">
					<input type="hidden" name="freeBoard_no" value="${freeboard.freeBoard_no}"/>	
					<span>작성자</span>
				</td>
				<td>
					<span>${freeboard.ef_id}</span>
				</td>
				<td class="tableName">
					<span>작성일자</span>
				</td>
				<td>
					<span>${freeboard.freeBoard_writeDate}</span>
				</td>
				<td class="viewSide">
					<c:if test="${freeboard.freeBoard_publicScope == 'open'}">
						<div id="publicScope1">공개 여부 : <input type="text" value="공개" style="color:white; border:none; background: none; width:54px; height:15px; font-size:12px;" disabled/></div>
					</c:if>
					<c:if test="${freeboard.freeBoard_publicScope == 'private'}">
						<div id="publicScope1">공개 여부 : <input type="text" value="비밀" style="color:white; border:none; background: none; width:54px; height:15px; font-size:12px;" disabled/></div>
					</c:if>
					<div id="publicScope2" style="display:none;">
						공개 여부 : <select name="freeBoard_publicScope" style="color:black; width:55px; height:18px; font-size:12px;">
		            				<option value="open" selected>공개</option>
		            				<option value="private">비밀</option>
		            				</select>
					</div>
				</td>	
			</tr>
			<tr>
				<td class="tableName">		
					<span>제목</span>
				</td>
				<td colspan=3>
			    <span>${freeboard.freeBoard_title}</span>
			    </td>
			    <td rowspan=2 class="viewSide">
			    <p/><br/>
			    	<div id="originalImg">
						<c:if test="${not empty freeboard.freeBoard_imageFileName && freeboard.freeBoard_imageFileName != 'null'}">		
							<input type="hidden" name="originalImageFileName" value="${freeboard.freeBoard_imageFileName}"/>
							<!-- 외부 경로 이미지 불러오기 -->
							<a href="${contextPath}/img/${freeboard.freeBoard_no}/${freeboard.freeBoard_imageFileName}" target="_blank" class="imgLinkText">[확대보기]</a>
							<img id="originalImage" usemap="#test" width="120" height="120" src="${contextPath}/img/${freeboard.freeBoard_no}/${freeboard.freeBoard_imageFileName}"/><br/>
							
							<map name="test">
							<area shape="default" coords="10,8,150,292" href="${contextPath}/img/${freeboard.freeBoard_no}/${freeboard.freeBoard_imageFileName}" target="_blank"/>
							</map>
						</c:if>
					</div>
					<div id="previewImg" style="display:none;">	
					<span class="imgLinkText">[미리보기]</span>
						<img id="modImage" width="120" height="120"/>
					</div>
					<input type="file" name="freeBoard_imageFileName" id="freeBoard_imageFileName" onchange="readURL(this);" style="display:none;" disabled/>
					<label for="freeBoard_imageFileName" class="bbsUpload" style="display:none;">▶파일 첨부</label>
			    </td>
		    </tr>
		    <tr>
			    <td class="tableName">
					<span>글 내용</span>
				</td>
				<td colspan=3>
					<input type="hidden" name="freeBoard_notice" value="${freeboard.freeBoard_notice}"/>
				<div>
					<textarea rows="16" cols="80" name="freeBoard_content" id="freeBoard_content" class="writeContent" disabled>${freeboard.freeBoard_content}</textarea>		
				</div>
				</td>
			</tr>
			</table>
			<p/>
			<div id="div_btn" class="div_btnMenu">
				<input class="div_btn" type="button" value="목록" onclick="location.href='${contextPath}/freeboard/freeboardMenu.do'"/>
				<!-- 내가 작성한 글인 경우 수정하기를 보여준다. -->
				<c:if test="${ef_id == freeboard.ef_id}">
					<input class="div_btn" type="button" value="수정" 	 onclick="fn_enable(this.form)"/>
					<input class="div_btn deleteFreeboard" type="button" value="삭제" 	 onclick="#"/>
				</c:if>
			</div>	
			<div id="div_btn_modify" class="div_btnMenu" style="display:none;">
				<input class="div_btn" type="button" id="modBtn" value="수정완료"/>
				<input class="div_btn" type="button" value="취소"			onclick="fn_enable1(this.form)"/>
			</div>
</form>
<p/>

<!--  댓글 작성하기 영역  -->
	<form id="comment" name="comment" method="Post">
		<table class="commentTable">
		<tr>
			<td class="coment">
				<div style="display:none;"><input type="text" id="ef_id" name="ef_id" value="${ef_id}">작성자</div>
				<div style="display:none;"><input type="text" id="freeBoard_no" name="freeBoard_no" value="${freeboard.freeBoard_no}">게시물번호</div>
				<div style="display:none;"><input type="text" id="freeBoard_commentLevel" name="freeBoard_commentLevel" value="0"></div>
				<input type="text" id="freeBoard_commentContent" name="freeBoard_commentContent" placeholder="댓글 내용 입력" class="comentContent">
			</td>
			<td	class="etc">	          
				<button type="button" id="sbBtn" class="div_btn">댓글달기</button>
			</td>
	    </table>
	</form>
<p/>


<!-- 댓글 리스트 영역 -->
<div>
<table class="replyListTable">
<c:if test="${not empty freeBoardViewCommentsList}">
	<tr>		
		<th style="display:none;">게시글 번호</th>
		<th style="display:none;">댓글/답글</th>
		<th class="reName">작성자</th>
		<th class="reContent">내용</th>
		<th colspan=2 class="reDate">작성날짜</th>	
	</tr>	
	<c:forEach var="freeBoardViewCommentsList" items="${freeBoardViewCommentsList}">
		<c:if test="${freeBoardViewCommentsList.freeBoard_commentLevel == 0}">		
			<tr>		
				<td style="display:none;">${freeBoardViewCommentsList.freeBoard_commentNO}</td>
				<td style="display:none;">${freeBoardViewCommentsList.freeBoard_commentLevel}</td>
				<td>${freeBoardViewCommentsList.ef_id}</td>
				<c:if test="${freeBoardViewCommentsList.freeBoard_reCommentCount==0}">
				<td class="reConLeft"><span class="replyBtn boardLinkText">${freeBoardViewCommentsList.freeBoard_commentContent}</span></td>
				</c:if>
				<c:if test="${freeBoardViewCommentsList.freeBoard_reCommentCount!=0}">
				<td class="reConLeft"><span class="replyBtn boardLinkText">${freeBoardViewCommentsList.freeBoard_commentContent}<span class="boardLinkText2">[${freeBoardViewCommentsList.freeBoard_reCommentCount}]</span></span></td>
				</c:if>
				<td>${freeBoardViewCommentsList.freeBoard_commentWriteDate.substring(0,10)}</td>
				<c:if test="${freeBoardViewCommentsList.ef_id == ef_id}">
					<td><span id="delReply" class="inTableLinkText2">삭제</span></td>
				</c:if>
				<c:if test="${freeBoardViewCommentsList.ef_id != ef_id}">
					<td><span class="inTableLinkText2"></span></td>
				</c:if>
			</tr>
			<tr>
				<td colspan=5>
				<div id="reply${freeBoardViewCommentsList.freeBoard_commentNO}" class="replyTest" style="display:none;">
				</div>
				</td>
			</tr>
		</c:if>			
	</c:forEach>
</c:if>
</table>
</div>
</div>
</div>
</body>
</html>