<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" 	value="${pageContext.request.contextPath}"/>
<%-- HashMpa으로 저장해서 넘어온 값들의 이름이 길기때문에 <c:set>태그를 이용해서 
	 각 값들을 짧은 변수이름으로 저장한다. --%>	 	 
<c:set var="article"		value="${article}"/>	 
<c:set var="totArticles"	value="${totArticles}"/>
<c:set var="section"		value="${section}"/>
<c:set var="pageNum"		value="${pageNum}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의 목록</title>
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>		
	<style type="text/css">
			li {list-style: none; float: left; padding: 6px;}
	</style>
	<style>
	.no-uline	{	text-decoration:	none;	}
	/* 선택된 페이지 번호를 빨간색으로 표시한다.	*/
	.sel-page	{	text-decoration:	none;	color:		red;	}
	.writing		{	text-decoration:	none;	}
	</style>	
</head>
<script>
$(document).ready(function() {
	var id = "${ef_id}";
	/* 로그인 후 글쓰기 접속허용 */
	$(".addPosts").on("click", function() {					
		if(id == ''||id == null) {
			alert("로그인이 필요합니다.");
			return false;		
		}
		else {
			location.href = "${contextPath}/article/inqWrite.do"
		}	
	});
	$(".private").on("click", function() {					
		/* 로그인 후 문의하기 접속허용 */
		if(id == ''||id == null) {
			alert("로그인 하십시오.");
			return false;		
		}
	});
	$(".bimilTitle").on("click", function() {					
		/* 오너가 아닌 경우 자신의 아이디가 아니면 접속 미허용 */
		alert("본인 작성자의 제목를 선택해주세요.");
		return false;		
	});
});
</script>
<body> 
<div class="ownerField">
	<p/>
	<span class="companyTitle">문의 목록</span><br/>
	<span class="addPosts">문의사항 글 작성</span>
	<table class="freeBoardTable">
		<tr>
			<th class="tableNo">글번호</th>
			<th class="tableTitle">제 목</th>
			<th class="tableName">작성자</th>
			<th class="tableDate">작성날짜</th>
		</tr>			
		<c:set var="num" value="${pageMaker.totalCount - (pageMaker.cri.page - 1) * (pageMaker.cri.perPageNum)}"></c:set>
			<c:forEach var="article"  items="${inquiryList}">
				<tr>
					<c:if test="${isLogOn != true}">			
						<td width="5%">${num}</td>
						<td width="10%"><a class="private" href="${contextPath}/article/inqStatus.do?ef_i_no=${article.ef_i_no}">${article.ef_i_title}</a></td>																									
						<td width="10%">${article.ef_id}</td>
						<td width="10%"><fmt:formatDate value="${article.ef_writeDate}" pattern="yyyy-MM-dd"/></td>
					</c:if>
					<c:if test="${isLogOn == true}">
						<c:if test="${ef_class == '11'}">	
							<td width="5%">${num}</td>
							<td width="10%"><a href="${contextPath}/article/inqStatus.do?ef_i_no=${article.ef_i_no}">${article.ef_i_title}</a></td>																									
							<td width="10%">${article.ef_id}</td>
							<td width="10%"><fmt:formatDate value="${article.ef_writeDate}" pattern="yyyy-MM-dd"/></td>
						</c:if>
						<c:if test="${ef_class != '11'}">
							<td width="5%">${num}</td>
							<c:if test="${article.ef_id == ef_id}">
								<td width="10%"><a href="${contextPath}/article/inqStatus.do?ef_i_no=${article.ef_i_no}">${article.ef_i_title}</a></td>									
							</c:if>
							<c:if test="${article.ef_id != ef_id}">
								<td width="10%"><span class="bimilTitle">${article.ef_i_title}</span></td>
							</c:if>												
							<td width="10%">${article.ef_id}</td>
							<td width="10%"><fmt:formatDate value="${article.ef_writeDate}" pattern="yyyy-MM-dd"/></td>
						</c:if>
					</c:if>
				</tr>
		<c:set var="num" value="${num-1}"></c:set>	
			</c:forEach>
	</table>
	<ul class="pageCntNum">
	    <c:if test="${pageMaker.prev}">
	    	<li><a href="${contextPath}/article/inquiry.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">◀</a></li>
	    </c:if> 
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	    	<li><a href="${contextPath}/article/inquiry.do${pageMaker.makeQuery(idx)}" id="curP${idx}">${idx}</a></li>
	    </c:forEach>
	    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	    	<li><a href="${contextPath}/article/inquiry.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">▶</a></li>
	    </c:if> 
	 </ul>
	
</div>	
</body>
</html>

<%
/*	varStatus="상태용 변수"
varStatus="articleNum"
${articleNum.current}	현재 for문의 해당하는 번호
${articleNum.index}		0 부터 시작하는 순서
${articleNum.count}		1 부터 시작하는 순서
${articleNum.first}		첫번째 인지의 여부
${articleNum.last}		마지막 인지의 여부
${articleNum.begin}		for문의 시작번호
${articleNum.end}		for문의 끝번호
${articleNum.step}		for문의 증가값

<fmt:formatDate value="${article.writeDate}" pattern="yyyy년MM월dd일 hh시mm분ss초"/>

http://localhost:8080/board01/listArticles.jsp

http://localhost:8080/board/listArticles.do

*/
%>













