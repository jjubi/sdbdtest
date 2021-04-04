<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstAccordian > nttDetail.jsp
  * @상세설명 : accordian 게시물 상세 페이지 
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
<% pageContext.setAttribute("rlcn", "\n"); %>
<c:set var="pageTitle"><c:out value="${bbsVO.bbsNm }"/></c:set>

<div class="row">
	<div class="col-12">
		<div class="list-group list-group-lg list-group-flush">
			<div class="list-group-item tableView">
				<div class="row align-items-center mb-4">
					<div class="col">
						<h4 class="mb-0" id="menuTitle">${pageTitle} <spring:message code="title.detail" />
							<c:if test="${not empty parentNttVO }">
								<small>(상단 글 제목 : <c:out value="${parentNttVO.nttSj}"/>)</small>
							</c:if>
						</h4>
					</div>
				</div>
				<div class="row align-items-center mb-4">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<div class="mb-3">
									<div class="row align-items-center pt-3">
								    	<div class="col">
								    		<h5 class="pl-1">
								    			<c:out value="${nttVO.nttSj }"/>
								    		</h5>
								    	</div>
							    		<p class="card-text small text-muted pr-5">
											<span class="far fa-clock"></span> 
											<time datetime="${nttVO.registDe }">${nttVO.registDe }</time>
										</p>
							    	</div>
						    	</div>
						    	<div class="mb-3">
						    		<c:import url="/cmmn/com/cfc/nttFileList.do">
										<c:param name="atchFileId" value="${nttVO.atchFileId }"/>
										<c:param name="nttId" value="${nttVO.nttId }"/>
										<c:param name="loadType" value="viewFileList"/>
									</c:import>
						    	</div>
						    	<hr>
						    	<p class="mb-3 pl-2">
						    		${fn:replace(nttVO.nttCn, rlcn, '<br/>') }
						    	</p>
						    	<c:if test="${nttVO.lcIndictAt eq 'Y' }">
						    		<div class="mb-3 text-center">
							    		<c:import url="/cmmn/com/cuc/mapView.do">
							    			<c:param name="lat" value="${nttVO.adresLa }"/>
							    			<c:param name="lng" value="${nttVO.adresLo }"/>
							    			<c:param name="addr" value="${nttVO.adres }"/>
							    		</c:import>
							    	</div>
						    	</c:if>
								<c:if test="${!empty nttVO.koglTy }">
								<!-- 공공누리 뷰 -->
									<c:import url="${pageContext.request.contextPath }/cmmn/com/cuc/koglView.do">
										<c:param name="koglTy" value="${nttVO.koglTy }"></c:param>
									</c:import>
								</c:if>
								
								<c:if test="${!empty nttVO.cclTy }">
								<!-- ccl 뷰 -->
									<c:import url="${pageContext.request.contextPath }/cmmn/com/cuc/cclView.do">
										<c:param name="cclTy" value="${nttVO.cclTy }"></c:param>
									</c:import>
								</c:if>
						    	<c:if test="${bbsOptVO.cmtUseAt eq 'Y' }">
						    		<sec:authorize access="hasRole('${bbsAuthVO.cmtRedngAuthor }')">
							    		<hr>
							    		<c:import url="/cmmn/adm/bbs/ntt/${bbsId }/view/nttOptnCmtView.do">
											<c:param name="bbsTyCode" value="${bbsTyCode }"/>
											<c:param name="nttId" value="${nttVO.nttId }"/>
										</c:import>
									</sec:authorize>
						    	</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="row align-items-center">
					<div class="col">
						<a class="btn btn-secondary" href="javascript:;" onclick="fnSelectNttList();"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a>
					</div>
					<c:if test="${bbsOptVO.answerUseAt eq 'Y' && nttVO.noticeAt eq 'N' && nttVO.nttDp le 3}">
					<sec:authorize access="hasRole('${bbsAuthVO.answerRegistAuthor }')">
					<div class="col-auto">
						<a href="/cmmn/adm/bbs/ntt/${bbsId }/nttAnswerRegist.do?nttId=<c:out value="${nttVO.nttId }"/>" class="btn btn-primary" title="<spring:message code="button.reply" /> <spring:message code="input.button" />"><spring:message code="button.reply" /></a><!-- 답글 -->
					</div>
					</sec:authorize>
					</c:if>
					<sec:authorize access="hasRole('${bbsAuthVO.nttDeleteAuthor }')">
					<c:if test="${loginVO.uniqId eq nttVO.registId || topAdmin == true}">
					<div class="col-auto">
						<button type="button" onclick="fnDeleteNtt('<c:out value="${nttVO.nttId }"/>');" class="btn btn-danger" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button><!-- 삭제 -->
					</div>
					</c:if>
					</sec:authorize>
					<sec:authorize access="hasRole('${bbsAuthVO.nttUpdtAuthor }')">
					<c:if test="${loginVO.uniqId eq nttVO.registId || topAdmin == true}">
					<div class="col-auto">
						<a href="/cmmn/adm/bbs/ntt/${bbsId}/nttModify.do?nttId=<c:out value="${nttVO.nttId }"/>" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /></a><!-- 수정 -->
					</div>
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