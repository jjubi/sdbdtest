<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : ipCtrlRegist.jsp
  * @상세설명 : ip 관리 등록 페이지
  * @작성일시 : 2021. 01. 19
  * @작 성 자 : jeon
  * @수 정 이 력
  * @
  * @  수정일                 수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2021.01.19     jeon	            최초등록
  * @ 
  * 
  */
%>
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

<script src="${pageContext.request.contextPath}/static/js/vaiv/cmmn/adm/perm/ipCtrl.js"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="ipCtrlVO" staticJavascript="false" xhtml="true" cdata="false"/>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.create" /></h1>
			<form:form id="ipCtrlForm" commandName="ipCtrlVO" action="${pageContext.request.contextPath}/cmmn/adm/perm/ipCtrlInsert.do" method="post" onsubmit="return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivIpCtrl.regist.conectIp" /></c:set>
								<label for="connectIp">${title } <span class="pilsu">*</span> 
									<span id="checkIpStr" class="d-block"></span>
								</label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group">
									<input type="hidden" id="checkIpBoolean" name="checkIpBoolean" value="false">
									<input type="hidden" id="permAt" name="permAt" value="${permAt}"/>
									<form:input path="connectIp" id="connectIp" class="form-control" title="${title}" size="40" maxlength="15"/>
									<div><form:errors path="connectIp" cssClass="error" /></div>
									<div class="input-group-append">
										<a class="input-group-text text-reset" href="javascript:;" onclick="fn_IP_check();" title="<spring:message code="vaivIpCtrl.regist.ipDupCheck" /> <spring:message code="input.button" />"><spring:message code="vaivIpCtrl.regist.ipDupCheck" /></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectIpCtrlList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="submit" id="ipCtrlRegistBtn" class="btn btn-basic" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
						</button>
					</div>
				</div>
			</form:form>			
		</div>
	</div>
</div>

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>
