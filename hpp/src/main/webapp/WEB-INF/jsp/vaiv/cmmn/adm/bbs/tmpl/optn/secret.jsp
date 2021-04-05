<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : nttOptn > secret.jsp
  * @상세설명 : 게시물 옵션 페이지(비밀글사용여부) 
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

<script>
$(document).ready(function(){
	$('input[name="secretAt"]').on('change', function(){
		if($(this).val() == 'Y'){
			$('.secretKeyArea').show();
		} else {
			$('.secretKeyArea').hide();
			$('.secretKeyArea').find('#secretKey').val('');
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
			<!-- 비밀글 여부 -->
			<label for="secretAtY">비밀글 여부 <span class="pilsu">*</span></label>
		</div>
		<div class="col-sm-12 col-md-4">
            <label class="radio-inline" for="secretAtY">
            	<c:if test="${formType eq 'regist'}">
        			<form:radiobutton path="secretAt" id="secretAtY" value="Y" checked="checked" />
            	</c:if>
            	<c:if test="${formType eq 'update'}">
            		<form:radiobutton path="secretAt" id="secretAtY" value="Y" />
            	</c:if>
            	사용
            </label>
            <label class="radio-inline" for="secretAtN">
            	<form:radiobutton path="secretAt" id="secretAtN" value="N" /> 사용안함
            </label>
        </div>
		<div class="col-sm-12 col-md-2 control-label col-lg-2 secretKeyArea" style="display: ${formType eq 'update' && nttVO.secretAt eq 'N' ? 'none' : 'block'};">
			<!-- 비밀글 Key -->
			<label for="secretKey">비밀글 Key <span class="pilsu">*</span></label>
		</div>
		<div class="col-sm-12 col-md-4 secretKeyArea" style="display: ${formType eq 'update' && nttVO.secretAt eq 'N' ? 'none' : 'block'};">
			<form:input path="secretKey" class="form-control" title="${title} 입력" size="40" maxlength="30" />
			<div><form:errors path="secretKey" cssClass="error" /></div>
		</div>
	</c:otherwise>
</c:choose>

