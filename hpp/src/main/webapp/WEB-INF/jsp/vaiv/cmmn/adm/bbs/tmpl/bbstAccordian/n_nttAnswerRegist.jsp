<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstAccordian > nttAnswerRegist.jsp
  * @상세설명 : accordian 게시물 답글 등록 페이지 
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
<%@ include file="/WEB-INF/jsp/vaiv/cmmn/adm/bbs/tmpl/nttCommon.jsp" %>
<c:set var="pageTitle"><c:out value="${bbsVO.bbsNm }"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="nttVO" staticJavascript="false" xhtml="true" cdata="false"/>

<form:form commandName="nttAnswerVO" action="${pageContext.request.contextPath}/cmmn/adm/bbs/ntt/${bbsId}/insertNtt.do" method="post" enctype="multipart/form-data">
	<div class="row">
		<div class="col-12">
			<div class="list-group list-group-lg list-group-flush">
				<div class="list-group-item tableView">
					<div class="row align-items-center mb-4">
						<div class="col">
							<h4 class="mb-0" id="menuTitle">${pageTitle} <spring:message code="title.reply" /> <small>( ${nttVO.nttSj } )</small></h4>
						</div>
					</div>
					<div class="row align-items-center mb-3">
						<div class="col">
							<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
							<!-- 게시글 제목 -->
							<c:set var="title"><spring:message code="vaivBbsNtt.regist.nttSj" /></c:set>
							<label for="nttSj">${title } <span class="pilsu">*</span></label>
							<form:input path="nttSj" class="form-control" title="${title} ${inputTxt}" size="40" required="true" maxlength="300" value="RE:${nttVO.nttSj }" />
							<div><form:errors path="nttSj" cssClass="error" /></div>
						</div>
					</div>
					<div class="row align-items-center mb-3">
						<div class="col">
							<!-- 게시글 내용 -->
							<c:set var="title"><spring:message code="vaivBbsNtt.regist.nttCn" /></c:set>
							<label for="nttCn">${title } <span class="pilsu">*</span></label>
							<form:textarea path="nttCn" class="form-control" title="${title} ${inputTxt}" cols="30" rows="10" />
							<div><form:errors path="nttCn" cssClass="error" /></div>
						</div>
					</div>
					<c:if test="${bbsOptnVO.atchFileUseAt eq 'Y' }">
					<sec:authorize access="hasRole('${bbsAuthorVO.nttFileUploadAuthor }')">
					<!-- 첨부파일 사용 -->
					<div class="row align-items-center mb-3">
						<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/regist/nttOptnAtchFile.do">
							<c:param name="bbsTyCode" value="${bbsTyCode }"/>
							<c:param name="atchFilePermExtsn" value="${bbsOptnVO.atchFilePermExtsn }"/>
							<c:param name="atchFilePermMxmmCnt" value="${bbsOptnVO.atchFilePermMxmmCnt }"/>
						</c:import>
					</div>
					</sec:authorize>
					</c:if>
					
					<c:if test="${bbsOptnVO.secretUseAt eq 'Y' }">
					<sec:authorize access="hasRole('${bbsAuthorVO.secretUseAuthor }')">
					<!-- 비밀글 사용-->
					<div class="row align-items-center mb-3">
						<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/regist/nttOptnSecret.do">
							<c:param name="bbsTyCode" value="${bbsTyCode }"/>
						</c:import>
					</div>
					</sec:authorize>
					</c:if>				
					
					<c:if test="${bbsOptnVO.lcIndictUseAt eq 'Y' }">
					<sec:authorize access="hasRole('${bbsAuthorVO.lcIndictUseAuthor }')">
					<!-- 위치 사용 -->
					<div class="row align-items-center mb-3">
						<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/regist/nttOptnLotDspl.do">
							<c:param name="bbsTyCode" value="${bbsTyCode }"/>
						</c:import>
					</div>
					</sec:authorize>
					</c:if>
					
					<div class="row align-items-center">
						<div class="col">
							<a class="btn btn-secondary" href="javascript:;" onclick="fnSelectNttList();"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a>
						</div>
						<sec:authorize access="hasRole('${bbsAuthorVO.answerRegistAuthor }')">
						<div class="col-auto">
							<button type="button" id="nttAnswerRegistBtn" class="btn btn-primary" title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></button><!-- 등록 -->
						</div>
						</sec:authorize>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<input type="hidden" name="bbsTyCode" value="${bbsTyCode }"/>
	<form:hidden path="nttDp" value="${nttVO.nttDp + 1 }"/>
	<form:hidden path="upperNttId" value="${nttVO.nttId }"/>
	<form:hidden path="nttOrdr" value="${nttVO.nttOrdr}"/>
	<form:hidden path="nttInnerOrdr" value="${nttVO.nttInnerOrdr }"/>
</form:form>

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>
