<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbsRegist.jsp
  * @상세설명 : 게시판 등록 페이지
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

<c:set var="pageTitle"><spring:message code="vaivBbs.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="bbsVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="${pageContext.request.contextPath}/static/js/vaiv/cmmn/adm/bbs/bbs.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.create" /></h1>
			<form:form commandName="bbsVO" action="${pageContext.request.contextPath}/cmmn/adm/bbs/insertBbs.do" method="post" onsubmit="return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbs.regist.bbsTyId" /></c:set>
								<label for="bbsTyId">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="bbsTyId" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${bbsTyList}" itemValue="bbsTyId" itemLabel="bbsTyNm"/>
								</form:select>
								<div><form:errors path="bbsTyId" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbs.regist.bbsNm" /></c:set>
								<label for="bbsNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="bbsNm" class="form-control" title="${title} ${inputTxt}" size="40" required="true" maxlength="50" />
								<div><form:errors path="bbsNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbs.regist.bbsDc" /></c:set>
								<label for="bbsDc">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:textarea path="bbsDc" class="form-control" title="${title} ${inputTxt}" cols="30" rows="10" />
								<div><form:errors path="bbsDc" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbs.regist.bbsPdUseAt" /></c:set>
								<label for="bbsPdUseAtY">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<label class="radio-inline" for="bbsPdUseAtY">
                                	<form:radiobutton path="bbsPdUseAt" id="bbsPdUseAtY" value="Y" checked="checked" /> 사용
                                </label>
                                <label class="radio-inline" for="bbsPdUseAtN">
                                	<form:radiobutton path="bbsPdUseAt" id="bbsPdUseAtN" value="N" /> 사용안함
                                </label>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2 bbsPdDateArea">
								<c:set var="title"><spring:message code="vaivBbs.regist.bbsPd" /></c:set>
								<label for="bbsPdBgnde">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4 bbsPdDateArea">
								<div class="input-group">
									<form:input path="bbsPdBgnde" type="text" class="form-control b-datepicker" title="${title} 시작일 ${inputTxt}" size="40" maxlength="10" />
									<div class="input-group-append">
										<span class="input-group-text"> ~ </span>
									</div>
									<form:input path="bbsPdEndde" type="text" class="form-control b-datepicker" title="${title} 종료일 ${inputTxt}" size="40" maxlength="10" />
								</div>
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
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectBbsList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="submit" id="bbsRegistBtn" class="btn btn-basic" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
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