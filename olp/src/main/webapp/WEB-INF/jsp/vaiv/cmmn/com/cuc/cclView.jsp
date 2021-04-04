<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<c:set var="nowUrl"><c:out value="${requestScope['javax.servlet.forward.request_uri']}"/></c:set>

<c:choose>
	<c:when test="${cclTy eq 'A'}">
		<c:set var="imgName" value="by.png"/>
		<c:set var="cclLicense" value="저작자 표시"/>
	</c:when>
	<c:when test="${cclTy eq 'B'}">
		<c:set var="imgName" value="by-sa.png"/>
		<c:set var="cclLicense" value="저작자 표시-동일조건 변경 허락"/>
	</c:when>
	<c:when test="${cclTy eq 'C'}">
		<c:set var="imgName" value="by-nd.png"/>
		<c:set var="cclLicense" value="저작자 표시-변경금지"/>
	</c:when>
	<c:when test="${cclTy eq 'D'}">
		<c:set var="imgName" value="by-nc.png"/>
		<c:set var="cclLicense" value="저작자 표시-비영리"/>
	</c:when>
	<c:when test="${cclTy eq 'E'}">
		<c:set var="imgName" value="by-nc-sa.png"/>
		<c:set var="cclLicense" value="저작자 표시-비영리-동일조건 변경 허락"/>
	</c:when>
	<c:when test="${cclTy eq 'F'}">
		<c:set var="imgName" value="by-nc-nd.png"/>
		<c:set var="cclLicense" value="저작자 표시-비영리-변경금지"/>
	</c:when>
</c:choose>

<div class="Kogl">
    <div class="Kogl_tit">
        <strong>컨텐츠 저작물<br />${cclLicense }</strong>
        <div class="Kogl_img">
            <img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/<c:out value="${imgName }"/>" alt="저작자표시 CC-by">
        </div>
    </div>
    <div class="Kogl_txt">
        <strong class="tit"><c:out value="${cclLicense }"/></strong>
        <p><c:out value="${registNm }"/> 에 의해 작성된 <em class="c_grey underline">'<c:out value="${cntntsNm }"/>'</em> 컨텐츠는 <strong class="c_red">Creative commons <c:out value="${cclLicense }"/> 라이선스</strong> 에 따라 이용할 수 있습니다.</p>
    </div>
</div>