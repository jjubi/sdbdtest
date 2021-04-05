<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : qestnarRegist.jsp
  * @상세설명 : 설문조사 관리 등록 페이지
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
<c:set var="pageTitle"><spring:message code="vaivQestnar.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="qestnarVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="${pageContext.request.contextPath}/static/js/vaiv/cmmn/adm/qestnar/qestnar.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.create" /></h1>
			<form:form commandName="qestnarVO" action="${pageContext.request.contextPath}/cmmn/adm/qestnar/insertQestnar.do" method="post" onsubmit="return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivQestnar.regist.qestnarNm" /></c:set>
								<label for="qestnarNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="qestnarNm" class="form-control" title="${title} ${inputTxt}" size="40" required="true" maxlength="50" />
								<div><form:errors path="qestnarNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivQestnar.regist.qestnarPrface" /></c:set>
								<label for="qestnarPrface">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:textarea path="qestnarPrface" class="form-control summernoteArea" title="${title} ${inputTxt}" cols="30" rows="10" />
								<div><form:errors path="qestnarPrface" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivQestnar.regist.qestnarCnclsn" /></c:set>
								<label for="qestnarCnclsn">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:textarea path="qestnarCnclsn" class="form-control summernoteArea" title="${title} ${inputTxt}" cols="30" rows="10" />
								<div><form:errors path="qestnarCnclsn" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivQestnar.regist.qestnarTrget" /></c:set>
								<label for="qestnarTrgetY">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<label class="radio-inline" for="qestnarTrgetY">
                                	<form:radiobutton path="qestnarTrget" id="qestnarTrgetY" value="ALL" checked="checked" /> 전체
                                </label>
                                <label class="radio-inline" for="qestnarTrgetN">
                                	<form:radiobutton path="qestnarTrget" id="qestnarTrgetN" value="TARGET" /> 설정
                                </label>
							</div>
						</div>
						<div class="form-group" id="qestnarTrgetListDiv" style="display: none;">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivQestnar.regist.qestnarTrget" /> <spring:message code="title.list" /></c:set>
								<label for="qestnarTrgetList">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:hidden path="qestnarTrgetListStr"/>
								<div class="input-group btn_target">
									<div class="input-group-prepend target_search">
										<a href="#trgetListModal" data-toggle="modal" class="input-group-text">대상 검색</a>
									</div>
									<div class="boxOn target_wrap">
		
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivQestnar.regist.qestnarPd" /></c:set>
								<label for="qestnarBgnde">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group">
									<form:input path="qestnarBgnde" type="text" class="form-control b-datepicker" title="${title} 시작일 ${inputTxt}" size="40" maxlength="10" />
									<em class="hyphen">~</em>
									<form:input path="qestnarEndde" type="text" class="form-control b-datepicker" title="${title} 종료일 ${inputTxt}" size="40" maxlength="10" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaviQestnar.regist.qestnarDplctAt" /></c:set>
								<label for="qestnarDplctAtY">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<label class="radio-inline" for="qestnarDplctAtY">
                                	<form:radiobutton path="qestnarDplctAt" id="qestnarDplctAtY" value="Y" checked="checked" /> 허용
                                </label>
                                <label class="radio-inline" for="qestnarDplctAtN">
                                	<form:radiobutton path="qestnarDplctAt" id="qestnarDplctAtN" value="N" /> 허용안함
                                </label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaviQestnar.regist.qestnarPgeAt" /></c:set>
								<label for="qestnarPgeAtY">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<label class="radio-inline" for="qestnarPgeAtY">
                                	<form:radiobutton path="qestnarPgeAt" id="qestnarPgeAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="qestnarPgeAtN">
                                	<form:radiobutton path="qestnarPgeAt" id="qestnarPgeAtN" value="N" checked="checked" /> 사용안함
                                </label>
							</div>
						</div>
						<div class="form-group">
                            <div class="col-sm-12 col-md-2 control-label col-lg-2">
                            	<c:set var="title"><spring:message code="vaivQestnar.regist.useAt" /></c:set>
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
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectQestnarList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="submit" id="qestnarRegistBtn" class="btn btn-basic" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
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

<jsp:include page="/WEB-INF/jsp/vaiv/cmmn/adm/qestnar/qestnarTrgetListModal.jsp"/>