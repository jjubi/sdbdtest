<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : nttOptn > kogl.jsp
  * @상세설명 : 게시물 옵션 페이지(공공누리 사용여부) 
  * @작성일시 : 2021. 03. 15
  * @작 성 자 : kmk
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2021.03.15   kmk	  최초등록
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
			<label for="koglTyA">공공누리 유형 <span class="pilsu">*</span></label>
		</div>
		<div class="col-sm-12 col-md-10 Kogl_check">
            <label class="radio-inline" for="koglTyA">
            	<form:radiobutton path="koglTy" id="koglTyA" value="A"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/img_opentype01.png" alt="공공누리 공공저작물 자유이용허락-출처표시">
            	<span>출처표시</span>
            </label>
            <label class="radio-inline" for="koglTyB">
            	<form:radiobutton path="koglTy" id="koglTyB" value="B"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/img_opentype02.png" alt="공공누리 공공저작물 자유이용허락-출처표시+상업용금지">
            	<span>출처표시+상업용금지</span>
            </label>
            <label class="radio-inline" for="koglTyC">
            	<form:radiobutton path="koglTy" id="koglTyC" value="C"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/img_opentype03.png" alt="공공누리 공공저작물 자유이용허락-출처표시+변경금지">
            	<span>출처표시+변경금지</span>
            </label>
            <label class="radio-inline" for="koglTyD">
            	<form:radiobutton path="koglTy" id="koglTyD" value="D"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/img_opentype04.png" alt="공공누리 공공저작물 자유이용허락-출처표시+상업용이용금지+변경금지">
            	<span>출처표시+상업용이용금지+변경금지</span>
            </label>
            <label class="radio-inline" for="koglTyNO">
            	<c:if test="${formType eq 'regist'}">
        			<form:radiobutton path="koglTy" id="koglTyNo" value="" checked="checked" />
            	</c:if>
            	<c:if test="${formType eq 'update'}">
            		<form:radiobutton path="koglTy" id="koglTyNo" value="" />
            	</c:if>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/no-data-img.png" alt="공공누리 없음">
            	<span>없음</span>
            </label>
        </div>
	</c:otherwise>
</c:choose>	