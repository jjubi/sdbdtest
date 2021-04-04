<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : ipCtrlMain.jsp
  * @상세설명 : IP 정보 목록 표출 페이지
  * @작성일시 : 2021. 01. 20
  * @작 성 자 : jeon
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2021.01.20   jeon	  최초등록
  * @ 
  */
%>
<script src="${pageContext.request.contextPath}/static/js/vaiv/cmmn/adm/perm/ipCtrl.js"></script>
<c:set var="pageTitle">
	<c:choose>
		<c:when test="${permAt eq 'Y'}">
			<spring:message code="vaivIpCtrl.perm.list"/>
		</c:when>
		<c:otherwise>
			<spring:message code="vaivIpCtrl.limit.list"/>
		</c:otherwise>
	</c:choose>
</c:set>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form name="listForm" id="listForm" method="post" action="${pageContext.request.contextPath}/cmmn/adm/perm/ipCtrlMain.do">
					<fieldset>
						<legend>IP 관리 검색 영역</legend>
						<input type="hidden" name="permIpId" id="permIpId" >
						<input type="hidden" name="permAt" value="<c:out value='${permAt}'/>">
						<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
						<div class="ui program-search" title="IP 관리 검색 영역">
							<div class="btn-group float-left">
								<a class="btn btn-sm btn-basic" href="<c:url value='/cmmn/adm/perm/ipCtrlRegist.do?permAt=${permAt}'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
									<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>><spring:message code="vaivIpCtrl.search.conectIp"/></option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control pressEnter" name="searchKeyword" data-press="fnSelectIpCtrlList('1');" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectIpCtrlList('1')">
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
					<caption>${pageTitle} 목록 : 번호, 접속IP, 등록일로 구성</caption>
					<colgroup>
						<col style="width:10%;">
						<col style="width:auto;">
						<col style="width:12%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">
								<spring:message code="table.num" /> <!-- 번호 -->
							</th>
							<th scope="col">
								<spring:message code="vaivIpCtrl.list.conectIp" /> <!-- 접속 IP -->
							</th>
							<th scope="col">
								<spring:message code="vaivIpCtrl.list.registDe" /> <!-- 등록일 -->
							</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty ipCrtlList or fn:length(ipCrtlList) == 0 }">
							<tr>
								<td colspan="3"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${ipCrtlList }" var="ipCtrlVO" varStatus="status">
							<tr>
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td>
									<a href="javascript:;" onclick="fnSelectIpCtrl('<c:out value="${ipCtrlVO.permIpId }"/>')">
										<c:out value="${ipCtrlVO.connectIp }"/>
									</a>
								</td>
								<td><c:out value="${ipCtrlVO.registDe }"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 테이블 : e -->
				<!-- paging navigation -->
				<div class="text-center">
					<ul class="pagination justify-content-center">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectIpCtrlList"/>
					</ul>
				</div>	
			</div>
		</div>
	</div>
</div>


