<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib	prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <title>회원 가입 화면</title>
	<link href="${contextPath}/css/ezen.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
			<script>
			
			function fnc_process() { // 아이디 중복 검사
				
		        var tid = document.getElementById('ef_id');
		        var id_RegExpCn = /^[a-zA-Z0-9]{4,12}$/;
			    var	_id1	= $("#ef_id").val();
			    
			        
						$.ajax({	// 아이디 중복 체크 기능
								type:		"post",
								url:		"${contextPath}/user/checkId.do",
								dataType:	"json",
								async : false,
								data:		{ef_id: _id1},	
								success:	function(data) {
									
								// 서버에서 전송된 결과에 따라 메시지를 표시한다.
								
									if (tid.value == '' || tid.value == null){				// 아이디 입력란이 공백인 경우.
										$("#idChkMsg").html("아이디를 입력해주세요.");
										$("#subBtn").prop("disabled", true);
										$("#idChkMsg").css("color","white")
									} else if (!id_RegExpCn.test(tid.value)){				// 아이디에 특수문자나 공백이 들어간 경우.
										$("#idChkMsg").html("영문&숫자 4~12자리만 가능합니다");
										$("#idChkMsg").css("color","pink")
										$("#subBtn").prop("disabled", true);
							        } else if ($.trim(data) == 0) {							// 아이디가 DB와 겹치지 않는 경우.
										$("#idChkMsg").html("등록 가능한 아이디 입니다.");	
										$("#idChkMsg").css("color","skyblue")
										$("#subBtn").prop("disabled", false);
									} else {												// 아이디가 DB와 겹치는 경우.
										$("#idChkMsg").html("이미 사용중인 아이디 입니다.");
										$("#idChkMsg").css("color","pink")
										$("#subBtn").prop("disabled", true);
									}
								}
						});
					}

			$(document).ready(function() {
				
				
				
				// 취소버튼, 가입하기 버튼 눌렀을 시
				$("#cancel").on("click", function() {
					location.href = "${contextPath}/index.do";
				});
				
				
				$("#subBtn").on("click", function() {
					var RegExp = /^[a-zA-Z0-9]{4,12}$/; // 비밀번호 유효성 검사
					var e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; // 이메일 유효성 검사
				    var n_RegExp = /^[가-힣]{2,12}$/; //이름 유효성검사
				    
				    var objPwd = document.getElementById("ef_pwd"); // 비밀번호 입력값 받기
				    var objEmail = document.getElementById("ef_email"); // 이메일 입력값 받기
				    var objName = document.getElementById("ef_name"); // 이름 입력값 받기				    				    
				    var objRadio = $(":input:radio[name=Agree]:checked").val(); // 이용 표준약관 라디오 박스 동의 여부 값 받기
				    
				    
				    // 등록업체 선택 유효성 검사 영역
				    if($("#ef_c_name").val()=="가입 업체"){
			        	alert("등록하신 업체를 선택해주세요.")
			        	return false;
			        }
				    
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
			            alert("특수문자,영어,숫자는 사용할수 없습니다. 한글만 입력하여주세요.");
			            $("#ef_name").focus();
			            return false;
			        }
					
					// 전화번호 유효성 검사 영역
					if($("#ef_p_number").val() == '') {
						alert("전화번호를 입력하십시오");
						$("#ef_p_number1").focus();
						return false;
					}
					
					// 이메일 주소 유효성 검사 영역
					if($("#ef_email").val() == '') {
						alert("이메일을 입력하십시오");
						$("#ef_email1").focus();
						return false;
					}        
			        if(!e_RegExp.test(objEmail.value)){
			            alert("올바른 이메일 주소를 입력해 주세요. ex) ezenfit@ezenfit.com");
			            $("#ef_email1").focus();
			            return false;
			        }
			        			        			        
			     	// 약관 동의 함 체크 검사 영역
			        if(objRadio=='no'){
				        alert('이용 표준약관에 동의하셔야 가입이 가능합니다.');
				        $("#disagree").focus();
				        return false;
				    }
			       
				
					document.memInsForm.action = "${contextPath}/user/addUser.do";
					document.memInsForm.submit();
				
				}); // submit버튼
				
				
				
			}); // function
			
			
			// 업체 선택여부 검증.
			function selectCn(){
				var selV = document.getElementById('ef_c_name');
				
			if ($(selV).val()=="가입 업체"){
					$("#cnChkMsg").html("업체를 선택해주세요.");
					$("#cnChkMsg").css("color","white");
			}else if ($(selV).val()!="가입 업체"){
					$("#cnChkMsg").css("color","skyblue");
					document.getElementById('cnChkMsg').innerText
				    = "선택하신 업체는 " + $(selV).val() + " 입니다.";
			}
	        }
				
			</script>
</head>
<body>
	<div class="Field">
	<span class="joinTitle">회원가입</span>
	<!-- 좌우를 나누는 가장 바깥 테이블 -->
	<table id="center">
		<tr>
			<td>
				<form method="post" name="memInsForm">
					<!-- 회원가입 양식 좌측 테이블 -->
					<table id="joinTable">
						<tr style="display:none;">
							<th><input type="text" id="ef_class" name="ef_class" value="02"></th>
						</tr>	
						<tr> <!--  업체명 등록 관련  -->
							<td><span class="joinText">업체명</span></td>
							<td><select id="ef_c_name" class="joinSelect" name="ef_c_name" onchange="selectCn()">
									<option selected>가입 업체</option>
									<c:forEach items="${centerList}" var="center">
										<option><c:out value="${center.ef_c_name}"/></option>
									</c:forEach>
								</select></td>
						<tr>				
							<td><span class="joinText2">√ 선택한 업체 : </span></td>
							<td id="cnChkMsg" >업체를 선택해주세요.</td>
						</tr>							
						<tr>
							<td><span class="joinText">아이디</span></td>
							<td><input type="text" id="ef_id" name="ef_id" class="joinInput" maxlength="12" placeholder="아이디 입력" oninput="fnc_process();"></td>
						<tr>
							<td><span class="joinText2">√ 아이디 중복 체크 : </span></td>
							<td id="idChkMsg">아이디를 입력해주세요.</td>
						</tr>		
						<tr>
							<td><span class="joinText">비밀번호</span></td>
							<td><input type="password" id="ef_pwd" name="ef_pwd" class="joinInput" maxlength="12" placeholder="비밀번호 입력"/></td>
						</tr>
						
						<tr>
							<td><span class="joinText">비밀번호 확인</span></td>
							<td><input type="password" id="repwd" name="repwd" class="joinInput" maxlength="12" placeholder="비밀번호 다시 입력"/></td>
						</tr>
						<tr>
							<td><span class="joinText">이 름</span></td>
							<td><input type="text" id="ef_name" name="ef_name" class="joinInput" maxlength="12" placeholder="이름 입력"/></td>
						</tr>		
						<tr>
							<td><span class="joinText">전화번호</span></td>
							<td><input type="text" id="ef_p_number" name="ef_p_number" class="joinInput" maxlength="12" placeholder="전화번호 입력"/></td>
						</tr>		
						<tr>
							<td><span class="joinText">이메일</span></td>
							<td><input type="text" id="ef_email" name="ef_email" class="joinInput" maxlength="50" placeholder="이메일 입력"/></td>
						</tr>		
						<tr>
							<td class="leftSide"><button type="button" id="cancel" class="memberBtn">가입취소</button></td>
							<td><button type="button" id="subBtn" disabled="disabled" class="memberBtn">가입하기</button></td>
						</tr>
					</table>
				</form>
			</td>
			<td>
				<!-- 표준약관 양식 우측 테이블 -->
				<table id="termsTable">
				<thead>
					<tr>
						<th colspan=2>이젠핏 이용 표준약관</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan=2>
						제1조<br/>
						(목적)<br/> 
						이 약관은 ○○체력단련장과 체력단련장이 제공하는 시설 및 서비스를 이용하는 자(이하 ‘이용자’라 합니다) 사이에 체결된 계약에 따른 권리․의무에 관한 사항을 정함을 목적으로 합니다.<br/>
						제2조<br/>
						(적용대상) 이 약관은 ○○체력단련장을 이용하는 모든 이용자에게 적용합니다.<br/>
						제3조<br/>
						(이용계약 및 약관 교부)
						<ol>
						  <li> 이용자는 체력단련장(이하 ‘사업자’라 합니다)이 정한 절차에 따라 이용 신청을 하고 이용료를 납부하여야 합니다.
						  <li> 사업자는 체력단련장 이용신청을 승낙할 경우 이용자에게 이용증과 약식 약관(이용증 이면에 첨부)을 교부하여야 합니다.
						  <li> 사업자는 제2항의 약식약관의 교부에 관계없이 이용자가 요구할 경우 약관의 사본을 교부하여야 합니다.
						</ol>
						제4조<br/>
						(게시․설명의무)
						 <ol>
						  <li> 사업자는 이용자가 용이하게 볼 수 있는 장소에 다음 사항을 게시하여야 합니다.<br/>
						  <ol type="a">
						    <li> 시간별 프로그램 내용 및 정원
						    <li> 지도강사의 인적사항
						    <li> 강습의 변경
						    <li> 이용료
						    <li> 약관내용
						    <li> 소지품 보관시 유의 사항 등
						    <li> 이용자 안전수칙 등 기타 필요사항
						  </ol>
						  <li> 사업자는 이용자가 체력단련장 시설 및 기구를 안전하게 이용할 수 있도록 이용방법 및 주의사항 설명 등 선량한 관리자로서 의무를 다하여야 합니다.
						  </ol>						  
						제5조<br/>
						(이용증 제시)
						<ol>
						  <li> 이용자는 사업자가 이용증 제시를 요구할 경우 이용증을 제시하여야 합니다.
						  <li> 이용자는 이용증을 분실하였을 경우 지체없이 사업자에게 그 사실을 통지 하고 재교부를 받아야 합니다.
						</ol>
						제6조<br/>
						(이용신청 철회)
						<ol>
						  <li> 이용자가 체력단련장 이용을 할부계약으로 신청한 경우 신청일로부터 7일 이내 서면으로, 방문사원 권유로 신청한 경우에는 신청일로부터 14일 이내에 이용신청을 철회할 수 있습니다. 단 신청일에 계약서(또는 약관)을 교부받지 못한 경우 이용 개시일을 기준으로 합니다.
						  <li> 제1항의 경우 사업자는 ‘할부거래에관한법률’ 제6조, ‘방문판매등에관한법률’ 제9조에 따른 원상회복의 의무를 집니다.
						</ol>
						제7조<br/>
						(이용 연기)
						<ol>
						  <li> 이용자는 체력단련장 이용 전 또는 이용 중  연기가 필요한 경우 사업자와 협의하여 이용시기를 연기할 수 있습니다.
						  <li> 제1항의 경우에 이용자는 연기신청서를 사업자에게 제출하여야 합니다.
						  <li> 제2항의 신청을 받은 사업자는 특별한 사정이 없는 한 승낙하여야 합니다.
						</ol>
						제8조<br/>
						(계약의 해제․해지)
						  <ol type="a">
						   <li> 이용자는 다음 각 호의 사유가 있을 경우 계약을 해제․해지할 수 있습니다.
						  <ol>
						    <li> 체력단련장 시설 이용 및 강습 등에 관한 계약내용 또는 광고내용이 실제와 다른 경우
						    <li> 체력단련기기 및 시설의 고장으로 정상적인 이용이 곤란한 경우
						    <li> 이전․휴업․폐업․정원초과 등으로 체력단련장 이용이 곤란한 경우
						    <li> 기타 사업자의 책임 있는 사유로 체력단련장 이용이 곤란한 경우
						  </ol>
						  <li> 계약을 해제․해지하는 경우 사업자와 이용자는 다음과 같은 조건으로 계약을 해제․해지할 수 있습니다.
							   이용개시일 이전 계약 해제․해지
							<ol>
						       <li>제1항 각 호의 사유로 인하여 이용자가 계약을 해제․해지하는 경우 사업자는 총 이용금액(‘매 이용 계약시 이용료로 납입한 총액’으로 이하 같음) 전액과 총 이용금액의 10%에 해당하는 금액을 이용자에게 환불합니다.
						       <li>이용자 사정으로 계약을 해제․해지하는 경우 사업자는 총 이용금액의 10%에 해당하는 금액을 공제한 후 이용자에게 환불합니다.
							</ol>
						  <li>이용개시일 이후 계약 해제․해지
						  	<ol>
						   <li>제1항 각 호의 사유로 이용자가 계약을 해제․해지하는 경우 사업자는 총 이용금액에서 해지일까지 이용금액을 일할 계산하여 공제하고 난 후의 금액과 총 이용금액의 10%에 해당하는 금액을 이용자에게 환불합니다.						    	
						   <li>이용자 사정으로 계약을 해제․해지하는 경우 사업자는 총 이용금액을 기준으로 해지일까지 이용일수에 해당하는 금액과 총 이용금액의 10%에 해당하는 금액을 공제한 후 이용자에게 환불합니다.
						    </ol>
						  <li> 계약의 해제․해지에도 불구하고 사업자가 제공한 사은품은 반환 또는 환불하지 않습니다.
						  </ol>
						제9조<br/>
						(손해배상)
						  <ol>
						  	<li>체력단련장의 시설물에 의해 이용자에게 신체상의 피해가 발생한 경우 사업자는 그 손해를 배상하여야 합니다. 다만 그 손해가 불가항력으로 인하여 발생한 경우 또는 그 손해의 발생이 이용자의 고의 또는 과실로 인한 경우에는 그 배상의 책임이 경감 또는 면제됩니다.
						  	<li>이용자의 고의․중대한 과실로 체력단련기기의 파손 등 체력단련장에 손해가 발생한 경우 이용자는 이를 배상하여야 합니다.
						  </ol>
						제10조<br/>
						(소지품의 보관)
						<ol>
						  <li>이용자는 사업자에게 자신이 휴대한 소지품의 보관을 요구할 수 있습니다.
						  <li>제1항의 요구를 받은 사업자는 이용자의 소지품을 안전하게 보관한 후 반환하여야 합니다.
						  <li>제1항에 의해 보관한 소지품이 멸실 또는 훼손된 경우 사업자는 불가항력으로 인한 것임을 입증하지 못하면 그 손해를 배상하여야 합니다.
						  <li>이용자가 보관을 의뢰하지 않은 소지품도 사업자측의 책임있는 사유로  멸실 또는 훼손된 경우 사업자는 그 손해를 배상하여야 합니다.
						  <li>이용자는 휴대품이 화폐, 유가증권 기타 고가물인 경우 그 종류와 가액을 명시하여 사업자에게 보관을 의뢰하여야 합니다. 사업자는 이용자의 요구에 응하여 해당 물품을 안전하게 보관하여야 합니다.
						  <li>제5항에 의해 이용자가 보관을 의뢰하지 않은 고가물이 멸실 또는 훼손된 경우 사업자는 제4항에 의거 손해배상 책임을 집니다.
						  <li>기타 이용자의 소지품 보관에 관한 사항은 상법 제151조 이하의 규정에 의합니다.
						</ol>
						제11조<br/>
						(면책조항)
						<ol>
						  <li>사업자는 천재지변 기타 불가항력의 사유로 체력단련장 이용이 곤란한 때에는 그 사유가 종료될 때까지 이용을 중단시킬 수 있습니다.
						  <li>제1항의 경우에 이용자는 계약을 해지할 수 있다. 단, 이 경우 이용자는 사업자에 대해 손해배상을 청구할 수 없습니다.
						  <li>이용자가 제1항을 이유로 계약을 해지하는 경우 사업자는 총 이용금액에서 이용을 중단한 날까지 일할 계산하여 공제하고 난 후의 금액을 환불합니다.
						</ol>
						제12조<br/>
						(사물함 열쇠 반환)
						<ol>
						  <li>이용자는 체력단련장 이용 종료와 동시에 사물함열쇠를 사업자에게 반환하여야 합니다.
						  <li>이용자가 체력단련장 이용종료일에 사물함 열쇠를 반환하지 않을 경우 사업자는 열쇠 반환일까지 사물함 사용료를 청구할 수 있습니다.
						  <li>체력단련장 이용이 종료한 날로부터 1달이 경과하여도 이용자가 사물함 열쇠를 반환하지 않을 경우 사업자는 사물함을 회수할 수 있습니다.
						</ol>
						제13조<br/>
						(기타)
						<ol>
						  <li>이 약관에서 규정하지 않은 사항은 관계법령 및 거래 관행을 고려하여 신의칙에 따라 사업자와 이용자가 합의하여 해결합니다.
						  <li>이 약관과 관련된 소송의 관할법원은 민사소송법상의 관련규정에 의합니다.
						</ol>
						</td>
					</tr>
					<tr>
						<td id="radio">
						<input type="radio" name="Agree" id="disagree" value="no" checked>동의 하지 않음
						</td>
						<td id="radio">
						<input type="radio" name="Agree" id="Agree" value="yes">동의 함
						</td>
					</tr>
				</tbody>
				</table>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>