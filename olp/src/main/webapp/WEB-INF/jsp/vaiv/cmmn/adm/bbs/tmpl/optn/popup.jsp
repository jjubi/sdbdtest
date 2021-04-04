<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : nttOptn > popup.jsp
  * @상세설명 : 게시물 옵션 페이지(팝업사용여부) 
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

<c:choose>
	<c:when test="${bbsTyCode eq 'xxx' }">
		<!-- 게시글 첨부파일 사용 시 특별한 템플릿이 있을경우 추가 -->
	</c:when>
	<c:otherwise>
		<!-- 기본적으로 사용되는 게시글 템플릿 -->
		<div class="col-sm-12 col-md-2 control-label col-lg-2">
			<!-- 팝업 사용 -->
			<label for="noticeAtY">팝업 여부 <span class="pilsu">*</span></label>
		</div>
		<div class="col-sm-12 col-md-4">
            <label class="radio-inline" for="popupAtY">
            	<c:if test="${formType eq 'regist'}">
        			<form:radiobutton path="popupAt" id="popupAtY" value="Y" checked="checked" />
            	</c:if>
            	<c:if test="${formType eq 'update'}">
            		<form:radiobutton path="popupAt" id="popupAtY" value="Y" />
            	</c:if>
            	사용
            </label>
            <label class="radio-inline" for="noticeAtN">
            	<form:radiobutton path="popupAt" id="popupAtN" value="N" /> 사용안함
            </label>
        </div>
	</c:otherwise>
</c:choose>	