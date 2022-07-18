<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <title>자유게시판</title>
     
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<script>
// 로그인 유효성
var id   = "<c:out value='${ef_id}'/>";
$(document).ready(function() {
	$(".addPosts").on("click", function() {
		// alert(id);
		if(id == '' || id == null) {
			alert("로그인이 필요합니다.");
			return false;
		} else {
			location.href = "${contextPath}/freeboard/freeboardWritePosts.do"
		}
		
	});	
});
</script>
<script>
$(document).ready(function() {			// 현재 오픈되어있는 페이지 넘버를, 페이지 리스트에 색상 변화로 표기하기 위한 기능.
	
var curUrl = window.location.search.substring(6);		// 현재 URL의 GET 주소를 6번째 문자부터 받아온다.
var pairs = curUrl.split("&");							// 그렇게 받아온 문자열을 & 기준하여 배열로 나누어준다.

if(pairs[0]==''){										// 그래서 pairs[0]은 배열의 앞에 해당하므로 열린 페이지 주소를 받아오게 된다.
	$("#curP1").css("color","gold")						// 이 때, 최초오픈 default 경우의 수를 고려하여 공란인 때에는 1page로 간주한다.
}
else {													// 그 외 경우에는 오픈된 페이지와 일치하는 페이지 리스트 넘버의 글자색을 황금색으로 처리한다.
	$("#curP"+pairs[0]).css("color","gold")
}
});
</script>
<body>
<div class="ownerField">
	<div>
	<p/>
		<span class="companyTitle">자유게시판</span><br/>
		<span class="addPosts">게시글 작성</span>
		<table class="freeBoardTable">
			<tr>
				<th class="tableNo">번호</th>
				<th class="tableTitle">글 제목</th>
				<th class="tableName">작성자</th>
				<th class="tableDate">작성날짜</th>			
			</tr>
			<c:set var="num" value="${pageMaker.totalCount - (pageMaker.cri.page - 1) * (pageMaker.cri.perPageNum)}"></c:set>
			<c:forEach var="freeBoardList" items="${freeBoardList}">
				<tr>
					<c:if test="${isLogOn != true}">
						<td>${num}</td>
						<c:if test="${freeBoardList.freeBoard_publicScope == 'open'}">
							<c:if test="${freeBoardList.freeBoard_commentCount == 0}">
								<td class="bbsTitle"><a href="${contextPath}/freeboard/freeBoardViewPosts.do?freeBoard_no=${freeBoardList.freeBoard_no}" class="boardLinkText">${freeBoardList.freeBoard_title}</a></td>
							</c:if>
							<c:if test="${freeBoardList.freeBoard_commentCount != 0}">
								<td class="bbsTitle"><a href="${contextPath}/freeboard/freeBoardViewPosts.do?freeBoard_no=${freeBoardList.freeBoard_no}" class="boardLinkText">${freeBoardList.freeBoard_title}<span class="boardLinkText2">[${freeBoardList.freeBoard_commentCount}]</span></a></td>
							</c:if>
						</c:if>
						<c:if test="${freeBoardList.freeBoard_publicScope == 'private'}">
							<c:if test="${freeBoardList.freeBoard_commentCount == 0}">
								<td class="bbsTitle bimilTitle">[비공개]<span class="bimilTitle"> ${freeBoardList.freeBoard_title}</span></td>
							</c:if>
							<c:if test="${freeBoardList.freeBoard_commentCount != 0}">
								<td class="bbsTitle bimilTitle">[비공개]<span class="bimilTitle"> ${freeBoardList.freeBoard_title}<span class="boardLinkText2">[${freeBoardList.freeBoard_commentCount}]</span></span></td>
							</c:if>
						</c:if>
						<td>${freeBoardList.ef_id}</td>
						<td>${freeBoardList.freeBoard_writeDate}</td>
					</c:if>
					<c:if test="${isLogOn == true}">
						<c:if test="${ef_class == '11'}">
							<td>${num}</td>
							<c:if test="${freeBoardList.freeBoard_commentCount == 0}">
								<td class="bbsTitle"><a href="${contextPath}/freeboard/freeBoardViewPosts.do?freeBoard_no=${freeBoardList.freeBoard_no}" class="boardLinkText">${freeBoardList.freeBoard_title}</a></td>
							</c:if>
							<c:if test="${freeBoardList.freeBoard_commentCount != 0}">
								<td class="bbsTitle"><a href="${contextPath}/freeboard/freeBoardViewPosts.do?freeBoard_no=${freeBoardList.freeBoard_no}" class="boardLinkText">${freeBoardList.freeBoard_title}<span class="boardLinkText2">[${freeBoardList.freeBoard_commentCount}]</span></a></td>
							</c:if>
							<td>${freeBoardList.ef_id}</td>
							<td>${freeBoardList.freeBoard_writeDate}</td>
						</c:if>
						<c:if test="${ef_class != '11'}">
							<td>${num}</td>
							<c:if test="${freeBoardList.freeBoard_publicScope == 'open'}">
								<c:if test="${freeBoardList.freeBoard_commentCount == 0}">
									<td class="bbsTitle"><a href="${contextPath}/freeboard/freeBoardViewPosts.do?freeBoard_no=${freeBoardList.freeBoard_no}" class="boardLinkText">${freeBoardList.freeBoard_title}</a></td>
								</c:if>
								<c:if test="${freeBoardList.freeBoard_commentCount != 0}">
									<td class="bbsTitle"><a href="${contextPath}/freeboard/freeBoardViewPosts.do?freeBoard_no=${freeBoardList.freeBoard_no}" class="boardLinkText">${freeBoardList.freeBoard_title}<span class="boardLinkText2">[${freeBoardList.freeBoard_commentCount}]</span></a></td>
								</c:if>
							</c:if>
							<c:if test="${freeBoardList.ef_id == ef_id && freeBoardList.freeBoard_publicScope == 'private'}">
								<c:if test="${freeBoardList.freeBoard_commentCount == 0}">
									<td class="bbsTitle"><a href="${contextPath}/freeboard/freeBoardViewPosts.do?freeBoard_no=${freeBoardList.freeBoard_no}" class="boardLinkText">${freeBoardList.freeBoard_title}</a></td>
								</c:if>
								<c:if test="${freeBoardList.freeBoard_commentCount != 0}">
									<td class="bbsTitle"><a href="${contextPath}/freeboard/freeBoardViewPosts.do?freeBoard_no=${freeBoardList.freeBoard_no}" class="boardLinkText">${freeBoardList.freeBoard_title}<span class="boardLinkText2">[${freeBoardList.freeBoard_commentCount}]</span></a></td>
								</c:if>
							</c:if>
							<c:if test="${freeBoardList.ef_id != ef_id && freeBoardList.freeBoard_publicScope == 'private'}">
								<c:if test="${freeBoardList.freeBoard_commentCount == 0}">
									<td class="bbsTitle bimilTitle">[비공개]<span class="bimilTitle"> ${freeBoardList.freeBoard_title}</span></td>
								</c:if>
								<c:if test="${freeBoardList.freeBoard_commentCount != 0}">
									<td class="bbsTitle bimilTitle">[비공개]<span class="bimilTitle"> ${freeBoardList.freeBoard_title}<span class="boardLinkText2">[${freeBoardList.freeBoard_commentCount}]</span></span></td>
								</c:if>
							</c:if>
							<td>${freeBoardList.ef_id}</td>
							<td>${freeBoardList.freeBoard_writeDate}</td>
						</c:if>
					</c:if>
				</tr>
			<c:set var="num" value="${num-1}"></c:set>	
			</c:forEach>
		</table>
		  <ul class="pageCntNum">
		    <c:if test="${pageMaker.prev}">
		    	<li><a href="${contextPath}/freeboard/freeboardMenu.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">◀</a></li>
		    </c:if> 
		    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
		    	<li><a href="${contextPath}/freeboard/freeboardMenu.do${pageMaker.makeQuery(idx)}" id="curP${idx}">${idx}</a></li>
		    </c:forEach>
		    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
		    	<li><a href="${contextPath}/freeboard/freeboardMenu.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">▶</a></li>
		    </c:if> 
		  </ul>
	</div>
</div>
</body>
</html>