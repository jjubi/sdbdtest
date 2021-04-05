<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : cntntsMain.jsp
  * @상세설명 : 컨텐츠 목록 표출 페이지
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
<c:set var="pageTitle"><spring:message code="vaivCntnts.title"/></c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/cntnts/cntnts.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath }/cmmn/adm/cntnts/cntntsMain.do" onsubmit="return false;">
				<fieldset>
					<legend>컨텐츠 검색 영역</legend>
					<input type="hidden" name="cntntsId"/>
					<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
					<div class="ui program-search" title="컨텐츠 검색 영역">
						<div class="btn-group float-left">
							<a class="btn btn-sm btn-basic" href="<c:url value='/cmmn/adm/cntnts/cntntsRegist.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
							</a>
						</div>
						<div class="search_inner clearfix float-right">
							<div class="fieldset">
								<div class="search-select">
									<span>
										<select name="searchCondition" class="form-control" title="검색조건 선택">
											<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>컨텐츠 코드</option>
											<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>컨텐츠 명</option>
											<option value="3" <c:if test="${searchVO.searchCondition eq '3'}">selected="selected"</c:if>>컨텐츠 내용</option>
										</select>
									</span>
								</div>
								<div class="search-text">
									<span>
										<input type="text" class="form-control pressEnter" name="searchKeyword" data-press="fnSelectCntntsList('1');" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
									</span>
								</div>
								<div class="search-btn">
									<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectCntntsList('1')">
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
					<caption>컨텐츠 목록 : 번호, 컨텐츠 코드, 컨텐츠 명, 사용 여부, 등록일, 비고로 구성</caption>
					<colgroup>
						<col style="width:8%;">
						<col style="width:12%;">
						<col style="width:auto;">
						<col style="width:10%;">
						<col style="width:15%;">
						<col style="width:20%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">
								<spring:message code="table.num" /> <!-- 번호 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCntnts.list.cntntsCode" /> <!-- 컨텐츠 코드 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCntnts.list.cntntsNm" /> <!-- 컨텐츠 명 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCntnts.list.useAt" /> <!-- 사용여부 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCntnts.list.registDe" /> <!-- 등록일 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCntnts.list.etc" /> <!-- 비고 -->
							</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty cntntsList or fn:length(cntntsList) == 0 }">
							<tr>
								<td class="text-center" colspan="6"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${cntntsList }" var="cntntsVO" varStatus="status">
							<tr>
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td><c:out value="${cntntsVO.cntntsCode }"/></td>
								<td>
									<a href="javascript:;" onclick="fnSelectCntnts('<c:out value="${cntntsVO.cntntsId }"/>');">
										<c:out value="${cntntsVO.cntntsNm }"/>
									</a>
								</td>
								<td><c:out value="${cntntsVO.useAt }"/></td>
								<td><c:out value="${cntntsVO.registDe }"/></td>
								<td>
									<a class="btn btn-sm btn-info" href="#cntntsHisListModal" data-toggle="modal" data-id="${cntntsVO.cntntsId }" title="컨텐츠 히스토리 목록 모달 호출...">
										히스토리
									</a>
									<div class="dropdown" style="display: inline-block;">
										<a href="#" class="dropdown-ellipses dropdown-toggle btn btn-sm btn-primary" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											미리보기<i class="fe fe-more-vertical"></i>
										</a>
										<div class="dropdown-menu dropdown-menu-right">
											<a href="${pageContext.request.contextPath }/cmmn/adm/cntnts/${cntntsVO.cntntsCode}/cntntsView${cntntsVO.cntntsId }.do" class="dropdown-item" target="_blank">
  												관리자
											</a>
											<a href="javascript:swAlert('준비중입니다.');" class="dropdown-item">
  												사용자
											</a>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 테이블 : e -->
			<!-- paging navigation -->
			<div class="text-center">
				<ul class="pagination justify-content-center">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectCntntsList"/>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- 컨텐츠 히스토리 목록 모달 -->
<jsp:include page="/WEB-INF/jsp/vaiv/cmmn/adm/cntnts/cntntsHisListModal.jsp"/>
