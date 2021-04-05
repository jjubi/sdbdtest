<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : sysCmptnMain.jsp
  * @상세설명 : 시스템 구성 목록 표출 페이지
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
<c:set var="pageTitle"><spring:message code="vaivSysCmptn.title"/></c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/sys/cmptn/sysCmptn.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath}/cmmn/adm/sys/cmptn/sysCmptnMain.do" onsubmit="return false;">
					<fieldset>
						<legend>시스템구성정보 검색 영역</legend>
						<input type="hidden" name="sysCmptnCode">
						<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
						<div class="ui program-search" title="시스템구성정보 검색 영역">
							<div class="btn-group float-left">
								<a class="btn btn-sm btn-basic" href="<c:url value='/cmmn/adm/sys/cmptn/sysCmptnRegist.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
									<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>><spring:message code="vaivSysCmptn.search.sysCmptnCode"/></option>
												<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>><spring:message code="vaivSysCmptn.search.sysCmptnNm"/></option>
												<option value="3" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>><spring:message code="vaivSysCmptn.search.sysCmptnValue"/></option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control pressEnter" name="searchKeyword" data-press="fnSelectSysCmptnList('1')" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectSysCmptnList('1')">
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
					<caption>시스템구성정보 목록 : 번호, 시스템 구성 코드, 시스템 구성 명, 사용여부, 등록일로 구성</caption>
					<colgroup>
						<col style="width:10%;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:10%;">
						<col style="width:12%;">
					</colgroup>
					<thead>
						<tr>
							<th class="text-center" scope="col">
								<spring:message code="table.num" /> <!-- 번호 -->
							</th>
							<th class="text-center" scope="col">
								<spring:message code="vaivSysCmptn.list.sysCmptnCode" /> <!-- 시스템 구성 코드 -->
							</th>
							<th class="text-center" scope="col">
								<spring:message code="vaivSysCmptn.list.sysCmptnNm" /> <!-- 시스템 구성 명 -->
							</th>
							<th class="text-center" scope="col">
								<spring:message code="vaivSysCmptn.list.useAt" /> <!-- 사용여부 -->
							</th>
							<th class="text-center" scope="col">
								<spring:message code="vaivSysCmptn.list.registDe" /> <!-- 등록일 -->
							</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty sysCmptnList or fn:length(sysCmptnList) == 0 }">
							<tr>
								<td class="text-center" colspan="5"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${sysCmptnList }" var="sysCmptnVO" varStatus="status">
							<tr>
								<td class="text-center">${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td class="text-center">
									<a href="javascript:;" onclick="fnSelectSysCmptn('<c:out value="${sysCmptnVO.sysCmptnCode }"/>')">
										<c:out value="${sysCmptnVO.sysCmptnCode }"/>
									</a>
								</td>
								<td class="text-center"><c:out value="${sysCmptnVO.sysCmptnNm }"/></td>
								<td class="text-center"><c:out value="${sysCmptnVO.useAt }"/></td>
								<td class="text-center"><c:out value="${sysCmptnVO.registDe }"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 테이블 : e -->
				<!-- paging navigation -->
				<div class="text-center">
					<ul class="pagination justify-content-center">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectSysCmptnList"/>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

