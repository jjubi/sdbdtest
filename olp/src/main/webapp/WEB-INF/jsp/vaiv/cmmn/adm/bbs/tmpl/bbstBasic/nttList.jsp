<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstBasic > nttList.jsp
  * @상세설명 : basic 게시물 목록 표출 페이지 
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

<div class="container-fluid">
	<h1 class="header-title">${pageTitle}</h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath }/cmmn/adm/bbs/ntt/${bbsId}/nttList.do" onsubmit="return false;">
				<fieldset>
					<legend>게시물 검색 영역</legend>
					<input type="hidden" name="bbsTyCode" value="<c:out value="${bbsTyCode }"/>"/>
					<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
					<div class="ui program-search" title="게시물 검색 영역">
						<div class="btn-group float-left">
							<a class="btn btn-sm btn-basic" href="${pageContext.request.contextPath }/cmmn/adm/bbs/ntt/${bbsId}/nttRegist.do" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
							</a>
						</div>
						<div class="search_inner clearfix float-right">
							<div class="fieldset">
								<div class="search-select">
									<span>
										<select name="searchCondition" class="form-control" title="검색조건 선택">
											<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>제목</option>
											<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>내용</option>
										</select>
									</span>
								</div>
								<div class="search-text">
									<span>
										<input type="text" class="form-control pressEnter" name="searchKeyword" data-press="fnSelectNttList('1');" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
									</span>
								</div>
								<div class="search-btn">
									<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectNttList('1')">
										<i class="fa fa-search" aria-hidden="true"></i> 조회
									</button>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="ui program-search-form">
                    <!-- 게시글정보 -->
                    <div class="ui program-info">
                        <div class="ui program-count">
                            <span>총 게시물 
                            	<strong><c:out value="${paginationInfo.totalRecordCount }"/></strong> 개
                            </span>, 
                            <span class="ui program--division-line">페이지 
                            	<strong><c:out value="${paginationInfo.currentPageNo }"/></strong>
                                / <c:out value="${paginationInfo.totalPageCount }"/>
                            </span>
                        </div>
                    </div>
                </div>
			</form>
			<!-- 테이블 : s -->
			<div class="table-responsive">
				<table class="table table-bordered text-center">
					<caption>게시물 목록 : 번호, 제목, 작성자, 등록일, 조회수, 첨부파일로 구성</caption>
					<colgroup>
						<col style="width:8%;">
						<col style="width:auto;">
						<col style="width:12%;">
						<col style="width:12%;">
						<col style="width:8%;">
						<c:if test="${bbsOptnVO.atchFileUseAt eq 'Y' }">
						<col style="width:12%;">
						</c:if>
					</colgroup>
					<thead>
						<tr>
							<th scope="col">
								<spring:message code="table.num" /> <!-- 번호 -->
							</th>
							<th scope="col">
								<spring:message code="vaivBbsNtt.list.nttSj" /> <!-- 제목 -->
							</th>
							<th scope="col">
								<spring:message code="vaivBbsNtt.list.registNm" /> <!-- 작성자 -->
							</th>
							<th scope="col">
								<spring:message code="vaivBbsNtt.list.registDe" /> <!-- 등록일 -->
							</th>
							<th scope="col">
								<spring:message code="vaivBbsNtt.list.nttRdcnt" /> <!-- 조회수 -->
							</th>
							<c:if test="${bbsOptnVO.atchFileUseAt eq 'Y' }">
							<th scope="col">
								<spring:message code="vaivBbsNtt.list.atchFileId" /> <!-- 첨부파일 -->
							</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
					<c:if test="${bbsOptnVO.noticeUseAt eq 'Y' && not empty noticeNttList}">
						<c:forEach items="${noticeNttList }" var="notiNttVO" varStatus="status">
							<tr>
								<td><font color="#ff0000"><i class="fas fa-bullhorn"></i></font></td>
								<td class="text-left">
									<sec:authorize access="hasRole('${bbsAuthorVO.nttRedngAuthor }')">
									<a class="depth-${notiNttVO.nttDp} font-weight-bolder text-reset" href="javascript:;" onclick="fnSelectNtt('<c:out value="${notiNttVO.nttId }"/>');">
										<c:out value="${notiNttVO.nttSj }" escapeXml="false"/>
									</a>
									</sec:authorize>
									<sec:authorize access="!hasRole('${bbsAuthorVO.nttRedngAuthor }')">
									<span class="depth-${notiNttVO.nttDp} font-weight-bolder">
										<c:out value="${notiNttVO.nttSj }" escapeXml="false"/>
									</span>
									</sec:authorize>
								</td>
								<td class="font-weight-bolder"><c:out value="${notiNttVO.registNm }"/></td>
								<td class="font-weight-bolder"><c:out value="${notiNttVO.registDe }"/></td>
								<td class="font-weight-bolder"><c:out value="${notiNttVO.nttRdcnt }"/></td>
								<c:if test="${bbsOptnVO.atchFileUseAt eq 'Y' }">
								<td>
									<sec:authorize access="hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
									<c:import url="/cmmn/com/cfc/nttFileList.do">
										<c:param name="atchFileId" value="${notiNttVO.atchFileId }"/>
										<c:param name="nttId" value="${notiNttVO.nttId }"/>
										<c:param name="loadType" value="listFileDown"/>
									</c:import>
									</sec:authorize>
									<sec:authorize access="!hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
										다운로드 권한 없음.
									</sec:authorize>
								</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty nttList or fn:length(nttList) == 0 }">
						<tr>
							<td class="text-center" colspan="${bbsOptnVO.atchFileUseAt eq 'Y' ? '6' : '5' }"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
					<c:forEach items="${nttList }" var="nttVO" varStatus="status">
						<tr>
							<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
							<td class="text-left">
							<c:if test="${nttVO.secretAt eq 'N' }">
								<sec:authorize access="hasRole('${bbsAuthorVO.nttRedngAuthor }')">
								<a class="depth-${nttVO.nttDp} text-reset" href="javascript:;" onclick="fnSelectNtt('<c:out value="${nttVO.nttId }"/>');">
									<c:if test="${nttVO.nttDp ne 1 }">
										<i class="fas fa-reply transform-180"></i> 
									</c:if>
									<c:out value="${nttVO.nttSj }" escapeXml="false"/>
								</a>
								</sec:authorize>
								<sec:authorize access="!hasRole('${bbsAuthorVO.nttRedngAuthor }')">
								<span class="depth-${nttVO.nttDp}">
									<c:if test="${nttVO.nttDp ne 1 }">
										<i class="fas fa-reply transform-180"></i> 
									</c:if>
									<c:out value="${nttVO.nttSj }" escapeXml="false"/>
								</span>
								</sec:authorize>
							</c:if>
							
							<c:if test="${nttVO.secretAt eq 'Y' }">
								<sec:authorize access="hasRole('${bbsAuthorVO.nttRedngAuthor }')">
								<a class="depth-${nttVO.nttDp} text-reset" href="javascript:;" onclick="fnSecretSelectNtt('<c:out value="${nttVO.nttId }"/>');">
									<c:if test="${nttVO.nttDp ne 1 }">
										<i class="fas fa-reply transform-180"></i>
									</c:if>
									<i class="fas fa-lock"></i> 비밀글 입니다.
								</a>
								</sec:authorize>
								<sec:authorize access="!hasRole('${bbsAuthorVO.nttRedngAuthor }')">
								<span class="depth-${nttVO.nttDp}">
									<c:if test="${nttVO.nttDp ne 1 }">
										<i class="fas fa-reply transform-180"></i>
									</c:if>
									<i class="fas fa-lock"></i> 비밀글 입니다.
								</span>
								</sec:authorize>
							</c:if>
							</td>
							<td><c:out value="${nttVO.registNm }"/></td>
							<td><c:out value="${nttVO.registDe }"/></td>
							<td><c:out value="${nttVO.nttRdcnt }"/></td>
							<c:if test="${bbsOptnVO.atchFileUseAt eq 'Y' }">
							<td>
								<sec:authorize access="hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
								<c:import url="/cmmn/com/cfc/nttFileList.do">
									<c:param name="atchFileId" value="${nttVO.atchFileId }"/>
									<c:param name="nttId" value="${nttVO.nttId }"/>
									<c:param name="loadType" value="listFileDown"/>
								</c:import>
								</sec:authorize>
								<sec:authorize access="!hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
									다운로드 권한 없음.
								</sec:authorize>
							</td>
							</c:if>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 테이블 : e -->
			<!-- paging navigation -->
			<div class="text-center">
				<ul class="pagination justify-content-center">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectNttList"/>
				</ul>
			</div>
		</div>
	</div>
</div>
