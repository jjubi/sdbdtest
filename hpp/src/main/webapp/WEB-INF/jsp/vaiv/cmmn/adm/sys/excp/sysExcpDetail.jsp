<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : sysCmptnModify.jsp
  * @상세설명 : 시스템 구성 정보 수정 페이지
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
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/sys/excp/sysExcp.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.detail" /></h1>
			<div class="card-wrap">
				<div class="form-horizontal contents-form">
					<div class="form-group">
						<div class="col-sm-12 col-md-2 control-label col-lg-2">
							<label>예외명</label>
						</div>
						<div class="col-sm-12 col-md-10">
							<c:out value="${sysExcpVO.exceptionNm }"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 col-md-2 control-label col-lg-2">
							<label>예외 발생 클래스</label>
						</div>
						<div class="col-sm-12 col-md-10">
							<c:out value="${sysExcpVO.exceptionClass }"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 col-md-2 control-label col-lg-2">
							<label>예외 발생 메소드</label>
						</div>
						<div class="col-sm-12 col-md-10">
							<c:out value="${sysExcpVO.exceptionMethod }"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 col-md-2 control-label col-lg-2">
							<label>예외 발생 일자</label>
						</div>
						<div class="col-sm-12 col-md-10">
							<c:out value="${sysExcpVO.registDe }"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 col-md-2 control-label col-lg-2">
							<label>예외 발생 내용</label>
						</div>
						<div class="col-sm-12 col-md-10">
							<c:out value="${sysExcpVO.exceptionContent }"/>
						</div>
					</div>
				</div>
			</div>
			<div class="card-wrap-footer">
				<button type="button" class="btn btn-outline-secondary" onclick="fnSelectSysExcpList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
					<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
				</button>
			</div>
		</div>
	</div>
</div>

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>