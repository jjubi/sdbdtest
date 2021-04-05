<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstBasic > nttModify.jsp
  * @상세설명 : basic 게시물 수정 페이지 
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

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="nttVO" action="${pageContext.request.contextPath}/cmmn/adm/bbs/ntt/${bbsId}/updateNtt.do" method="post" enctype="multipart/form-data" onsubmit="return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsNtt.regist.nttSj" /></c:set>
								<label for="nttSj">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<input id="nttSj" name="nttSj" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="300" value="<c:out value="${nttVO.nttSj }" escapeXml="false" />">
								<div><form:errors path="nttSj" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsNtt.regist.nttCn" /></c:set>
								<label for="nttCn">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<textarea id="nttCn" name="nttCn" class="form-control" title="${title} ${inputTxt}" cols="30" rows="10" ><c:out value="${nttVO.nttCn }" escapeXml="false"/></textarea>
								<div><form:errors path="nttCn" cssClass="error" /></div>
							</div>
						</div>
						<c:if test="${bbsOptnVO.atchFileUseAt eq 'Y' }">
						<sec:authorize access="hasRole('${bbsAuthorVO.nttFileUploadAuthor }')">
						<!-- 첨부파일 사용 -->
						<div class="form-group">
							<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/update/nttOptnAtchFile.do">
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
						<div class="form-group">
							<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/update/nttOptnSecret.do">
								<c:param name="bbsTyCode" value="${bbsTyCode }"/>
							</c:import>
						</div>
						</sec:authorize>
						</c:if>				
						
						<c:if test="${bbsOptnVO.noticeUseAt eq 'Y' }">
						<sec:authorize access="hasRole('${bbsAuthorVO.noticeUseAuthor }')">
						<!-- 공지글 사용 -->
						<div class="form-group">
							<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/update/nttOptnNotice.do">
								<c:param name="bbsTyCode" value="${bbsTyCode }"/>
							</c:import>
						</div>
						</sec:authorize>
						</c:if>
						
						<c:if test="${bbsOptnVO.lcIndictUseAt eq 'Y' }">
						<sec:authorize access="hasRole('${bbsAuthorVO.lcIndictUseAuthor }')">
						<!-- 위치 사용 -->
						<div class="form-group">
							<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/update/nttOptnLotDspl.do">
								<c:param name="bbsTyCode" value="${bbsTyCode }"/>
							</c:import>
						</div>
						</sec:authorize>
						</c:if>
						
						<c:if test="${bbsOptnVO.popupUseAt eq 'Y' }">
						<sec:authorize access="hasRole('${bbsAuthorVO.popupUseAuthor }')">
						<!-- 팝업 사용 -->
						<div class="form-group">
							<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/update/nttOptnPopup.do">
								<c:param name="bbsTyCode" value="${bbsTyCode }"/>
							</c:import>
						</div>
						</sec:authorize>
						</c:if>
						
						<c:if test="${bbsOptnVO.cclUseAt eq 'Y' }">
						<!-- ccl 사용 -->
							<div class="form-group">
								<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/update/nttOptnCcl.do">
									<c:param name="bbsTyCode" value="${bbsTyCode }"/>
								</c:import>
							</div>
						</c:if>
						
						<!-- 공공누리 사용 -->
						<c:if test="${bbsOptnVO.koglUseAt eq 'Y' }">
							<div class="form-group">
								<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/update/nttOptnKogl.do">
									<c:param name="bbsTyCode" value="${bbsTyCode }"/>
								</c:import>
							</div>
						</c:if>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectNttList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<c:if test="${nttVO.nttAnswerYn eq 'Y' }">
							<c:set var="updateAuthor" value="${bbsAuthorVO.answerUpdtAuthor }"/>
						</c:if>
						<c:if test="${nttVO.nttAnswerYn ne 'Y' }">
							<c:set var="updateAuthor" value="${bbsAuthorVO.nttUpdtAuthor }"/>
						</c:if>
						<sec:authorize access="hasRole('${updateAuthor }')">
						<c:if test="${loginVO.uniqId eq nttVO.registId || isAdmin == true}">
						<div class="col-auto">
							<button type="submit" id="nttUpdateBtn" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" /><!-- 수정 -->
							</button>
						</div>
						</c:if>
						</sec:authorize>
					</div>
				</div>
				<form:hidden path="nttId"/>
				<form:hidden path="atchFileId"/>
				<input type="hidden" name="bbsTyCode" value="${bbsTyCode }"/>
			</form:form>			
		</div>
	</div>
</div>
	
<input type="hidden" id="nttAnswerYn" value="${nttVO.nttAnswerYn }">

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>
