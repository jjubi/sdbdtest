<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : csnstMain.jsp
  * @상세설명 : 만족도 조사 표출 페이지
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
<c:set var="pageTitle"><spring:message code="vaivCsnst.title"/></c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/csnst/csnst.js"></script>

<div class="container-fluid">
	<h1 class="header-title">만족도 조사</h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath}/cmmn/adm/csnst/csnstMain.do" onsubmit="return false;">
					<fieldset>
						<legend>만족도 조사 검색 영역</legend>
						<input type="hidden" name="csnstSeqNo">
						<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
						<div class="ui program-search" title="만족도 조사 검색 영역">
							<div class="btn-group float-left">
								<a class="btn btn-sm btn-info" href="javascript:swAlert('확인','준비중입니다.','info');" title="<spring:message code="button.excel" /> <spring:message code="input.button" />">
<%-- 								<a class="btn btn-sm btn-info" href="<c:url value='/cmmn/adm/csnst/csnstExcelDownload.do'/>" title="<spring:message code="button.excel" /> <spring:message code="input.button" />"> --%>
									<spring:message code="button.excel" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>메뉴명</option>
												<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>만족도평가</option>
												<option value="3" <c:if test="${searchVO.searchCondition eq '3'}">selected="selected"</c:if>>만족도점수</option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input type="text" class="form-control pressEnter" name="searchKeyword" data-press="fnSelectCsnstList('1');" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectCsnstList('1')">
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
					<caption>만족도조사 목록 : 번호, 메뉴명(게시판, 게시물, 컨텐츠), 고객만족도평가, 페이지URL, 접속IP, 고객만족도 점수, 등록일로 구성</caption>
					<colgroup>
						<col style="width:5%;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:5%;">
						<col style="width:12%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="vaivCsnst.list.num"/></th>
							<th scope="col"><spring:message code="vaivCsnst.list.pageNm"/></th>
							<th scope="col"><spring:message code="vaivCsnst.list.csnstDc"/></th>
							<th scope="col"><spring:message code="vaivCsnst.list.pgeUrl"/></th>
							<th scope="col"><spring:message code="vaivCsnst.list.conectIp"/></th>
							<th scope="col"><spring:message code="vaivCsnst.list.csnstScore"/></th>
							<th scope="col"><spring:message code="vaivCsnst.list.registDe"/></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty csnstList or fn:length(csnstList) == 0 }">
							<tr>
								<td class="text-center" colspan="7"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${csnstList }" var="csnstVO" varStatus="status">
							<tr>
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td>
									<c:out value="${csnstVO.menuNm }"/>
									<c:if test="${not empty csnstVO.bbsId }">
										&gt; <c:out value="${csnstVO.bbsNm }"/>
									</c:if>
									<c:if test="${not empty csnstVO.nttId }">
										&gt; <c:out value="${csnstVO.nttSj }"/>
									</c:if>
									<c:if test="${not empty csnstVO.cntntsId }">
										&gt; <c:out value="${csnstVO.cntntsNm }"/>
									</c:if>
								</td>
								<td><c:out value="${csnstVO.csnstDc }"/></td>
								<td><c:out value="${csnstVO.pgeUrl }"/></td>
								<td><c:out value="${csnstVO.conectIp }"/></td>
								<td><c:out value="${csnstVO.csnstScore }"/></td>
								<td><c:out value="${csnstVO.registDe }"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 테이블 : e -->
				<!-- paging navigation -->
				<div class="text-center">
					<ul class="pagination justify-content-center">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectCsnstList"/>
					</ul>
				</div>	
			</div>
		</div>
	</div>
</div>
