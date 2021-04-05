<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : sysExcpMain.jsp
  * @상세설명 : exception 관리 목록 표출 페이지
  * @작성일시 : 2021. 01. 29
  * @작 성 자 : kmk
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2021.01.29   kmk	  최초등록
  * @ 
  * 
  */
%>
<%-- <spring:message code="vaivSysCmptn.title"/> --%>
<c:set var="pageTitle">
Exception 관리
</c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/sys/excp/sysExcp.js"></script>
<script src="${pageContext.request.contextPath }/static/js/plugins/xlsx/xlsx.full.min.js"></script>
<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body" id="ex">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath}/cmmn/adm/sys/excp/sysExcpMain.do">
					<fieldset>
						<legend>Exception관리 검색 영역</legend>
						<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
						<div class="ui program-search" title="Exception관리 검색 영역">
                            <div class="btn-group float-left">
<!-- 							     <input type="file" name="bus" id="bus"> -->
<!-- 							     <a href="javascript:getExcelData();">엑셀 업로드 확인</a> -->
<%-- 								<a class="btn btn-sm btn-info" href="javascript:excelDownloadToHTML('예외관리','Exception관리','excpDownloadTable');" title="<spring:message code="button.excel" /> <spring:message code="input.button" />"> --%>
								<a class="btn btn-sm btn-info" href="javascript:exportExcel('예외관리.xlsx','Exception관리','table','ex');" title="<spring:message code="button.excel" /> <spring:message code="input.button" />">
									<spring:message code="button.excel" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>예외명</option>
												<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>예외 발생 클래스</option>
												<option value="3" <c:if test="${searchVO.searchCondition eq '3'}">selected="selected"</c:if>>예외 발생 메소드</option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control pressEnter" name="searchKeyword" data-press="fnSelectSysExcpList('1')" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectSysExcpList('1')">
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
				<table class="table table-bordered text-center" id="excpDownloadTable">
					<caption>Exception관리 목록 : 번호, 예외명, 예외발생클래스, 예외발생메소드, 예외발생일자로 구성</caption>
					<colgroup>
						<col style="width:10%;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:10%;">
						<col style="width:12%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="table.num" /></th> <!-- 번호 -->
							<th scope="col">
								예외명
							</th>
							<th scope="col">
								예외 발생 클래스
							</th>
							<th scope="col">
								예외 발생 메소드
							</th>
							<th scope="col">
								예외발생일자
							</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty sysExcpList or fn:length(sysExcpList) == 0 }">
							<tr>
								<td colspan="5"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${sysExcpList }" var="sysExcpVO" varStatus="status">
							<tr>
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td>
										<a href="javascript:;" onclick="fnSelectSysExcp('<c:out value="${sysExcpVO.exceptionSeqNo }"/>')">
										<c:out value="${sysExcpVO.exceptionNm }"/>
									</a>
								</td>
								<td><c:out value="${fn:replace(sysExcpVO.exceptionClass, 'class ', '') }"/></td>
								<td><c:out value="${sysExcpVO.exceptionMethod }"/></td>
								<td><c:out value="${sysExcpVO.registDe }"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
				<!-- paging navigation -->
				<div class="text-center">
					<ul class="pagination justify-content-center">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectSysExcpList"/>
					</ul>
				</div>		
			</div>
		</div>
	</div>
</div>
