<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstGallery > nttDetail.jsp
  * @상세설명 : gallery 게시물 상세 페이지 
  * @작성일시 : 2020. 12. 31
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2020.12.31   jo     최초등록
  * @ 
  * 
  */
%>
<%@ include file="/WEB-INF/jsp/vaiv/cmmn/adm/bbs/tmpl/nttCommon.jsp" %>
<% pageContext.setAttribute("rlcn", "\n"); %>
<c:set var="pageTitle"><c:out value="${bbsVO.bbsNm }"/></c:set>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/plugins/slick/slick.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/slick/slick.min.js"></script>

<script>
$(document).ready(function(){
    $('.board_info_btn').setLinkBtn();
    $('.board_info_btn').setPrintBtn('contentsArea');
    $('.board_info_btn').setSocialShareBtn();
});
</script>

<div class="container-fluid" id="contentsArea">
    <h1 class="header-title">${pageTitle}</h1>
    <div class="card">
        <div class="card-body">
            <h1 class="h1">
                ${pageTitle} <spring:message code="title.detail" />
                <c:if test="${not empty parentNttVO }">
                    <small>(상단 글 제목 : <c:out value="${parentNttVO.nttSj}"/>)</small>
                </c:if>
            </h1>
            <div class="card-wrap">
                <div class="form-horizontal board_view">
                    <div class="board_view_header">
                        <div class="board_tit">
                            <h2><c:out value="${nttVO.nttSj }" escapeXml="false"/></h2>
                            <div class="board_info">
                                <span class="write_name">
                                    <strong>작성자</strong>
                                    <em><c:out value="${nttVO.registNm }"/></em>
                                </span>
                                <span class="write_date">
                                    <strong>등록일</strong>
                                    <em><c:out value="${nttVO.registDe }"/></em>
                                </span>
                                <span class="view_num">
                                    <strong>조회수</strong>
                                    <em><c:out value="${nttVO.nttRdcnt }"/></em>
                                </span>
                            </div>
                        </div>
                        <div class="board_info_btn">
<!--                            <button type="button" class="linkBtn"><i class="board-icon icon-linkcopy">링크 아이콘</i></button> -->
<!--                            <button type="button" class="printBtn"><i class="board-icon icon-print">인쇄 아이콘</i></button> -->
<!--                            <button type="button" class="socialShare" data-toggle="dropdown"><i class="board-icon icon-share">공유 아이콘</i></button> -->
                        </div>
                    </div>
                    <div class="board_view_cont">
                        <p>
                            <c:out value="${fn:replace(nttVO.nttCn, rlcn, '<br/>') }" escapeXml="false"/>
                        </p>
                        <c:if test="${nttVO.lcIndictAt eq 'Y' }">
                            <c:import url="/cmmn/com/cuc/mapView.do">
                                <c:param name="lat" value="${nttVO.adresLa }"/>
                                <c:param name="lng" value="${nttVO.adresLo }"/>
                                <c:param name="addr" value="${nttVO.adres }"/>
                            </c:import>
                        </c:if>
                    </div>
                    <c:if test="${not empty nttVO.atchFileId }">
                    <!-- 갤러리 뷰 -->
                    <c:import url="/cmmn/com/cfc/nttFileList.do">
                        <c:param name="atchFileId" value="${nttVO.atchFileId }"/>
                        <c:param name="nttId" value="${nttVO.nttId }"/>
                        <c:param name="loadType" value="imgListView01"/>
                    </c:import>
                    <div class="board_view_file">
                        <c:import url="/cmmn/com/cfc/nttFileList.do">
                            <c:param name="atchFileId" value="${nttVO.atchFileId }"/>
                            <c:param name="nttId" value="${nttVO.nttId }"/>
                            <c:param name="loadType" value="viewFileList"/>
                        </c:import>
                    </div>
                    </c:if>
                    
                    <c:if test="${!empty nttVO.koglTy }">
                    <!-- 공공누리 뷰 -->
                        <c:import url="/cmmn/com/cuc/koglView.do">
                            <c:param name="koglTy" value="${nttVO.koglTy }"></c:param>
                        </c:import>
                    </c:if>
                    
                    <c:if test="${!empty nttVO.cclTy }">
                    <!-- ccl 뷰 -->
                        <c:import url="/cmmn/com/cuc/cclView.do">
                            <c:param name="cclTy" value="${nttVO.cclTy }"></c:param>
                        </c:import>
                    </c:if>
                </div>
                <c:if test="${bbsOptnVO.cmtUseAt eq 'Y' }">
                <sec:authorize access="hasRole('${bbsAuthorVO.cmtRedngAuthor }')">
                    <c:import url="/cmmn/adm/bbs/ntt/${bbsId }/view/nttOptnCmtView.do">
                        <c:param name="bbsTyCode" value="${bbsTyCode }"/>
                        <c:param name="nttId" value="${nttVO.nttId }"/>
                    </c:import>
                </sec:authorize>
                </c:if>
            </div>
            <div class="card-warp-footer">
                <a class="btn btn-secondary" href="javascript:;" onclick="fnSelectNttList();"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a>
                <div class="right btn-group">
                    <c:if test="${bbsOptnVO.answerUseAt eq 'Y' && nttVO.noticeAt eq 'N' && nttVO.nttDp le 3}">
                    <sec:authorize access="hasRole('${bbsAuthorVO.answerRegistAuthor }')">
                        <a href="/cmmn/adm/bbs/ntt/${bbsId }/nttAnswerRegist.do?nttId=<c:out value="${nttVO.nttId }"/>" class="btn btn-primary" title="<spring:message code="button.reply" /> <spring:message code="input.button" />"><spring:message code="button.reply" /></a><!-- 답글 -->
                    </sec:authorize>
                    </c:if>
                    <sec:authorize access="hasRole('${bbsAuthorVO.nttDeleteAuthor }')">
                    <c:if test="${loginVO.uniqId eq nttVO.registId || topAdmin == true}">
                        <button type="button" onclick="fnDeleteNtt('<c:out value="${nttVO.nttId }"/>');" class="btn btn-danger" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button><!-- 삭제 -->
                    </c:if>
                    </sec:authorize>
                    <sec:authorize access="hasRole('${bbsAuthorVO.nttUpdtAuthor }')">
                    <c:if test="${loginVO.uniqId eq nttVO.registId || topAdmin == true}">
                        <a href="/cmmn/adm/bbs/ntt/${bbsId}/nttModify.do?nttId=<c:out value="${nttVO.nttId }"/>" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /></a><!-- 수정 -->
                    </c:if>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</div>

<input type="hidden" name="nttId" value="${nttVO.nttId}">

<form id="hiddenSearchForm" class="d-none" method="post">
    <input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
    <input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
    <input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>