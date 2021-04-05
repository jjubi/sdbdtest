<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : nttOptn > lotdspl.jsp
  * @상세설명 : 게시물 옵션 페이지(위치표시여부) 
  * @작성일시 : 2020. 12. 31
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2020.12.31   jo	  최초등록
  * @ 
  * 
  */
%>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoAPIKey }&libraries=services"></script>

<!-- 도로명 api 활용 -->
<%-- <script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/ajaxCall.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/juso/jusoPopup.js"></script> --%>
<script>
$(document).ready(function(){
	$('input[name="lcIndictAt"]').on('change', function(){
		if($(this).val() == 'Y'){
			$('.lcIndictAddrArea').show();
		} else {
			$('.lcIndictAddrArea').hide();
			$('.lcIndictAddrArea').find('#adres').val('');
		}
	});
	
});
</script>

<c:choose>
	<c:when test="${bbsTyCode eq 'xxx' }">
		<!-- 게시글 첨부파일 사용 시 특별한 템플릿이 있을경우 추가 -->
	</c:when>
	<c:otherwise>
		<!-- 기본적으로 사용되는 게시글 템플릿 -->
		<div class="col-sm-12 col-md-2 control-label col-lg-2">
			<!-- 위치표시 사용 -->
			<label for="lcIndictAtY">위치표시 여부 <span class="pilsu">*</span></label>
		</div>
		<div class="col-sm-12 col-md-4">
            <label class="radio-inline" for="lcIndictAtY">
            	<c:if test="${formType eq 'regist'}">
            		<form:radiobutton path="lcIndictAt" id="lcIndictAtY" value="Y" checked="checked" />
            	</c:if>
            	<c:if test="${formType eq 'update'}">
            		<form:radiobutton path="lcIndictAt" id="lcIndictAtY" value="Y" />
            	</c:if>
            	사용
            </label>
            <label class="radio-inline" for="lcIndictAtN">
            	<form:radiobutton path="lcIndictAt" id="lcIndictAtN" value="N" /> 사용안함
            </label>
        </div>
		<div class="col-sm-12 col-md-2 control-label col-lg-2 lcIndictAddrArea" style="display: ${formType eq 'update' && nttVO.lcIndictAt eq 'N' ? 'none' : 'block'};">
			<!-- 위치표시 사용 -->
			<label for="lcIndictAtY">위치표시 주소 <span class="pilsu">*</span></label>
		</div>
		<div class="col-sm-12 col-md-4 lcIndictAddrArea" style="display: ${formType eq 'update' && nttVO.lcIndictAt eq 'N' ? 'none' : 'block'};">
			<div class="input-group">
				<form:input path="adres" class="form-control" title="${title} 입력" size="40" maxlength="30" readonly="true" />
				<div><form:errors path="adres" cssClass="error" /></div>
				<br/>
				<div class="input-group-append">
					<!-- 다음 주소검색 -->
					<a href="javascript:searchAddr('','adres','adresLa','adresLo');" class="input-group-text">주소 검색</a>
					<!-- 도로명 api 활용 -->
<!-- 					<a href="javascript:searchJusoAdres('zipCode','adres','detailAddr', 'adresLa', 'adresLo');" class="input-group-text">주소 검색</a> -->
					<br/>
					<form:hidden path="adresLa"/>
					<form:hidden path="adresLo"/>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>
