<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : comCodeMain.jsp
  * @상세설명 : 공통코드 표출 페이지
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
<c:set var="pageTitle"><spring:message code="vaivCode.title"/></c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/comCode/comCode.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath}/cmmn/adm/comCode/comCodeMain.do" onsubmit="return false;">
					<fieldset>
						<legend>공통코드 검색 영역</legend>
						<input type="hidden" name="code">
						<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
						<div class="ui program-search" title="공통코드 검색 영역">
							<div class="btn-group float-left">
								<a class="btn btn-sm btn-basic" href="<c:url value='/cmmn/adm/comCode/comCodeRegist.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
									<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="" selected="selected">선택</option>
												<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>><spring:message code="vaivCode.search.groupCode"/></option>
												<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>><spring:message code="vaivCode.search.code"/></option>
												<option value="3" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>><spring:message code="vaivCode.search.codeNm"/></option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input type="text" class="form-control pressEnter" name="searchKeyword" data-press="fnSelectComCodeList('1');" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectComCodeList('1')">
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
					<caption>공통코드 목록 : 번호, 그룹코드, 코드, 코드값, 코드명, 사용여부, 등록일로 구성</caption>
					<colgroup>
						<col style="width:10%;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:10%;">
						<col style="width:12%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">
								<spring:message code="table.num" /> <!-- 번호 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCode.list.groupCode" /> <!-- 그룹코드 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCode.list.code" /> <!-- 코드 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCode.list.codeValue" /> <!-- 코드값 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCode.list.codeNm" /> <!-- 코드명 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCode.list.useAt" /> <!-- 사용여부 -->
							</th>
							<th scope="col">
								<spring:message code="vaivCode.list.registDe" /> <!-- 등록일 -->
							</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty comCodeList or fn:length(comCodeList) == 0 }">
							<tr>
								<td class="text-center" colspan="7"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${comCodeList }" var="comCodeVO" varStatus="status">
							<tr>
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td><c:out value="${comCodeVO.groupCode }"/></td>
								<td>
									<a href="javascript:;" onclick="fnSelectComCode('<c:out value="${comCodeVO.code }"/>')">
										<c:out value="${comCodeVO.code }"/>
									</a>
								</td>
								<td><c:out value="${comCodeVO.codeValue }"/></td>
								<td><c:out value="${comCodeVO.codeNm }"/></td>
								<td><c:out value="${comCodeVO.useAt }"/></td>
								<td><c:out value="${comCodeVO.registDe }"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 테이블 : e -->
				<!-- paging navigation -->
				<div class="text-center">
					<ul class="pagination justify-content-center">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectComCodeList"/>
					</ul>
				</div>	
			</div>	
		</div>
	</div>
</div>
