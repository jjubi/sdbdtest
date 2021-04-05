<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : comCodeGroupMain.jsp
  * @상세설명 : 공통코드 그룹코드 표출 페이지
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

<c:set var="pageTitle"><spring:message code="vaivCodeGrp.title"/></c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/comCode/group/comCodeGroup.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath}/cmmn/adm/comCode/group/comCodeGroupMain.do" onsubmit="return false;">
					<fieldset>
						<legend>공통코드 그룹 검색 영역</legend>
						<input type="hidden" name="groupCode">
						<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
						<div class="ui program-search" title="공통코드 그룹 검색 영역">
							<div class="btn-group float-left">
								<a class="btn btn-sm btn-basic" href="<c:url value='/cmmn/adm/comCode/group/comCodeGroupRegist.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
									<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="" selected="selected">선택</option>
												<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>><spring:message code="vaivCodeGrp.search.groupCode"/></option>
												<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>><spring:message code="vaivCodeGrp.search.groupNm"/></option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control pressEnter" name="searchKeyword" data-press="fnSelectComCodeGroupList('1');" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectComCodeGroupList('1')">
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
					<caption>공통코드 그룹 목록 : 번호, 그룹코드, 그룹코드명, 사용여부, 등록일로 구성</caption>
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
								<spring:message code="vaivCodeGrp.list.groupCode" /> <!-- 그룹코드 -->
							</th>
							<th class="text-center" scope="col">
								<spring:message code="vaivCodeGrp.list.groupNm" /> <!-- 그룹코드명 -->
							</th>
							<th class="text-center" scope="col">
								<spring:message code="vaivCodeGrp.list.useAt" /> <!-- 사용여부 -->
							</th>
							<th class="text-center" scope="col">
								<spring:message code="vaivCodeGrp.list.registDe" /> <!-- 등록일 -->
							</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty comCodeGroupList or fn:length(comCodeGroupList) == 0 }">
							<tr>
								<td class="text-center" colspan="5"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${comCodeGroupList }" var="comCodeGroupVO" varStatus="status">
							<tr>
								<td class="text-center">${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td class="text-center">
									<a href="javascript:;" onclick="fnSelectComCodeGroup('<c:out value="${comCodeGroupVO.groupCode }"/>')">
										<c:out value="${comCodeGroupVO.groupCode }"/>
									</a>
								</td>
								<td class="text-center"><c:out value="${comCodeGroupVO.groupNm }"/></td>
								<td class="text-center"><c:out value="${comCodeGroupVO.useAt }"/></td>
								<td class="text-center"><c:out value="${comCodeGroupVO.registDe }"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 테이블 : e -->
				<!-- paging navigation -->
				<div class="text-center">
					<ul class="pagination justify-content-center">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectComCodeGroupList"/>
					</ul>
				</div>	
			</div>	
		</div>
	</div>
</div>
