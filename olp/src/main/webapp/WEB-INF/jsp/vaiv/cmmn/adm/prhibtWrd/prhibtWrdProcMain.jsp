<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : prhibtWrdProcMain.jsp
  * @상세설명 : 금지단어 관리 표출 페이지
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
<c:set var="pageTitle"><spring:message code="vaivPrhibtWrd.title"/></c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/prhibtWrd/prhibtWrd.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath }/cmmn/adm/prhibtWrd/prhibtWrdProcMain.do" onsubmit="return false;">
				<fieldset>
					<legend>금지단어 관리 검색 영역</legend>
					<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}" >
					<div class="ui program-search" title="금지단어 관리 검색 영역">
						<div class="search_inner clearfix left">
							<div class="fieldset">
								<input class="form-control form-control-sm" id="insertKeyword" name="insertKeyword" type="text" placeholder="등록할 금칙어 입력 ..." title="검색어 입력" value="" maxlength="155">
							</div>
						</div>
						<div class="btn-group float-left">
							<a class="btn btn-sm btn-basic" href="javascript:;" onclick="fnInsertPrhibtWrd()" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
							</a>
							<a class="btn btn-sm btn-info" href="javascript:;" onclick="fnExcelDataUpload();" title="<spring:message code="button.excel" /> <spring:message code="input.button" />">
								<spring:message code="button.excel" />
							</a>
						</div>
						<div class="search_inner clearfix right">
							<div class="fieldset">
								<div class="search-select">
									<span>
										<select class="form-control" name="useAt">
											<option value="">전체</option>
											<option value="Y" <c:if test="${searchVO.useAt eq 'Y'}">selected="selected"</c:if>>사용</option>
											<option value="N" <c:if test="${searchVO.useAt eq 'N'}">selected="selected"</c:if>>사용안함</option>
										</select>
									</span>
									<span>
										<select name="searchCondition" class="form-control" title="검색조건 선택">
											<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>금지 단어</option>
										</select>
									</span>
								</div>
								<div class="search-text">
									<span>
										<input class="form-control pressEnter" name="searchKeyword" data-press="fnSelectPrhibtWrdList('1');" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">
									</span>
								</div>
								<div class="search-btn">
									<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectPrhibtWrdList('1')">
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
			<table class="table table-bordered text-center">
				<caption>금지단어 목록 : 번호, 금지단어, 등록일자, 사용여부, 비고로 구성</caption>
				<colgroup>
					<col style="width:8%;">
					<col style="width:auto;">
					<col style="width:10%;">
					<col style="width:15%;">
					<col style="width:15%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">
							<spring:message code="table.num" /> <!-- 번호 -->
						</th>
						<th scope="col">
							<spring:message code="vaivPrhibtWrd.list.prhibtWrd" /> <!-- 금지 단어 -->
						</th>
						<th scope="col">
							<spring:message code="vaivPrhibtWrd.list.registDe" /> <!-- 등록일 -->
						</th>
						<th scope="col">
							<spring:message code="vaivPrhibtWrd.list.useAt" /> <!-- 사용여부 -->
						</th>
						<th scope="col">
							<spring:message code="vaivPrhibtWrd.list.etc" /> <!-- 비고 -->
						</th>
					</tr>
				</thead>
				<tbody id="prhibtWrdTbody">
					<c:if test="${empty prhibtWrdList or fn:length(prhibtWrdList) == 0 }">
						<tr>
							<td colspan="5"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
					<c:forEach items="${prhibtWrdList }" var="prhibtWrdVO" varStatus="status">
						<tr class="prhibtTr${prhibtWrdVO.prhibtWrdSeqNo }">
							<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
							<td><c:out value="${prhibtWrdVO.prhibtWrd }"/></td>
							<td><c:out value="${prhibtWrdVO.registDe }"/></td>
							<td>
								<div class="custom-control custom-switch text-center">
									<input type="checkbox" class="custom-control-input" id="useAt${prhibtWrdVO.prhibtWrdSeqNo }" value="${prhibtWrdVO.prhibtWrdSeqNo }" <c:if test="${prhibtWrdVO.useAt == 'Y'}">checked="checked"</c:if>>
									<label class="custom-control-label" for="useAt${prhibtWrdVO.prhibtWrdSeqNo }"></label>
								</div>
							</td>
							<td>
								<a class="btn btn-sm btn-info" href="javascript:;" onclick="fnSelectPrhibtWrd('<c:out value="${prhibtWrdVO.prhibtWrdSeqNo }"/>');">수정</a>
								<a class="btn btn-sm btn-danger" href="javascript:;" onclick="fnDeletePrhibtWrd('<c:out value="${prhibtWrdVO.prhibtWrdSeqNo }"/>');">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 테이블 : e -->
			<!-- paging navigation -->
			<div class="text-center">
				<ul class="pagination justify-content-center">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectPrhibtWrdList"/>
				</ul>
			</div>	
		</div>
	</div>
</div>

