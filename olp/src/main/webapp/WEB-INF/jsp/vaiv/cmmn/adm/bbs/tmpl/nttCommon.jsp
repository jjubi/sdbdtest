<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<!-- 게시물 공통 변수 : s -->
<!-- nowBbsId -->
<input type="hidden" id="nowBbsId" name="nowBbsId" value="${bbsId}">
<!-- bbstId -->
<input type="hidden" id="nowBbsTyId" name="nowBbsTyId" value="${bbsVO.bbsTyId}">
<!-- 최고관리자 여부 -->
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<c:set var="isAdmin" value="true"/>
</sec:authorize>

<!-- 게시물 공통 변수 : e -->
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/bbs/ntt/ntt.js"></script>







