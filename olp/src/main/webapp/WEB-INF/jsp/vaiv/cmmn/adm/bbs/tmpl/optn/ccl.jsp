<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : nttOptn > ccl.jsp
  * @상세설명 : 게시물 옵션 페이지(ccl사용여부) 
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
			<label for="cclTyA">ccl 유형 <span class="pilsu">*</span></label>
		</div>
		<div class="col-sm-12 col-md-10 Kogl_check">
            <label class="radio-inline" for="cclTyA">
            	<form:radiobutton path="cclTy" id="cclTyA" value="A"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
            	<span>BY</span>
            </label>
            <label class="radio-inline" for="cclTyB">
            	<form:radiobutton path="cclTy" id="cclTyB" value="B"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-sa.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
            	<span>BY-SA</span>
            </label>
            <label class="radio-inline" for="cclTyC">
            	<form:radiobutton path="cclTy" id="cclTyC" value="C"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-nd.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
            	<span>BY-ND</span>
            </label>
            <label class="radio-inline" for="cclTyD">
            	<form:radiobutton path="cclTy" id="cclTyD" value="D"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-nc.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
            	<span>BY-NC</span>
            </label>
            <label class="radio-inline" for="cclTyE">
            	<form:radiobutton path="cclTy" id="cclTyE" value="E"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-nc-sa.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
            	<span>BY-NC-SA</span>
            </label>
            <label class="radio-inline" for="cclTyF">
            	<form:radiobutton path="cclTy" id="cclTyF" value="F"/>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-nc-nd.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
            	<span>BY-NC-ND</span>
            </label>
            <label class="radio-inline" for="cclTyNO">
               	<c:if test="${formType eq 'regist'}">
        			<form:radiobutton path="cclTy" id="cclTyNo" value="" checked="checked" />
            	</c:if>
            	<c:if test="${formType eq 'update'}">
            		<form:radiobutton path="cclTy" id="cclTyNo" value="" />
            	</c:if>
            	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/no-data-img.png" alt="CCL 없음">
            	<span>없음</span>
            </label>
        </div>
	</c:otherwise>
</c:choose>	