<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbsTyModify.jsp
  * @상세설명 : 게시판 유형 수정 페이지
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

<c:set var="pageTitle"><spring:message code="vaivBbsTy.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="bbsTyVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="${pageContext.request.contextPath}/static/js/vaiv/cmmn/adm/bbs/ty/bbsTy.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="bbsTyVO" action="${pageContext.request.contextPath}/cmmn/adm/bbs/ty/updateBbsTy.do" method="post" onsubmit="return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsTy.regist.bbsTyId" /></c:set>
								<label for="bbsTyId">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:hidden path="bbsTyId" />
								<c:out value="${bbsTyVO.bbsTyId }"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsTy.regist.bbsTyNm" /></c:set>
								<label for="bbsTyNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="bbsTyNm" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="100" />
								<div><form:errors path="bbsTyNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsTy.regist.bbsTyCode" /></c:set>
								<label for="bbsTyCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="bbsTyCode" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="20" />
								<div><form:errors path="bbsTyCode" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsTy.regist.bbsTyDc" /></c:set>
								<label for="bbsTyDc">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:textarea path="bbsTyDc" class="form-control" title="${title} ${inputTxt}" cols="30" rows="10" />
								<div><form:errors path="bbsTyDc" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
                            <div class="col-sm-12 col-md-2 control-label col-lg-2">
                            	<c:set var="title"><spring:message code="vaivBnr.regist.useAt" /></c:set>
								<label for="useAtY">${title } <span class="pilsu">*</span></label>
                            </div>
                            <div class="col-sm-12 col-md-10">
                                <label class="radio-inline" for="useAtY">
                                	<form:radiobutton path="useAt" id="useAtY" value="Y" checked="checked" /> 사용
                                </label>
                                <label class="radio-inline" for="useAtN">
                                	<form:radiobutton path="useAt" id="useAtN" value="N" /> 사용안함
                                </label>
                            </div>
                        </div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectBbsTyList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="button" id="bbsTyDeleteBtn" class="btn btn-danger" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">
							<spring:message code="button.delete" /> <!-- 삭제 -->
						</button>
						<button type="submit" id="bbsTyUpdateBtn" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
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
