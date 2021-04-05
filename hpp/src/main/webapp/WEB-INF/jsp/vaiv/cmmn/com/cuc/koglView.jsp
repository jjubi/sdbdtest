<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<c:set var="nowUrl"><c:out value="${requestScope['javax.servlet.forward.request_uri']}"/></c:set>

<c:choose>
	<c:when test="${koglTy eq 'A'}">
		<c:set var="imgName" value="img_opentype01.png"/>
		<c:set var="koglNum" value="1"/>
		<c:set var="koglType" value="출처표시"/>
	</c:when>
	<c:when test="${koglTy eq 'B'}">
		<c:set var="imgName" value="img_opentype02.png"/>
		<c:set var="koglNum" value="2"/>
		<c:set var="koglType" value="출처표시 + 상업적 이용금지"/>
	</c:when>
	<c:when test="${koglTy eq 'C'}">
		<c:set var="imgName" value="img_opentype03.png"/>
		<c:set var="koglNum" value="3"/>
		<c:set var="koglType" value="출처표시 + 변경금지"/>
	</c:when>
	<c:when test="${koglTy eq 'D'}">
		<c:set var="imgName" value="img_opentype04.png"/>
		<c:set var="koglNum" value="4"/>
		<c:set var="koglType" value="출처표시 + 상업적 이용금지 + 변경금지"/>
	</c:when>
</c:choose>

<div class="Kogl">
    <div class="Kogl_tit">
        <strong>공공저작물 <br />자유이용 허락 표시</strong>
        <div class="Kogl_img">
            <img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/<c:out value="${imgName }"/>" alt="공공누리 공공저작물 자유이용허락-${koglType }">
        </div>
    </div>
    <div class="Kogl_txt">
        <strong class="tit">공공누리 <span class="c_red">${koglNum }</span>유형</strong>
        <p>본 저작물은 <span><c:out value="${registNm }"/></span>에서 작성하여 공공누리 출처표시 조건에 따라 <strong>공공누리 <em class="c_red">${koglNum }</em>유형</strong><strong class="c_red">"${koglType }"</strong> 조건에 따라 이용할 수 있습니다.</p>
    </div>
</div>